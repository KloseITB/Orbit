package it.unipv.posfw.orbit.gui;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.URL;

public final class Prefab {
	
	// Parameters
	
	// COMMON WINDOW RESOLUTION
	public final static int DEFAULT_WINDOW_WIDTH = 1280;
	public final static int DEFAULT_WINDOW_HEIGHT = 720;
		
	// COLORS
	public final static Color FRAME_BG = new Color(20, 18, 23);           // Primary Background Color
	public final static Color PANEL_BG = new Color(66, 61, 71);           // Secondary background color
	public final static Color GRAY_BG = new Color(47, 45, 48);			  // Tertiary background
	public final static Color HEADER = new Color(40, 35, 50);             // Header color
	public final static Color BUTTON_PURPLE = new Color(180, 160, 220);   // Buttons color
	public final static Color ACCENT_YELLOW = new Color(255, 200, 0);     // Underlining color
		
	// COMMON IMAGES
	public final URL ORBIT_LOGO = getClass().getResource("/images/commons/orbit_logo.png");
	public final URL ORBIT_ICON = getClass().getResource("/images/commons/orbit_icon_only.png");
	public final URL PLACEHOLDER_COVER = getClass().getResource("/images/commons/game_placeholder.png");
	
	    
	// FONT
	public final static String FONT_NAME = "Arial"; // placeholder font
		
	// Methods
	
	// COMMONS
	
	public static JPanel createHeader() {
		JPanel header = new JPanel();
		header.setBounds(0, 0, 1280, 60);
		header.setAlignmentY(Component.TOP_ALIGNMENT);
		header.setAlignmentX(Component.LEFT_ALIGNMENT);
		header.setBackground(HEADER);
		FlowLayout fl_header = (FlowLayout) header.getLayout();
		fl_header.setAlignment(FlowLayout.LEFT);
		fl_header.setVgap(14);
		fl_header.setHgap(20);
		
		JLabel logoLabel = new JLabel();
		logoLabel.setIcon(new ImageIcon("C:\\Users\\Utente\\eclipse-workspace\\Orbit\\res\\images\\commons\\orbit_logo.png") /*ORBIT_LOGO*/);
		header.add(logoLabel);
		
		return header;
	}
	
	public static JButton createHeaderButton(String buttonText) {
		JButton button = new JButton(buttonText);
		button.setForeground(Color.WHITE);
		button.setFont(new Font(FONT_NAME, Font.BOLD, 20));
		button.setFocusable(false);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setBackground(BUTTON_PURPLE);
		button.setBounds(0, 0, 190, 40);
		
		return button;
	}
	
	// FRONT PAGE PREFABS
		
	public static JPanel createGameUpdateLabel(ImageIcon image, String updateTitleLabel, String updateDescription) {
		JPanel panel = new JPanel();
		panel.setBounds(50, 130, 550, 160);
		panel.setLayout(null);
		panel.add(createGameImage(image));
		panel.add(createUpdateTitle(updateTitleLabel));
		panel.add(createUpdateDetails(updateDescription));
		
		return panel;
	}
	
	// If no image is given, the program will use the placeholder cover
	public static JPanel createGameUpdateLabel(String updateTitleLabel, String updateDescription) {
		JPanel panel = new JPanel();
		panel.setSize(550, 160);
		ImageIcon gamePlaceholderImage = new ImageIcon("C:\\Users\\Utente\\eclipse-workspace\\Orbit\\res\\images\\commons\\game_placeholder.png")  /*PLACEHOLDER_COVER*/;
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.add(createGameImage(gamePlaceholderImage));
		panel.add(createUpdateTitle(updateTitleLabel));
		panel.add(createUpdateDetails(updateDescription));
		
		return panel;
	}
	
	private static JLabel createGameImage(ImageIcon image) {
		JLabel gameImage = new JLabel(image);
		gameImage.setHorizontalAlignment(SwingConstants.CENTER);
		gameImage.setBounds(20, 10, 100, 140);
		
		return gameImage;
	}
	
	
	private static JLabel createUpdateTitle(String title) {
		JLabel updateTitle = new JLabel(title.toUpperCase());
		updateTitle.setFont(new Font(FONT_NAME, Font.BOLD, 18));
		updateTitle.setForeground(Color.WHITE);
		updateTitle.setBounds(130, 10, 410, 20);
		
		return updateTitle;
	}
	
	private static JTextArea createUpdateDetails(String updateDescription) {
		JTextArea updateDetails = new JTextArea();
		updateDetails.setEditable(false);
		updateDetails.setLineWrap(true);
		updateDetails.setBackground(PANEL_BG);
		updateDetails.setForeground(Color.WHITE);
		updateDetails.setText(updateDescription);
		updateDetails.setFont(new Font(FONT_NAME, Font.PLAIN, 12));
		updateDetails.setBounds(130, 35, 410, 115);
		updateDetails.setBorder(BorderFactory.createCompoundBorder(updateDetails.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		return updateDetails;
	}
	
	// LIBRARY PAGE PREFABS

}