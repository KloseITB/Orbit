package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.account.User;

public class Review {
    
    // parameters
    private int userId;
    private int gameId;
    private int vote; // vote from 1 to 5
    private String reviewText;
    
    
    // constructor
    public Review(int userId, int gameId, int vote, String reviewText) {
        this.userId = userId;
        this.gameId = gameId;
        this.vote = vote;
        this.reviewText = reviewText;
    }
    
    
    // methods
    public int getUserId() {
        return userId;
    }
    
    public int getGameId() { 
    	return gameId; 
    }

    public int getVote() {
        return vote;
    }
    
    public String getReviewText() {
        return reviewText;
    }
    

}