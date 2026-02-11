package it.unipv.posfw.orbit.database;


import java.sql.*;
import java.util.LinkedList;
import it.unipv.posfw.orbit.account.*;
import it.unipv.posfw.orbit.exception.*;
import it.unipv.posfw.orbit.game.Game;

public class SingletonDatabaseHelper {
    
    private static SingletonDatabaseHelper instance;
    private static final String URL = "jdbc:sqlite:res/database/OrbitDB.db"; // we need to put here the directory of the .db file 
    
    // constructor
    private SingletonDatabaseHelper() {
    	try {
            Class.forName("org.sqlite.JDBC");
            createTables();  // create the tables to check id they exists
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver SQLite non trovato! Hai aggiunto il jar al build path?");
        }
    }
    
    // method instance
    public static SingletonDatabaseHelper getInstance() {
        if (instance == null) instance = new SingletonDatabaseHelper();
        return instance;
    }

    
    private void createTables() {
        try (Connection conn = DriverManager.getConnection(URL);  // conn create a connection between classes and the db, we need to create it in order to send the query to the db
             Statement stmt = conn.createStatement()) {
            
            
            String sqlUsers = "CREATE TABLE IF NOT EXISTS users (" +
                              "id INTEGER PRIMARY KEY AUTOINCREMENT, " + // id increments by itself
                              "nickname TEXT NOT NULL UNIQUE, " +
                              "password TEXT NOT NULL, " +
                              "role TEXT NOT NULL, " +
                              "balance REAL DEFAULT 0.0)";
            stmt.execute(sqlUsers); // execute the query and create the table

            String sqlGames = "CREATE TABLE IF NOT EXISTS games (" +
                              "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                              "title TEXT NOT NULL, " +
                              "tag TEXT NOT NULL, " +
                              "baseprice REAL NOT NULL, " +
                              "currentprice REAL NOT NULL, " +
                              "score REAL DEFAULT 0.0, " +
                              "cover_path TEXT, " +
                              "publisher_id INTEGER, " +
                              "FOREIGN KEY (publisher_id) REFERENCES users(id))";
            stmt.execute(sqlGames);

            String sqlLib = "CREATE TABLE IF NOT EXISTS library (" +
                            "user_id INTEGER, game_id INTEGER, " +
                            "purchase_date DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                            "PRIMARY KEY (user_id, game_id), " +
                            "FOREIGN KEY (user_id) REFERENCES users(id), " +
                            "FOREIGN KEY (game_id) REFERENCES games(id))";
            stmt.execute(sqlLib);
            
            String sqlTrans = "CREATE TABLE IF NOT EXISTS transactions (" +
                              "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                              "buyer_id INTEGER NOT NULL, " +
                              "game_id INTEGER NOT NULL, " +
                              "amount_paid REAL NOT NULL, " +
                              "transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                              "FOREIGN KEY (buyer_id) REFERENCES users(id), " +
                              "FOREIGN KEY (game_id) REFERENCES games(id))";
            stmt.execute(sqlTrans);
            
            String sqlGift = "CREATE TABLE IF NOT EXISTS gift_cards (" +
            				 "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            				 "code TEXT NOT NULL UNIQUE, " +
            				 "value REAL NOT NULL)";
            stmt.execute(sqlGift); 
            
     
            
            String sqlReviews = "CREATE TABLE IF NOT EXISTS reviews (" +
                              "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                              "game_id INTEGER, " + 
                              "autore TEXT, " +
                              "voto INTEGER, " +
                              "commento TEXT)";
            stmt.execute(sqlReviews);
           

        } catch (SQLException e) { e.printStackTrace(); } // print any error that could pop up
    }

    // method for login of SingletonAccountManager
    public User login(String nickname, String password) throws UserNotFoundException, WrongPasswordException{
    	
    	// check the nickname
        String sql = "SELECT * FROM users WHERE nickname = ?"; 
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nickname);
            ResultSet rs = pstmt.executeQuery(); // execute the query and save it in rs

