package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.account.User;

public class Review {
    
    // Variabili semplici
	
	
    private User autore;
    private int voto; // valutazione da 1 a 5
    private String testo;

    
    
    // Costruttore
    
    
    public Review(User autore, int voto, String testo) {
        this.autore = autore;
        this.voto = voto;
        this.testo = testo;
    }

    
    // Metodi per leggere i dati
    
    
    public String getNomeAutore() {
        return autore.getNickname();
    }
    
    

    public int getVoto() {
        return voto;
        
        
    }

    
    public String getTesto() {
        return testo;
        
    }
    
    
    @Override
    public String toString() {
        return getNomeAutore() + " ha votato " + voto + "/5: " + testo;
        
    }
}