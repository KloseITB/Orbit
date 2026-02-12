package it.unipv.posfw.orbit.view.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.view.FacadeUI;
import it.unipv.posfw.orbit.view.UI.resources.Prefab;
import it.unipv.posfw.orbit.view.UI.ReviewWindow;
import it.unipv.posfw.orbit.view.UI.resources.Res;

public class LibraryWindow implements ActionListener{
	
	JPanel gameListPanel;
	JPanel gameInfoPanel;
	JFrame libraryFrame = Prefab.frameOrbit("Library", Res.DEFAULT_WINDOW_WIDTH, Res.DEFAULT_WINDOW_HEIGHT);
	JButton mainPageButton;
	JButton shopButton;
	
	public LibraryWindow() {
		
		libraryFrame.setLayout(new BorderLayout());
			
			// HEADER PANEL
			JPanel header = Prefab.headerOrbit(Res.DEFAULT_WINDOW_WIDTH);
			mainPageButton = Prefab.buttonOrbit("MAIN PAGE", 0, 0);
			header.add(mainPageButton);
			shopButton = Prefab.buttonOrbit("SHOP", 0, 0);
			header.add(shopButton);
			
			// MAIN PANEL
			JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
			centerPanel.setOpaque(false);
			
				
				// GAME LIST PANEL
				gameListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 10));
				gameListPanel.setPreferredSize(new Dimension(700, 590));
				gameListPanel.setOpaque(false);
				JLabel ownedGamesLabel = new JLabel("OWNED GAMES");
				ownedGamesLabel.setPreferredSize(new Dimension(700, 80));
				ownedGamesLabel.setHorizontalAlignment(JLabel.LEFT);
				ownedGamesLabel.setVerticalAlignment(JLabel.CENTER);
				ownedGamesLabel.setFont(new Font(Res.FONT_NAME, Font.BOLD, 30));
				ownedGamesLabel.setForeground(Color.WHITE);
				gameListPanel.add(ownedGamesLabel);
				populateLibraryPanel(FacadeUI.getInstance().getSessionUser());
				
				// GAME INFOS PANEL
				gameInfoPanel = new JPanel();
				gameInfoPanel.setPreferredSize(new Dimension(500, 590));
				gameInfoPanel.setBackground(Res.PANEL_BG);
			
			// ACTION LISTENERS
			mainPageButton.addActionListener(this);
			shopButton.addActionListener(this);
				
			centerPanel.add(gameListPanel);
			centerPanel.add(gameInfoPanel);
		
			
		libraryFrame.add(header, BorderLayout.NORTH);
		libraryFrame.add(centerPanel);
		libraryFrame.setVisible(true);
	}
	
	private void populateLibraryPanel(User user) {
		
		LinkedList<Game> userGames = FacadeUI.getInstance().getSessionUserGames();
		for (Game game : userGames) { 
			URL gameCoverPath;
			
			// if the image reference is null, the placeholder cover is used instead
			if(game.getCoverPath() == null) {
				gameCoverPath = new Res().GAME_PLACEHOLDER;
			}
			else {
				gameCoverPath = getClass().getResource(game.getCoverPath());
			}
			
			ImageIcon originalIcon = new ImageIcon(gameCoverPath); // original cover reference
			JButton gameButton = new JButton(new ImageIcon(gameCoverPath));
			gameButton.setPreferredSize(new Dimension(100, 140));
			gameButton.setOpaque(false);
			
			// button visuals
			gameButton.setBorderPainted(false);      
			gameButton.setContentAreaFilled(false);  
			gameButton.setFocusPainted(false);       
			gameButton.setOpaque(false);
			
			// generate the dark version of the image only when the button is clicked
			ImageIcon darkIcon = createDarkerIcon(originalIcon);
			gameButton.setPressedIcon(darkIcon);
			
			// for each button I add an action listener that will execute the code in the lambda expression
			gameButton.addActionListener(e -> {
				System.out.println("Clicked " + game.getTitle()); // debug
			    openGamePage(game); // open the relative game's info page 
			});
			
			addToGameListPanel(gameButton); // add the button to gameListPanel
		}

	}
	
	private void addToGameListPanel(JButton button) {
		gameListPanel.add(button);
	}
	
	private ImageIcon createDarkerIcon(ImageIcon original) {
		
	    // get the image dimension
	    int width = original.getIconWidth();
	    int height = original.getIconHeight();

	    // creates a new BufferedImage to paint on
	    BufferedImage darkenedParams = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = darkenedParams.createGraphics();

	    // draw the original image
	    g2.drawImage(original.getImage(), 0, 0, null);

	    // adds a dark filter to the image
	    g2.setColor(new Color(0, 0, 0, 150)); 
	    g2.fillRect(0, 0, width, height);

	    g2.dispose();
	    
	    return new ImageIcon(darkenedParams);
	}
	
	private void openGamePage(Game game) {

		gameInfoPanel.removeAll(); // remove all the previous elements from the Panel
		
		gameInfoPanel.setLayout(new BoxLayout(gameInfoPanel, BoxLayout.Y_AXIS));
		gameInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20px padding
		
		// INFOS
		JLabel titleLabel = new JLabel(game.getTitle());  // game title
		titleLabel.setFont(new Font(Res.FONT_NAME, Font.BOLD, 40));
		titleLabel.setForeground(Color.WHITE);
		
		JLabel genreLabel = new JLabel("Genre: " + game.getGenre()); // game genre
		genreLabel.setFont(new Font(Res.FONT_NAME, Font.PLAIN, 18));
		genreLabel.setForeground(Color.WHITE);
		
		//PLAY BUTTON (not fully implemented for obvious reasons)
		JButton playButton = Prefab.buttonOrbit("PLAY", 0, 0);
		playButton.setBackground(new Color(63, 193, 57)); // light green button
		playButton.setBorderPainted(false);
		playButton.addActionListener(e -> {
			System.out.println("Executing " + game.getTitle() + "..."); // debug
			playButton.setBackground(Color.GRAY); // the button changes to a gray color to give feedback to the user
		});
		// review button
		JButton reviewButton = Prefab.buttonOrbit("REVIEW GAME", 0, 0);
		
		// action listener
		reviewButton.addActionListener(e -> {
			System.out.println("User wants to review " + game.getTitle()); // debug
		    new ReviewWindow(FacadeUI.getInstance().getSessionUser(), game);
		});
	    
		// add all the elements to the panel
		gameInfoPanel.add(titleLabel);
	    gameInfoPanel.add(Box.createVerticalStrut(20)); // blank space
	    gameInfoPanel.add(genreLabel);
	    gameInfoPanel.add(Box.createVerticalStrut(20)); // blank space
	    gameInfoPanel.add(playButton);
	    gameInfoPanel.add(Box.createVerticalStrut(20)); // blank space
	    gameInfoPanel.add(reviewButton);
	    
	    // DRAW ELEMENTS
	    gameInfoPanel.revalidate(); // Recalculate the layout
	    gameInfoPanel.repaint();    // draws on screen the updated UI

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mainPageButton) {
			libraryFrame.dispose();
			new MainPageWindow();
		}
		
		if(e.getSource() == shopButton) {
			libraryFrame.dispose();
			new ShopWindow();
		}
		
	}
}
