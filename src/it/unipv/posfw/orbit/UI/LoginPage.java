package it.unipv.posfw.orbit.UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPage implements ActionListener{
	
	// parameters
	private final int WINDOW_WIDTH = 640;
	private final int WINDOW_HEIGHT = 640;
	private final Font FONT_REGULAR = new Font(Prefab.FONT_NAME, Font.PLAIN, 20);
	private final Font FONT_ROMANIC = new Font(Prefab.FONT_NAME, Font.ROMAN_BASELINE, 16);
	private final Font FONT_BOLD = new Font(Prefab.FONT_NAME, Font.BOLD, 16);
	
	private JFrame loginFrame;
	private JButton loginButton;
	private JButton signupButton;
	private JTextField nicknameTF;
	private JTextField passwordTF;
	private JLabel errorMessageLabel;
	
	
	// constructor
	public LoginPage() {

		// Initial Setup
		loginFrame = Prefab.frameOrbit("Login", WINDOW_WIDTH, WINDOW_HEIGHT);
		loginButton = Prefab.buttonOrbit("LOGIN", 0, 0);
		signupButton = Prefab.buttonOrbit("SIGNUP", 0, 0);
		
		// TOP BANNER
		JPanel topBanner = Prefab.headerOrbit(WINDOW_WIDTH);
		
		// LOGIN PANEL
		int loginPanelWidth = 350;
		int loginPanelHeight = 350;
		
		JPanel loginPanel = new JPanel();
		JLabel requestLabel = new JLabel("LOGIN OR SIGNUP TO ORBIT");
		JLabel usernameLabel = new JLabel("USERNAME");
		nicknameTF = new JTextField();
		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordTF = new JTextField();

		requestLabel.setForeground(Color.WHITE);
		requestLabel.setFont(FONT_REGULAR);
		requestLabel.setHorizontalAlignment(JLabel.CENTER);
		requestLabel.setVerticalAlignment(JLabel.CENTER);
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(FONT_BOLD);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(FONT_BOLD);
		
		loginPanel.setLayout(new GridLayout(6, 1, 0, 5));
		loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		loginPanel.setBounds((WINDOW_WIDTH - loginPanelWidth) / 2, (WINDOW_HEIGHT - loginPanelHeight) / 2, loginPanelWidth, loginPanelHeight);
		loginPanel.setBackground(Prefab.PANEL_BG);
		
		// LOGIN OR SIGNUP
		JPanel loginOrSignup = new JPanel( new FlowLayout(FlowLayout.CENTER, 15, 0));
		loginOrSignup.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		loginOrSignup.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		loginOrSignup.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		JLabel orLabel = new JLabel("OR");
		orLabel.setFont(FONT_REGULAR);
		orLabel.setForeground(Color.WHITE);
		loginOrSignup.add(loginButton);
		loginOrSignup.add(orLabel);
		loginOrSignup.add(signupButton);
		loginOrSignup.setOpaque(false);
		
		loginPanel.add(requestLabel);
		loginPanel.add(usernameLabel);
		loginPanel.add(nicknameTF);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordTF);
		loginPanel.add(loginOrSignup);
		
		// ERROR MESSAGES
		int labelHeight = 30;
		int labelY = loginPanel.getY() + loginPanel.getHeight() + 10; // 10px lower than the loginPanel
		errorMessageLabel = new JLabel();
		errorMessageLabel.setFont(FONT_ROMANIC);
		errorMessageLabel.setForeground(Color.RED);
		errorMessageLabel.setBounds(loginPanel.getX(), labelY, loginPanel.getWidth(), labelHeight);
		errorMessageLabel.setHorizontalAlignment(JLabel.CENTER);
		errorMessageLabel.setVisible(false);
		
		// ACTION LISTENERS
		loginButton.addActionListener(this);
		signupButton.addActionListener(this);
		
		// FRAME SETTINGS
		loginFrame.setLayout(null);
		loginFrame.add(topBanner);
		loginFrame.add(loginPanel);
		loginFrame.add(errorMessageLabel);
		loginFrame.setVisible(true);
	}
	
	
	// methods
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == loginButton) {
			if (FacadeUI.getInstance().loginUser(nicknameTF.getText(), passwordTF.getText())) {
				System.out.println("Login successful"); // debug string
				loginFrame.dispose();
				new FrontPage();
			}
			else {
				System.out.println("Login Failed"); // debug string
				errorMessageLabel.setText("USER CREDENTIALS NOT VALID");
				errorMessageLabel.setVisible(true);
				loginFrame.repaint();
			}
		}
		
		if (e.getSource() == signupButton) {
			if(FacadeUI.getInstance().signupUser(nicknameTF.getText(), passwordTF.getText())) {
				System.out.println("Signup successful"); // debug string
				loginFrame.dispose();
				new FrontPage();
			}
			else {
				System.out.println("Signup Failed"); // debug string
				errorMessageLabel.setText("NICKNAME ALREADY TAKEN");
				errorMessageLabel.setVisible(true);
				loginFrame.repaint();
			}
		}
		
	}
	
}
