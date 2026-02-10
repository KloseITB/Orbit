package it.unipv.posfw.orbit.view.UI;

import java.awt.*;
import javax.swing.*;


import it.unipv.posfw.orbit.view.UI.resources.Prefab;
import it.unipv.posfw.orbit.view.UI.resources.Res;

public class MainPageWindow {
	
	// ATTRIBUTES
	private final int WINDOW_WIDTH = 1280;
	private final int WINDOW_HEIGHT = 720;
	
	private JFrame mainPageFrame = Prefab.frameOrbit("Quick Access", WINDOW_WIDTH, WINDOW_HEIGHT);
	
	
	public MainPageWindow() {
		
		// MAIN CONTAINER
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
			
			// HEADER
			JPanel headerPanel = Prefab.headerOrbit(WINDOW_WIDTH, 140);
			
			// SECONDARY CONTAINER
			int borderThickness = 10;
			JPanel centerPanel = new JPanel(new GridLayout(1, 2, borderThickness, 0));
			centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			centerPanel.setBackground(Res.FRAME_BG);
		
				// NEWS CONTAINER
				JPanel newsPanel = new JPanel(new BorderLayout());
				newsPanel.setBackground(Color.ORANGE); // placeholder color
				newsPanel.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
				JLabel newsLabel = new JLabel("NEWS");
				newsLabel.setFont(new Font(Res.FONT_NAME, Font.BOLD, 30));
				newsLabel.setHorizontalAlignment(JLabel.CENTER);
				newsLabel.setVerticalAlignment(JLabel.TOP);
				newsLabel.setForeground(Color.WHITE);
				
					JPanel innerNewsPanel = new JPanel(new GridLayout(3, 1, 2, 0));
				
					innerNewsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
					innerNewsPanel.setBackground(Color.MAGENTA);
					innerNewsPanel.add(newsLabel);

				
				newsPanel.add(newsLabel, BorderLayout.NORTH);
				newsPanel.add(innerNewsPanel);
				
				
				// QUICK PLAY CONTAINER
				JPanel quickPlayPanel = new JPanel();
				quickPlayPanel.setBackground(Color.GREEN); // placeholder color
			
			centerPanel.add(newsPanel);
			centerPanel.add(quickPlayPanel);
		
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel);
		
		// FRAME SETTINGS
		mainPageFrame.add(mainPanel);
		mainPageFrame.setVisible(true);
		
	}
	
	
	// work in progress
	private JPanel gameNewsPanel(String newsTitle, String newsBody) {
		JPanel mainPanel = new JPanel(new FlowLayout());
		mainPanel.setBackground(Color.BLUE);
		
			JLabel gameImage = new JLabel();
			gameImage.setBackground(Color.BLACK);
			gameImage.setPreferredSize(new Dimension(2, 3));
			
			JPanel newsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
			newsPanel.setBackground(Color.CYAN);
				JLabel newsTitleLabel = new JLabel(newsTitle);
				newsTitleLabel.setFont(new Font(Res.FONT_NAME, Font.BOLD, 20));
				newsTitleLabel.setForeground(Color.WHITE);
				newsTitleLabel.setHorizontalAlignment(JLabel.LEFT);
				newsTitleLabel.setVerticalAlignment(JLabel.CENTER);
				JLabel newsBodyLabel = new JLabel (newsBody);
				newsBodyLabel.setForeground(Color.WHITE);
				newsBodyLabel.setFont(new Font(Res.FONT_NAME, Font.PLAIN, 12));
				newsBodyLabel.setHorizontalAlignment(JLabel.LEFT);
				newsBodyLabel.setVerticalAlignment(JLabel.TOP);
					
			newsPanel.add(newsTitleLabel);
			newsPanel.add(newsBodyLabel);
			
			
		//mainPanel.add(gameImage);
		mainPanel.add(newsPanel);
		return mainPanel;
	}

}
