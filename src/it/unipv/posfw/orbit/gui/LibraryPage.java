package it.unipv.posfw.orbit.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unipv.posfw.orbit.gui.FacadeUI;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.game.Game;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

public class LibraryPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel gameInfoPanel;
	private JButton homeButton;
	private JButton storeButton;

	public LibraryPage() {
		
		// FRAME
		
		setTitle("Orbit - Library");
		setIconImage(new ImageIcon(new Prefab().ORBIT_ICON).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Prefab.DEFAULT_WINDOW_WIDTH, Prefab.DEFAULT_WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBackground(Prefab.FRAME_BG);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// HEADER
		
		JPanel header = Prefab.createHeader();
		contentPane.add(header);
		contentPane.setLayout(null);
				
		homeButton = Prefab.createHeaderButton("HOME");
		homeButton.addActionListener(this);
		header.add(homeButton);
		
		storeButton = Prefab.createHeaderButton("STORE");
		storeButton.addActionListener(this);
		header.add(storeButton);
				
		// GAME CATALOG SECTION
		
		contentPane.add(createCatalogScrollPane(50, 120));
				
		// SELECTED GAME INFO SECTION
		
		gameInfoPanel = new JPanel();
		gameInfoPanel.setBackground(Prefab.PANEL_BG);
		gameInfoPanel.setBounds(660, 120, 550, 550);
		gameInfoPanel.setLayout(null);
		contentPane.add(gameInfoPanel);
		
	}
	
	
	// Helpers
	
	public JScrollPane createCatalogScrollPane(int x, int y) {
        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 15, 15));
        gridPanel.setBackground(Prefab.GRAY_BG);
        gridPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        User user = FacadeUI.getInstance().getCurrentUser();
        LinkedList<Game> userGameList = user.getLibrary().getGames();
        
        // Adding buttons
        for (Game game : userGameList){
			ImageIcon coverImage = new ImageIcon(getClass().getResource(game.getCoverPath())); // Retrieve Image Cover
			
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(100, 140));
            button.setIcon(coverImage);
            button.setFocusable(false);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            
            // Visual feedback for User
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBorder(BorderFactory.createLineBorder(Prefab.BUTTON_PURPLE, 2)); 
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    button.setBorder(BorderFactory.createLineBorder(Prefab.ACCENT_YELLOW, 3)); 
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    button.setBorder(BorderFactory.createLineBorder(Prefab.GRAY_BG, 2)); 
                }
            });
            
            // Action Listener to open the game's info page
            button.addActionListener(e -> {openGameInfo(game, coverImage);});
            
            // Wrapper Panel for the buttons
            JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            buttonWrapper.setOpaque(false);
            buttonWrapper.add(button);
            
            gridPanel.add(buttonWrapper);
        }

        // Wrapper Panel to prevent vertical stretching
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(new Color(25, 25, 30));
        wrapperPanel.add(gridPanel, BorderLayout.NORTH); 

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(Prefab.GRAY_BG, 1));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(x, y, 600, 550);

        return scrollPane;
    }
		
	private void openGameInfo(Game game, ImageIcon coverImage) {
		gameInfoPanel.removeAll(); // Removes all the previous elements from the Panel
		gameInfoPanel.setLayout(null);
		
		JLabel gameCover = new JLabel();
		gameCover.setIcon(coverImage);
		gameCover.setBounds(225, 30, 100, 140);
		gameInfoPanel.add(gameCover);
		
		JLabel gameTitle = new JLabel(game.getTitle().toUpperCase());
		gameTitle.setForeground(Color.WHITE);
		gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitle.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		gameTitle.setBounds(25, 190, 500, 24);
		gameInfoPanel.add(gameTitle);
		
		JLabel gameGenre = new JLabel("Genre: " + game.getGenre().toUpperCase());
		gameGenre.setForeground(Color.WHITE);
		gameGenre.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 14));
		gameGenre.setBounds(25, 220, 500, 24);
		gameInfoPanel.add(gameGenre);
		
		JLabel gameRating = new JLabel("Rating: " + game.avgRating() + "/5");
		gameRating.setForeground(Color.WHITE);
		gameRating.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 14));
		gameRating.setBounds(25, 250, 500, 24);
		gameInfoPanel.add(gameRating);
		
		JButton playButton = new JButton("PLAY");
		playButton.setForeground(Color.WHITE);
		playButton.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 16));
		playButton.setFocusable(false);
		playButton.setFocusPainted(false);
		playButton.setBorderPainted(false);
		playButton.setBackground(new Color(56, 209, 36)); // Light Green
		playButton.setBounds(25, 300, 100, 40);
		
		JButton reviewButton = new JButton();
		if (FacadeUI.getInstance().checkReview(game.getId(), FacadeUI.getInstance().getCurrentUser())) {
			reviewButton.setText("LEAVE A REVIEW");
			reviewButton.setForeground(Color.WHITE);
			reviewButton.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 16));
			reviewButton.setFocusable(false);
			reviewButton.setFocusPainted(false);
			reviewButton.setBorderPainted(false);
			reviewButton.setBackground(Prefab.BUTTON_PURPLE);
			reviewButton.setBounds(25, 360, 180, 40);
		}
		else {
			reviewButton.setText("GAME REVIEWED");
			reviewButton.setEnabled(false);
			reviewButton.setForeground(Color.WHITE);
			reviewButton.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 16));
			reviewButton.setFocusable(false);
			reviewButton.setFocusPainted(false);
			reviewButton.setBorderPainted(false);
			reviewButton.setBackground(Prefab.GRAY_BG);
			reviewButton.setBounds(25, 360, 180, 40);
		}
		

		
		// Action Listeners
		playButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, game.getTitle().toUpperCase() + " is launching...", "Game Started", JOptionPane.INFORMATION_MESSAGE);
		});
		reviewButton.addActionListener(e -> {
			ReviewPage frame = new ReviewPage(game);
			frame.setVisible(true);
		});
		
		gameInfoPanel.add(playButton);
		gameInfoPanel.add(reviewButton);
		gameInfoPanel.repaint(); // Draws on screen the updated gui
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == homeButton) {
			HomePage frame = new HomePage();
			frame.setVisible(true);
			this.dispose();
		}
		
		if(e.getSource() == storeButton) {
			StorePage frame = new StorePage();
			frame.setVisible(true);
			this.dispose();
		}
		
	}
}
