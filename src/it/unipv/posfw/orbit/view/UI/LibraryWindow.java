package it.unipv.posfw.orbit.view.UI;

import java.awt.*;
import java.net.URL;
import java.util.LinkedList;

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
			centerPanel.setOpaque(false);
			
				// GAME LIST PANEL
				gameListPanel = new JPanel(new GridLayout(6, 5, 5, 5));
				gameListPanel.setPreferredSize(new Dimension(700, 590));
				gameListPanel.setBackground(Color.RED);
				populateLibraryPanel(FacadeUserInterface.getInstance().getSessionUser());
				//GAME INFOS PANEL
				JPanel gameInfoPanel = new JPanel();
				gameInfoPanel.setPreferredSize(new Dimension(500, 590));
				gameInfoPanel.setBackground(Color.GREEN);
			
			// ACTION LISTENERS
			centerPanel.add(gameListPanel);
			centerPanel.add(gameInfoPanel);
		
			
		libraryFrame.add(header, BorderLayout.NORTH);
		libraryFrame.add(centerPanel);
		libraryFrame.setVisible(true);
	}
	
	private void populateLibraryPanel(User user) {
		
		LinkedList<Game> userGames = FacadeUserInterface.getInstance().getUserGames(user);
		for (Game game : userGames) { 
			URL gameCoverPath;
			
			// if the image reference is null, the placeholder cover is used instead
			if(game.getCoverPath() == null) {
				gameCoverPath = new Res().GAME_PLACEHOLDER;
			}
			else {
				gameCoverPath = getClass().getResource(game.getCoverPath());
			}
			
			JButton gameButton = new JButton(new ImageIcon(gameCoverPath));
			gameButton.setPreferredSize(new Dimension(100, 140));
			addToGameListPanel(gameButton);
		}
		
		
	}
	
	private void addToGameListPanel(JButton button) {
		gameListPanel.add(button);
	}
}
