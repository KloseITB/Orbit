package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.account.User;

public class Review {
    
    // parameters
    private User author;
    private int vote; // vote from 1 to 5
    private String reviewText;
    
    
    // constructor
    public Review(User author, int vote, String reviewText) {
        this.author = author;
        this.vote = vote;
        this.reviewText = reviewText;
    }
    
    
    // methods
    public String getAuthorNickname() {
        return author.getNickname();
    }

    public int getVote() {
        return vote;
    }

    public String getReviewText() {
        return reviewText;
    }
    
    @Override
    public String toString() {
        return getAuthorNickname() + "gave a vote of " + vote + "/5: \n" + reviewText;
        
    }
}