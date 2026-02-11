package it.unipv.posfw.orbit.view.UI;

import java.awt.*;
import javax.swing.*;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.library.Library;
import it.unipv.posfw.orbit.view.FacadeUserInterface;
import it.unipv.posfw.orbit.view.UI.resources.Prefab;
import it.unipv.posfw.orbit.view.UI.resources.Res;

public class LibraryWindow {
	
	JPanel gameListPanel;
	
	JFrame libraryFrame = Prefab.frameOrbit("Library", Res.DEFAULT_WINDOW_WIDTH, Res.DEFAULT_WINDOW_HEIGHT);
	
	public LibraryWindow() {
		
		libraryFrame.setLayout(new BorderLayout());
			
			// HEADER PANEL
			JPanel header = Prefab.headerOrbit(Res.DEFAULT_WINDOW_WIDTH);
			header.add(Prefab.buttonOrbit("MAIN PAGE", 0, 0));
			header.add(Prefab.buttonOrbit("SHOP", 0, 0));
			
			// MAIN PANEL
			JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
			
				// GAME LIST PANEL
				gameListPanel = new JPanel(new GridLayout(6, 5, 5, 5));
				gameListPanel.setPreferredSize(new Dimension(700, 590));
				gameListPanel.setBackground(Color.RED);
				//GAME INFOS PANEL
				JPanel gameInfoPanel = new JPanel();
				gameInfoPanel.setPreferredSize(new Dimension(500, 590));
				gameInfoPanel.setBackground(Color.GREEN);
				populateLibrary(FacadeUserInterface.getInstance().getSessionUser());
				
			centerPanel.add(gameListPanel);
			centerPanel.add(gameInfoPanel);
		
			
		libraryFrame.add(header, BorderLayout.NORTH);
		libraryFrame.add(centerPanel);
		libraryFrame.setVisible(true);
	}
	
	private void populateLibrary(User user) {
		
		// get the user's library list
		// iterate through the list and get the image for each owned games
		// create a button 100x140 with the image as input
		// addToGameListPanel(button)
	}
	
	private void addToGameListPanel(JButton button) {
		gameListPanel.add(button);
	}
}
