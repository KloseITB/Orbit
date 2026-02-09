package it.unipv.posfw.orbit.view.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class LoginWindow implements ActionListener{
	
	// WINDOW PARAMETERS
	
	private final int WINDOW_WIDTH = 640;
	private final int WINDOW_HEIGHT = 480;
	
	// WINDOW ELEMENTS
	
	JFrame frame = new JFrame();
	
	// COLORS
	
	private final Color FRAME_BG = new Color(30, 30, 35);           // main Background Color
    private final Color HEADER = new Color(40, 35, 50);            // header color
    private final Color PANEL_BG = new Color(60, 60, 60);           // secondary background color
    private final Color BUTTON_PURPLE = new Color(180, 160, 220);  // buttons color
    
    // RESOURCES
    
    private URL orbitLogo = getClass().getResource("/images/orbit_logo.png");
    private URL orbitIcon = getClass().getResource("/images/orbit_icon_only.png");
	
    // FONT
    
    private final String FONT_NAME = "MV Boli"; // placeholder font
	private Font regularFont = new Font(FONT_NAME, Font.PLAIN, 18);
	private Font boldFont = new Font(FONT_NAME, Font.BOLD, 18);
	private Font romanFont = new Font(FONT_NAME, Font.ROMAN_BASELINE, 18);
    
	public LoginWindow() {
		
		// TOP BANNER
		
		JPanel banner = new JPanel();
		JLabel OrbitLogoLabel = new JLabel(new ImageIcon(orbitLogo));
		banner.setBackground(HEADER);
		banner.setBounds(0, 0, WINDOW_WIDTH, 60);
		banner.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		
		if (orbitLogo != null) {
		    banner.add(OrbitLogoLabel);
		} else {
		    System.out.println("Errore: Immagine non trovata!");
		}
		
		// LOGIN PANEL
		
		JPanel loginPanel = new JPanel();
		JButton loginButton = new JButton("LOGIN");
		JLabel usernameLabel = new JLabel("USERNAME");
		JTextField usernameTF = new JTextField();
		JLabel passwordLabel = new JLabel("PASSWORD");
		JTextField passwordTF = new JTextField();
		
		int loginPanelWidth = 250;
		int loginPanelHeight = 300;
		
		loginButton.setFocusable(false);
		loginButton.setBounds((WINDOW_WIDTH - 200) / 2, (WINDOW_HEIGHT - 50) / 2, 200, 50);
		loginButton.setBackground(BUTTON_PURPLE);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(boldFont);
		
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(romanFont);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(romanFont);
		
		loginPanel.setLayout(new GridLayout(5, 1, 25, 25));
		loginPanel.setBorder(BorderFactory.createEmptyBorder(40, 25, 40, 25));
		loginPanel.setBounds((WINDOW_WIDTH - loginPanelWidth) / 2, (WINDOW_HEIGHT - loginPanelHeight) / 2, loginPanelWidth, loginPanelHeight);
		loginPanel.setBackground(PANEL_BG);
		
		loginPanel.add(usernameLabel);
		loginPanel.add(usernameTF);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordTF);
		loginPanel.add(loginButton);

		// FRAME SETTINGS
		
		if (orbitIcon != null) {
		    frame.setIconImage(new ImageIcon(orbitIcon).getImage());
		} else {
		    System.out.println("Errore: Immagine non trovata!");
		}
		
		frame.setTitle("Orbit - Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setLayout(null);
		frame.getContentPane().setBackground(FRAME_BG);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		frame.add(banner);
		frame.add(loginPanel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
