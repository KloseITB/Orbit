package it.unipv.posfw.orbit.view.UI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.view.FacadeUserInterface;
import it.unipv.posfw.orbit.view.UI.resources.Prefab;
import it.unipv.posfw.orbit.view.UI.resources.Res;

public class LoginWindow implements ActionListener{
	
	// PARAMETERS
	private final int WINDOW_WIDTH = 640;
	private final int WINDOW_HEIGHT = 640;
	private JFrame loginFrame;
	private JButton loginButton;
	private JButton signupButton;
	private JTextField nicknameTF;
	private JTextField passwordTF;
	private JLabel errorMessageLabel;
	private JLabel signupFailedMessageLabel;
	
    // FONT PRESETS
    
	private static Font FONT_REGULAR = new Font(Res.FONT_NAME, Font.PLAIN, 20);
	private static Font FONT_ROMANIC = new Font(Res.FONT_NAME, Font.ROMAN_BASELINE, 16);
	private static Font FONT_BOLD = new Font(Res.FONT_NAME, Font.BOLD, 16);
    
	public LoginWindow() {

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
		loginPanel.setBackground(Res.PANEL_BG);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// login button pressed
		if (e.getSource() == loginButton) {
			if (FacadeUserInterface.getInstance().setSessionUser(nicknameTF.getText(), passwordTF.getText())) {
				System.out.println("Login successful"); // debug string
				loginFrame.dispose();
				new MainPageWindow();
			}
			else {
				System.out.println("Login Failed"); // debug string
				errorMessageLabel.setText("USER CREDENTIALS NOT VALID");
				errorMessageLabel.setVisible(true);
				loginFrame.repaint();
			}
		}
		
		// signup button pressed
		if (e.getSource() == signupButton) {
			if(!FacadeUserInterface.getInstance().signupUser(nicknameTF.getText(), passwordTF.getText())) {
				System.out.println("Signup successful"); // debug string
				loginFrame.dispose();
				new MainPageWindow();
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
