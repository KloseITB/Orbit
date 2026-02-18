package it.unipv.posfw.orbit.UI;

import java.awt.*;

import java.net.URL;

import javax.swing.*;

public final class Prefab {
	
	// prefab elements to speed up the UI creation process
	
	// WINDOW SETTINGS
	public final static int DEFAULT_WINDOW_WIDTH = 1280;
	public final static int DEFAULT_WINDOW_HEIGHT = 720;
	
	// COLORS
	public final static Color FRAME_BG = new Color(20, 18, 23);           // main Background Color
	public final static Color HEADER = new Color(40, 35, 50);             // header color
	public final static Color PANEL_BG = new Color(66, 61, 71);          // secondary background color
	public final static Color BUTTON_PURPLE = new Color(180, 160, 220);  // buttons color
	public final static Color ACCENT_YELLOW = new Color(255, 200, 0);    // underlining color
	
    // COMMON IMAGES
	public final URL ORBIT_LOGO = getClass().getResource("/images/commons/orbit_logo.png");
	public final URL ORBIT_ICON = getClass().getResource("/images/commons/orbit_icon_only.png");
	public final URL COVER_PLACEHOLDER = getClass().getResource("/images/commons/game_placeholder.png");
    
    // FONT
	public final static String FONT_NAME = "Arial"; // placeholder font
	
	// Methods

	// FRAME
	
	public static JFrame frameOrbit(String name, int width, int height) {
		JFrame frame = new JFrame();
		
		// Icon setup 
		if (new Prefab().ORBIT_ICON != null) {
		    frame.setIconImage(new ImageIcon(new Prefab().ORBIT_ICON).getImage());
		} else {
		    System.out.println("Error: Image not found");
		}
		
		// Frame settings
		frame.setTitle("Orbit - " + name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.getContentPane().setBackground(Prefab.FRAME_BG);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		return frame;
	}
	
	// BUTTON
	
	public static JButton buttonOrbit(String buttonText, int xPos, int yPos) {
		JButton button = new JButton(buttonText);
		button.setFocusable(false);
		button.setBounds(xPos, yPos, 200, 50);
		button.setBackground(Prefab.BUTTON_PURPLE);
		button.setForeground(Color.WHITE);
		button.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 18));
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		return button;
	}
	
	public static JButton buttonOrbit(String buttonText, int xPos, int yPos, int width, int height) {
		JButton button = new JButton(buttonText);
		button.setFocusable(false);
		button.setBounds(xPos, yPos, width, height);
		button.setBackground(Prefab.BUTTON_PURPLE);
		button.setForeground(Color.WHITE);
		button.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 18));
		return button;
	}
	
	// HEADER
	
	public static JPanel headerOrbit(int width, int height) {
		JPanel banner = new JPanel();
		JLabel OrbitLogoLabel = new JLabel(new ImageIcon(new Prefab().ORBIT_LOGO));
		banner.setBackground(Prefab.HEADER);
		banner.setBounds(0, 0, width, height);
		banner.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		
		if (new Prefab().ORBIT_LOGO != null) {
		    banner.add(OrbitLogoLabel);
		} else {
		    System.out.println("Error: Image not found");
		}
		
		return banner;
	}
	
	public static JPanel headerOrbit(int width) {
		JPanel banner = new JPanel();
		JLabel OrbitLogoLabel = new JLabel(new ImageIcon(new Prefab().ORBIT_LOGO));
		banner.setBackground(Prefab.HEADER);
		banner.setBounds(0, 0, width, 60);
		banner.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		
		if (new Prefab().ORBIT_LOGO != null) {
		    banner.add(OrbitLogoLabel);
		} else {
		    System.out.println("Error: Image not found");
		}
		
		return banner;
	}
	
	
	// BUTTON WITH GAME COVER
	
	public JButton imageButton(String imagePath) {
		// If the game doesn't have an image, use the placeholder one
		JButton button = new JButton();
		if (imagePath == null) {
			button.setIcon(new ImageIcon(new Prefab().COVER_PLACEHOLDER));
		}
		else {
			@SuppressWarnings("unused")
			URL imageURL = getClass().getResource(imagePath);
			button.setIcon(new ImageIcon(imagePath));
		}
		button.setPreferredSize(new Dimension(100, 140));
		return button;
	}
	
	// LABEL
	
	public static JLabel labelOrbit(String text, int fontStyle, int textSize) {
		
		JLabel label = new JLabel(text);
		label.setForeground(Color.WHITE);
		label.setFont(new Font(Prefab.FONT_NAME, fontStyle, textSize));
		
		return label;
	}
	
public static JLabel labelOrbit(String text, int fontStyle, int textSize, int hAllignment, int vAllignment) {
		
		JLabel label = new JLabel(text);
		label.setForeground(Color.WHITE);
		label.setFont(new Font(Prefab.FONT_NAME, fontStyle, textSize));
		label.setHorizontalAlignment(hAllignment);
		label.setVerticalAlignment(vAllignment);
		
		return label;
	}
}
