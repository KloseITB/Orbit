package it.unipv.posfw.orbit.view.UI.resources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class Prefab {
	
	// prefab elements to speed up the UI creation process
	
	// FRAME
	
	public static JFrame frameOrbit(String name, int width, int height) {
		JFrame frame = new JFrame();
		
		// icon setup 
		if (new Res().ORBIT_ICON != null) {
		    frame.setIconImage(new ImageIcon(new Res().ORBIT_ICON).getImage());
		} else {
		    System.out.println("Error: Image not found");
		}
		
		// frame settings
		frame.setTitle("Orbit - " + name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.getContentPane().setBackground(Res.FRAME_BG);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		return frame;
	}
	
	// BUTTON
	
	public static JButton buttonOrbit(String buttonText, int xPos, int yPos) {
		JButton button = new JButton(buttonText);
		button.setFocusable(false);
		button.setBounds(xPos, yPos, 200, 50);
		button.setBackground(Res.BUTTON_PURPLE);
		button.setForeground(Color.WHITE);
		button.setFont(new Font(Res.FONT_NAME, Font.BOLD, 18));
		return button;
	}
	
	public static JButton buttonOrbit(String buttonText, int xPos, int yPos, int width, int height) {
		JButton button = new JButton(buttonText);
		button.setFocusable(false);
		button.setBounds(xPos, yPos, width, height);
		button.setBackground(Res.BUTTON_PURPLE);
		button.setForeground(Color.WHITE);
		button.setFont(new Font(Res.FONT_NAME, Font.BOLD, 18));
		return button;
	}
	
	// HEADER
	
	public static JPanel headerOrbit(int width, int height) {
		JPanel banner = new JPanel();
		JLabel OrbitLogoLabel = new JLabel(new ImageIcon(new Res().ORBIT_LOGO));
		banner.setBackground(Res.HEADER);
		banner.setBounds(0, 0, width, height);
		banner.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		
		if (new Res().ORBIT_LOGO != null) {
		    banner.add(OrbitLogoLabel);
		} else {
		    System.out.println("Error: Image not found");
		}
		
		return banner;
	}
	
	public static JPanel headerOrbit(int width) {
		JPanel banner = new JPanel();
		JLabel OrbitLogoLabel = new JLabel(new ImageIcon(new Res().ORBIT_LOGO));
		banner.setBackground(Res.HEADER);
		banner.setBounds(0, 0, width, 60);
		banner.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		
		if (new Res().ORBIT_LOGO != null) {
		    banner.add(OrbitLogoLabel);
		} else {
		    System.out.println("Error: Image not found");
		}
		
		return banner;
	}
	
	
	// creates a button rappresented by the game's thumbnail
	public JButton imageButton(String imagePath) {
		JButton button = new JButton();
		if (imagePath == null) {
			button.setIcon(new ImageIcon(new Res().GAME_PLACEHOLDER));
		}
		else {
			URL imageURL = getClass().getResource(imagePath);
			button.setIcon(new ImageIcon(imagePath));
		}
		button.setPreferredSize(new Dimension(100, 140));
		return button;
	}
}
