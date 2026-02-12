package it.unipv.posfw.orbit.game;

public class Review {
    
    private int userId;
    private int gameId;
    private int rating; // 1-5
    private String comment;
    
    public Review(int userId, int gameId, int rating, String comment) {
        this.userId = userId;
        this.gameId = gameId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getUserId() { 
    	return userId; 
    }
    
    public int getGameId() { 
    	return gameId; 
    }
    
    public int getRating() { 
    	return rating; 
    }
    
    public String getComment() { 
    	return comment; 
    }
    
}