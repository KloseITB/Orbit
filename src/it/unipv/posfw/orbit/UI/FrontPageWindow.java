package it.unipv.posfw.orbit.view.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrontPageWindow implements ActionListener {
	
	// ATTRIBUTES
	private String userNickname = FacadeUI.getInstance().getCurrentUser().getNickname().toUpperCase();
	private JFrame mainPageFrame = Prefab.frameOrbit("Front Page", Prefab.DEFAULT_WINDOW_WIDTH, Prefab.DEFAULT_WINDOW_HEIGHT);
	private ImageIcon gamePlaceholderImage = new ImageIcon(new Prefab().GAME_PLACEHOLDER);
	private JButton libraryButton;
	private JButton shopButton;
	
	public FrontPageWindow() {
		
		// MAIN CONTAINER
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(Prefab.DEFAULT_WINDOW_WIDTH, Prefab.DEFAULT_WINDOW_HEIGHT));
			
			// HEADER
			JPanel headerPanel = Prefab.headerOrbit(Prefab.DEFAULT_WINDOW_WIDTH, 140);
			libraryButton = Prefab.buttonOrbit("LIBRARY", 0, 0);
			shopButton = Prefab.buttonOrbit("SHOP", 0, 0);
			
			headerPanel.add(libraryButton);
			headerPanel.add(shopButton);
			
			// SECONDARY CONTAINER
			int borderThickness = 10;
			JPanel centerPanel = new JPanel(new GridLayout(1, 2, borderThickness, 0));
			centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			centerPanel.setBackground(Prefab.FRAME_BG);
		
				// NEWS CONTAINER
				JPanel leftPanel = new JPanel(new BorderLayout());
				leftPanel.setOpaque(false);
				leftPanel.setBorder(BorderFactory.createEmptyBorder(6, 90, 0, 0));
				JLabel newsLabel = new JLabel("NEWS");
				newsLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 30));
				newsLabel.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
				newsLabel.setHorizontalAlignment(JLabel.LEFT);
				newsLabel.setVerticalAlignment(JLabel.TOP);
				newsLabel.setForeground(Color.WHITE);
				
					JPanel innerNewsPanel = new JPanel();
					innerNewsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
					innerNewsPanel.setOpaque(false);
					innerNewsPanel.add(gameNewsPanel("GOONING", "We added gooning to the game", gamePlaceholderImage));
					innerNewsPanel.add(gameNewsPanel("MEOW", "We added cats", gamePlaceholderImage));
					innerNewsPanel.add(gameNewsPanel("HELLO", "hi :)", gamePlaceholderImage));

				leftPanel.add(newsLabel, BorderLayout.NORTH);
				leftPanel.add(innerNewsPanel);
				
				// QUICK PLAY CONTAINER
				JPanel rightPanel = new JPanel(new BorderLayout());
				rightPanel.setOpaque(false);
				rightPanel.add(greetingsPanel(), BorderLayout.NORTH);
				rightPanel.add(quickPlayPanel(gamePlaceholderImage));
				
			centerPanel.add(leftPanel);
			centerPanel.add(rightPanel);
		
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel);
		
		// ACTION LISTENER
		libraryButton.addActionListener(this);
		shopButton.addActionListener(this);
		
		mainPageFrame.add(mainPanel);
		mainPageFrame.setVisible(true);
	}
	
	private JPanel gameNewsPanel(String updateTitleText, String updateBodyText, ImageIcon gameImage) {
		
		final Font FONT_BOLD = new Font(Prefab.FONT_NAME, Font.BOLD, 18);
		final Font FONT_PLAIN = new Font(Prefab.FONT_NAME, Font.PLAIN, 14);
		
		// MAIN PANEL
		JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		mainPanel.setPreferredSize(new Dimension(550,150));
		mainPanel.setOpaque(false);
		
		// GAME IMAGE
			JLabel gameImageLabel = new JLabel(new ImageIcon(new Prefab().GAME_PLACEHOLDER));
			gameImageLabel.setOpaque(true);
			
		// UPDATE DESCRIPTION
			JPanel update = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
			update.setPreferredSize(new Dimension(400,150));
			update.setOpaque(false);
			
		// UPDATE TITLE
				JLabel updateTitle = new JLabel(updateTitleText);
				updateTitle.setPreferredSize(new Dimension(400, 25));
				updateTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				updateTitle.setHorizontalAlignment(JLabel.LEFT);
				updateTitle.setVerticalAlignment(JLabel.TOP);
				updateTitle.setFont(FONT_BOLD);
				updateTitle.setForeground(Color.WHITE);
				
		// UPDATE BODY
				JLabel updateBody = new JLabel(updateBodyText);
				updateBody.setPreferredSize(new Dimension(400, 110));
				updateBody.setHorizontalAlignment(JLabel.LEFT);
				updateBody.setVerticalAlignment(JLabel.TOP);
				updateBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				updateBody.setFont(FONT_PLAIN);
				updateBody.setBackground(Prefab.PANEL_BG);
				updateBody.setForeground(Color.WHITE);
				updateBody.setOpaque(true);
				
			update.add(updateTitle);
			update.add(updateBody);
		
		mainPanel.add(gameImageLabel);
		mainPanel.add(update);
		return mainPanel;
	}
	
	private JPanel greetingsPanel() {
		JPanel greetingsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		greetingsPanel.setPreferredSize(new Dimension(0, 60));
		greetingsPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 20, 20));
		
		// WELCOME BACK,
		JLabel welcomeLabel = new JLabel("WELCOME BACK,");
		welcomeLabel.setPreferredSize(new Dimension(265, 30));
		welcomeLabel.setVerticalAlignment(JLabel.CENTER);
		welcomeLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 30));
		welcomeLabel.setForeground(Color.WHITE);
		
		// USER'S NICKNAME
		JLabel nicknameLabel = new JLabel(userNickname);
		nicknameLabel.setPreferredSize(new Dimension(250, 30));
		nicknameLabel.setVerticalAlignment(JLabel.CENTER);
		nicknameLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 30));
		nicknameLabel.setForeground(Prefab.ACCENT_YELLOW);
		
		greetingsPanel.add(welcomeLabel);
		greetingsPanel.add(nicknameLabel);
		greetingsPanel.setOpaque(false);
		return greetingsPanel;
	}
	
	private JPanel quickPlayPanel(ImageIcon gameImage) {
		
		//MAIN PANEL
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panel.setOpaque(false);
		
		// LAST PLAYED GAMES
		JLabel lastGameLabel = new JLabel("LAST PLAYED GAMES");
		lastGameLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 26));
		lastGameLabel.setForeground(Color.WHITE);
		lastGameLabel.setPreferredSize(new Dimension(600, 26));
		
		// GAMES IMAGE
		JLabel mostRecentlyPlayedPanel = gameImageLabel();
		JLabel secondMostPlayedGamePanel = gameImageLabel();
		JLabel thirdMostPlayedGamePanel = gameImageLabel();
		
		panel.add(lastGameLabel);
		panel.add(mostRecentlyPlayedPanel);
		panel.add(secondMostPlayedGamePanel);
		panel.add(thirdMostPlayedGamePanel);
		return panel;
	}
	
	private JLabel gameImageLabel() {
		
		JLabel gameImageLabel = new JLabel(new ImageIcon(new Prefab().GAME_PLACEHOLDER));
		gameImageLabel.setOpaque(true);
		return gameImageLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == libraryButton) {
			mainPageFrame.dispose();
			new LibraryWindow();
		}
		
		if(e.getSource() == shopButton) {
			mainPageFrame.dispose();
			new ShopWindow();
		}
		
	}

}
