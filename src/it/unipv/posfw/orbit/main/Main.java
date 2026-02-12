package it.unipv.posfw.orbit.main;

import javax.swing.SwingUtilities;

import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.view.UI.*;

public class Main {
	
	public static void main(String[] args) {
		
		//new LoginWindow();
		//new MainPageWindow();
		//new LibraryWindow();
		new ShopWindow();
		//new CheckoutWindow(FacadeDB.getInstance().getGame(5)); // game set for debug
		


	}
	

}
