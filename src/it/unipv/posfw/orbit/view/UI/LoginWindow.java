package it.unipv.posfw.orbit.view.UI;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;

import it.unipv.posfw.orbit.view.UI.resources.Prefab;
import it.unipv.posfw.orbit.view.UI.resources.Res;

public class LoginWindow implements ActionListener{
	
	// PARAMETERS
	private final int WINDOW_WIDTH = 640;
	private final int WINDOW_HEIGHT = 480;
	private JFrame loginFrame;
	private JButton loginButton;

    // FONT PRESETS
    
	private static Font FONT_REGULAR = new Font(Res.FONT_NAME, Font.PLAIN, 12);
	private static Font FONT_ROMANIC = new Font(Res.FONT_NAME, Font.ROMAN_BASELINE, 16);
    
	public LoginWindow() {
		
		
		// Initial Setup
		
		loginFrame = Prefab.frameOrbit("Login", WINDOW_WIDTH, WINDOW_HEIGHT);
		loginButton = Prefab.buttonOrbit("LOGIN", (WINDOW_WIDTH - 200) / 2, (WINDOW_HEIGHT - 50) / 2);
		
		// TOP BANNER
		
		JPanel topBanner = Prefab.headerOrbit(WINDOW_WIDTH);
		
		// LOGIN PANEL
		
		int loginPanelWidth = 250;
		int loginPanelHeight = 300;
		
		JPanel loginPanel = new JPanel();
		JLabel requestLabel = new JLabel("ACCESS TO YOUR ORBIT ACCOUNT");
		JLabel usernameLabel = new JLabel("USERNAME");
		JTextField usernameTF = new JTextField();
		JLabel passwordLabel = new JLabel("PASSWORD");
		JTextField passwordTF = new JTextField();
		
		
		requestLabel.setForeground(Color.WHITE);
		requestLabel.setFont(FONT_REGULAR);
		requestLabel.setAlignmentX(JLabel.CENTER);
		requestLabel.setAlignmentY(JLabel.CENTER);
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(FONT_ROMANIC);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(FONT_ROMANIC);
		
		loginPanel.setLayout(new GridLayout(6, 1, 10, 10));
		loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		loginPanel.setBounds((WINDOW_WIDTH - loginPanelWidth) / 2, (WINDOW_HEIGHT - loginPanelHeight) / 2, loginPanelWidth, loginPanelHeight);
		loginPanel.setBackground(Res.PANEL_BG);
		
		loginPanel.add(requestLabel);
		loginPanel.add(usernameLabel);
		loginPanel.add(usernameTF);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordTF);
		loginPanel.add(loginButton);

		// FRAME SETTINGS
		
		loginFrame.setLayout(null);
		
		loginFrame.add(topBanner);
		loginFrame.add(loginPanel);
		loginFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
}