            if (rs.next()) { 
            	// if there is a row in the results then the user exist, now we check the password
                String dbPassword = rs.getString("password");
                
                if(dbPassword.equals(password)) {
                	// correct password so we load user's data
                	int id = rs.getInt("id");
                	String role = rs.getString("role");
                	double balance = rs.getDouble("balance");
                
                	User user;
                	// set the user role
                	switch (role) {
                    	case "ADMIN":
                    		user = new Admin(id, nickname, password, balance);
                    		break;
                    	case "PUBLISHER":
                    		user = new Publisher(id, nickname, password, balance);
                    		break;
                    	default:
                    		user = new User(id, nickname, password, balance);
                	}
                               
                	user.setLoggedIn(true); // set user status as online
                	return user;
                	
                } else {
                	// wrong password
                	throw new WrongPasswordException("Password sbagliata");
                }
            }else {
            	// id re.next() is false, the nickname doesn't exist in the db
            	throw new UserNotFoundException("User inesistente");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        
        return null; // return null if there's an error in the login
    }
    
    // method register new user (sign up)
    public void registerUser(User user) throws PlayerAlreadyExistException {
        
        // check if nickname already exist
        String checkSql = "SELECT nickname FROM users WHERE nickname = ?";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            
            checkStmt.setString(1, user.getNickname());
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                // if we find a row the nickname is already in use
                throw new PlayerAlreadyExistException("Esiste già un utente con il nickname: " + user.getNickname());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // exit completely if there is an sql error
            return;
        }

        // is the nickname is free to use we can register the user
        String insertSql = "INSERT INTO users (nickname, password, role, balance) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            
            insertStmt.setString(1, user.getNickname());
            insertStmt.setString(2, user.getPassword()); // if we change the password to be masked we need to change here
            insertStmt.setString(3, "USER"); // default role, when do we change it?
            insertStmt.setDouble(4, 0.0);    
            
            int affectedRows = insertStmt.executeUpdate();
            
            if (affectedRows > 0) {
                // the id is generate by the db, we need to get it to set it in the local memory
                try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        user.setId(newId); 
                    }
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // method Purchase
    public void executePurchase(User buyer, Game game) throws AmountNotValidException { // launch the exception if the balance is not enough
        if (buyer.getBalance() < game.getCurrentPrice()) {
             throw new AmountNotValidException("Saldo insufficiente");
        }

        Connection conn = null; // set the connection as null before using it so that we can manage the exceptions later
        try {
            conn = DriverManager.getConnection(URL);
            conn.setAutoCommit(false); // conn has auto-save, we need to disable it to begin the transaction

            // update user balance
            String updateBalance = "UPDATE users SET balance = balance - ? WHERE id = ?";
            try (PreparedStatement p1 = conn.prepareStatement(updateBalance)) {
                p1.setDouble(1, game.getCurrentPrice());
                p1.setInt(2, buyer.getId());
                p1.executeUpdate(); // manually save the new balance
            }

            // add library
            String addLib = "INSERT INTO library(user_id, game_id) VALUES(?, ?)";
            try (PreparedStatement p2 = conn.prepareStatement(addLib)) {
                p2.setInt(1, buyer.getId());
                p2.setInt(2, game.getId());
                p2.executeUpdate(); // save the game in the library
            }

            // log the transaction
            String addTrans = "INSERT INTO transactions(buyer_id, game_id, amount_paid) VALUES(?, ?, ?)";
            try (PreparedStatement p3 = conn.prepareStatement(addTrans)) {
                p3.setInt(1, buyer.getId());
                p3.setInt(2, game.getId());
                p3.setDouble(3, game.getCurrentPrice());
                p3.executeUpdate(); // save the transaction
            }

            conn.commit(); // commit in db
            
            // update also the user memory 
            buyer.removeFunds(game.getCurrentPrice()); // balance
            buyer.getLibrary().addGame(game.getId()); // library

        } catch (SQLException e) { // if the connection was already open, cancel the transaction and print the error
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally { // this is always done: close the connection and print the error id there's a fail
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    
    // method add a game
    public void registerGame(Game game, int publisherId) {
    	
    	// sql query to add a game, with no id since it will be added by the db
    	String sql = "INSERT INTO games(title, baseprice, currentprice, tag, cover_path, publisher_id) VALUES(?, ?, ?, ?, ?)";
    	
    	try (Connection conn = DriverManager.getConnection(URL); // we specify that we want back the generated keys
    		 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    		
    		pstmt.setString(1, game.getTitle());
            pstmt.setDouble(2, game.getBasePrice());
            pstmt.setDouble(3, game.getCurrentPrice());
            pstmt.setString(4, game.getGenre());
            pstmt.setString(5, game.getCoverPath());
            pstmt.setInt(6, publisherId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // if we successfully added to the db the game we get the id back
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1); // get the id
                        game.setId(newId); // update the game in java memory
                        System.out.println("Gioco registrato con ID: " + newId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // print exceptions if any
        }
    }
    
    // method that check if Gift Card is valid
    public boolean checkGiftCard(String code) throws CodeNotFoundException {
        String sql = "SELECT * FROM gift_cards WHERE code = ?";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return true; // the gift card exist
            } 
            
        } catch (SQLException e) {
            e.printStackTrace(); // sql error
        }
        
        // if the code is not found or the sql has an error we get here
        throw new CodeNotFoundException("Codice Gift Card non trovato: " + code);
    }
    
    // method that return the gift card's value
    public double getGiftCardValue(String code) {
        
        String sql = "SELECT value FROM gift_cards WHERE code = ?";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("value"); // gift card value
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); // sql error
        }
        
        // if we get here there must have been an error with sql since we checked the
        // validity of the card before so we return 0 for safety
        return 0.0;
    }
    
