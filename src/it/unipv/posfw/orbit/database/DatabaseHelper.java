package it.unipv.posfw.orbit.database;


import java.sql.*;
import java.util.ArrayList;
import it.unipv.posfw.orbit.account.*;
import it.unipv.posfw.orbit.exception.*;
import it.unipv.posfw.orbit.game.Game;

public class DatabaseHelper {
    
    private static DatabaseHelper instance;
    private static final String URL = "jdbc:sqlite:OrbitDB.db"; // poi bisogna mettere il file.db nella root del progetto
    
    //costruttore
    private DatabaseHelper() {
        createTables(); //prova creare le tabelle per vedere se esistono
    }
    
    //metodi
    public static DatabaseHelper getInstance() {
        if (instance == null) instance = new DatabaseHelper();
        return instance;
    }

    // chat gpt mi dice di farlo nel caso in cui le tabelle non siano già inizializzate, essendo che noi creeremo un file.bd già parzialmente completo, ha senso?
    private void createTables() {
        try (Connection conn = DriverManager.getConnection(URL);  //conn fa da ponte tra le classi e il db, bisgna usarla per poi passare le varie query
             Statement stmt = conn.createStatement()) {
            
            
            String sqlUsers = "CREATE TABLE IF NOT EXISTS users (" +
                              "id INTEGER PRIMARY KEY AUTOINCREMENT, " + //si incrementa da solo
                              "nickname TEXT NOT NULL UNIQUE, " +
                              "password TEXT NOT NULL, " +
                              "role TEXT NOT NULL, " +
                              "balance REAL DEFAULT 0.0)";
            stmt.execute(sqlUsers); //esegue la query e crea la tabella utenti

            String sqlGames = "CREATE TABLE IF NOT EXISTS games (" +
                              "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                              "title TEXT NOT NULL, " +
                              "tag TEXT NOT NULL, " +
                              "baseprice REAL NOT NULL, " +
                              "currentprice REAL NOT NULL, " +
                              "score REAL DEFAULT 0.0, " +
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

        } catch (SQLException e) { e.printStackTrace(); } //se ci sono errori sql questo lo stampa
    }

    // metodo login per SingletonAccountManager
    public User login(String nickname, String password) {
        String sql = "SELECT * FROM users WHERE nickname = ? AND password = ?"; //prendo tutte le info dello user
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nickname);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery(); //esegue la query e salva tutto in rs

            if (rs.next()) { //se c'è una riga nel risultato allora lo user esiste
                int id = rs.getInt("id");
                String role = rs.getString("role");
                double balance = rs.getDouble("balance");
                
                User user;
                // Istanzia la classe in base al ruolo
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
                                
                user.setLoggedIn(true); //diventa online
                return user;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null; //se c'è un errore nel login resitituisce null
    }
    
    //metodo acquisto 
    public void executePurchase(User buyer, Game game) throws AmountNotValidException { //lancia l'eccezione se il balance non basta
        if (buyer.getBalance() < game.getCurrentPrice()) {
             throw new AmountNotValidException("Saldo insufficiente");
        }

        Connection conn = null; //dichiaro la connessione prima di usarla effettivamente così poi posso gestire le eccezioni in fondo
        try {
            conn = DriverManager.getConnection(URL);
            conn.setAutoCommit(false); //conn ha il salvataggio automatico, lo si disabilita per iniziare la transazione

            //Aggiorna saldo utente
            String updateBalance = "UPDATE users SET balance = balance - ? WHERE id = ?";
            try (PreparedStatement p1 = conn.prepareStatement(updateBalance)) {
                p1.setDouble(1, game.getCurrentPrice());
                p1.setInt(2, buyer.getId());
                p1.executeUpdate(); //salva manualmente il nuovo saldo
            }

            //Aggiungi a Libreria
            String addLib = "INSERT INTO library(user_id, game_id) VALUES(?, ?)";
            try (PreparedStatement p2 = conn.prepareStatement(addLib)) {
                p2.setInt(1, buyer.getId());
                p2.setInt(2, game.getId());
                p2.executeUpdate(); //salva il gioco in libreria
            }

            //Registra Transazione
            String addTrans = "INSERT INTO transactions(buyer_id, game_id, amount_paid) VALUES(?, ?, ?)";
            try (PreparedStatement p3 = conn.prepareStatement(addTrans)) {
                p3.setInt(1, buyer.getId());
                p3.setInt(2, game.getId());
                p3.setDouble(3, game.getCurrentPrice());
                p3.executeUpdate(); //salva la transazione nello storico
            }

            conn.commit(); //commit nel db
            
            //Aggiorna lo stato in memoria dello user
            buyer.removeFunds(game.getCurrentPrice()); //saldo
            buyer.getLibrary().addGame(game.getId()); //libreria

        } catch (SQLException e) { //se la connesione era già aperta annulla la transazione in corso e stampa l'errore
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally { //questo viene fatto sempre: chiude la connessione e stampa l'errore in caso di fallimento
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}