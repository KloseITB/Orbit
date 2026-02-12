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
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.view.FacadeUI;
import it.unipv.posfw.orbit.view.UI.resources.Prefab;
import it.unipv.posfw.orbit.view.UI.resources.Res;


public class ShopWindow implements ActionListener{
	
	private JPanel gameListPanel;
	private JPanel gameInfoPanel;
	private JFrame shopFrame = Prefab.frameOrbit("Shop", Res.DEFAULT_WINDOW_WIDTH, Res.DEFAULT_WINDOW_HEIGHT);
	private JButton mainPageButton;
	private JButton libraryButton;
	public ShopWindow() {
		
		shopFrame.setLayout(new BorderLayout());
			
			// HEADER PANEL
			JPanel header = Prefab.headerOrbit(Res.DEFAULT_WINDOW_WIDTH);
			JLabel buffer = new JLabel();
			buffer.setPreferredSize(new Dimension(500, 60));
			
			// show the balance with the format #.00
			double userBalance = FacadeUI.getInstance().getSessionUser().getBalance();
			DecimalFormat df = new DecimalFormat("#.00"); 
			JLabel balanceLabel = new JLabel("Balance: " + df.format(userBalance) + "€"); 
			balanceLabel.setFont(new Font(Res.FONT_NAME, Font.BOLD, 20));
			balanceLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
			balanceLabel.setForeground(Color.WHITE);
			
			mainPageButton = Prefab.buttonOrbit("MAIN PAGE", 0, 0);
			header.add(mainPageButton);
			libraryButton = Prefab.buttonOrbit("LIBRARY", 0, 0);
			header.add(libraryButton);
			header.add(buffer);
			header.add(balanceLabel);
			
			// MAIN PANEL
			JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
			centerPanel.setOpaque(false);
			
				// GAME LIST PANEL
				gameListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 10));
				gameListPanel.setPreferredSize(new Dimension(700, 590));
				gameListPanel.setOpaque(false);
				JLabel gameCatalogLabel = new JLabel("ORBIT CATALOG");
				gameCatalogLabel.setPreferredSize(new Dimension(700, 80));
				gameCatalogLabel.setHorizontalAlignment(JLabel.LEFT);
				gameCatalogLabel.setVerticalAlignment(JLabel.CENTER);
				gameCatalogLabel.setFont(new Font(Res.FONT_NAME, Font.BOLD, 30));
				gameCatalogLabel.setForeground(Color.WHITE);
				gameListPanel.add(gameCatalogLabel);

				populateShopPanel();
				
				//GAME INFOS PANEL
				gameInfoPanel = new JPanel();
				gameInfoPanel.setPreferredSize(new Dimension(500, 590));
				gameInfoPanel.setBackground(Res.PANEL_BG);

			
			centerPanel.add(gameListPanel);
			centerPanel.add(gameInfoPanel);
		
			// ACTION LISTENERS
			mainPageButton.addActionListener(this);
			libraryButton.addActionListener(this);
			
		shopFrame.add(header, BorderLayout.NORTH);
		shopFrame.add(centerPanel);
		shopFrame.setVisible(true);
	}
	
	// helper to populate the Shop with every game available in the database
	private void populateShopPanel() {
		
		LinkedList<Game> catalog = FacadeUI.getInstance().getCatalog();
		LinkedList<Game> gamesNotOwned = new LinkedList<>();
		
		for (Game game : catalog) {
			if(!FacadeUI.getInstance().getSessionUserGames().contains(game)) {
				gamesNotOwned.add(game);
				System.out.println(game.getTitle());
			}
		}
		
		// create a button only for the games not owned by the user
		for (Game game : gamesNotOwned) { 
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
			
			addToCatalogPanel(gameButton); // add the button to gameListPanel
		}

	}
	
	
	// helper to add the game button
	private void addToCatalogPanel(JButton button) {
		gameListPanel.add(button);
	}
	
	// helper to create a darker version of an ImageIcon
	private ImageIcon createDarkerIcon(ImageIcon original) {
		
	    // get the image dimension
	    int width = original.getIconWidth();
	    int height = original.getIconHeight();

	    // creates a new BufferedImage to paint on
	    BufferedImage darkenedParams = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = darkenedParams.createGraphics();
	    
	    // draw the original image and add a darker filter
	    g2.drawImage(original.getImage(), 0, 0, null); 
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
		
		//BUY BUTTON
		Double priceWrapper = game.getCurrentPrice();
		JButton buyButton = Prefab.buttonOrbit("BUY FOR " + priceWrapper.toString() + "€", 0, 0);
		
		// action listener
		buyButton.addActionListener(e -> {
			System.out.println("User wants to buy " + game.getTitle()); // debug
		    new CheckoutWindow(game, this);
		});
		buyButton.setBackground(new Color(12, 109, 207)); // light blue button
		buyButton.setBorderPainted(false);
		
		// add all the components to the panel
		gameInfoPanel.add(titleLabel);
	    gameInfoPanel.add(Box.createVerticalStrut(20)); // blank space
	    gameInfoPanel.add(genreLabel);
	    gameInfoPanel.add(Box.createVerticalStrut(20)); // blank space
	    gameInfoPanel.add(buyButton);
	    
	    // draw components
	    gameInfoPanel.revalidate(); // Recalculate the layout
	    gameInfoPanel.repaint();    // draws on screen the updated UI

	}
	
	public void updateWindow() {
		shopFrame.revalidate(); // Recalculate the frame
		shopFrame.repaint();    // draws on screen the updated Catalog
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mainPageButton) {
			shopFrame.dispose();
			new MainPageWindow();
		}
		
		if(e.getSource() == libraryButton) {
			shopFrame.dispose();
			new LibraryWindow();
		}
		
	}
}