    // methods to delete the gift card from the db once used
    public void discardGiftCard(String code) {
        String sql = "DELETE FROM gift_cards WHERE code = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // method to update the user balance
    public void updateUserBalance(User user) {
        String sql = "UPDATE users SET balance = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, user.getBalance());
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); 
        }
        }
        
        
     // metodo in più per salvare le recensioni
        
        public void salvaRecensioneDb(int idGioco, it.unipv.posfw.orbit.game.Review r) {
            String sql = "INSERT INTO reviews(game_id, autore, voto, commento) VALUES(?,?,?,?)";
            
            try (java.sql.Connection conn = java.sql.DriverManager.getConnection(URL);
                 java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, idGioco);
                pstmt.setString(2, r.getAuthorNickname()); 
                pstmt.setInt(3, r.getVote());
                pstmt.setString(4, r.getReviewText());
                pstmt.executeUpdate();
                System.out.println("Review salvata nel database!");
                
            } catch (java.sql.SQLException e) {
                System.out.println("Errore DB: " + e.getMessage());
            }
       
     
    }
    
    // method return a list of id (user's library) given a user
    public LinkedList<Integer> getLibrary(User user) {
        LinkedList<Integer> gameIds = new LinkedList<>();
        String sql = "SELECT game_id FROM library WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // add every id found in the list
                gameIds.add(rs.getInt("game_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameIds;
    }
    
    // method that return the object game given its id
    public Game getGame(int gameId) {
        String sql = "SELECT * FROM games WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // recover the data from the db
                String title = rs.getString("title");
                double basePrice = rs.getDouble("baseprice");
                double currentPrice = rs.getDouble("currentprice"); // get the current price since it can change
                String tag = rs.getString("tag");
                String coverPath = rs.getString("cover_path");

                Game game = new Game(title, basePrice, tag, coverPath);
                
                game.setId(gameId); //set the id with the one from the db
                game.setCurrentPrice(currentPrice); // set the current price
                
                return game;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // if the game doesn't exist
    }
    
    public LinkedList<Integer> getAllGameIds() {
        LinkedList<Integer> allGameIds = new LinkedList<>();
        // we select only the id from the game's table
        String sql = "SELECT id FROM games";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                allGameIds.add(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGameIds;
    }
    
    
}