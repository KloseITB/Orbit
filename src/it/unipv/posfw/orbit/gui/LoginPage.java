package it.unipv.posfw.orbit.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class LoginPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton signupButton;

	public LoginPage() {
		
		// FRAME
		
		setTitle("Orbit - Login Page");
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(new ImageIcon(new Prefab().ORBIT_ICON).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Prefab.FRAME_BG);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// HEADER
		
		JPanel header = Prefab.createHeader();
		contentPane.add(header);
		contentPane.setLayout(null);
		
		// INPUT CREDENTIALS SECTION
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(100, 100, 300, 300);
		loginPanel.setBackground(Prefab.PANEL_BG);
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel loginText = new JLabel("LOGIN OR SIGNUP TO ORBIT");
		loginText.setForeground(Color.WHITE);
		loginText.setHorizontalAlignment(SwingConstants.CENTER);
		loginText.setBounds(10, 15, 280, 24);
		loginText.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 19));
		loginPanel.add(loginText);
		
		JLabel nicknameText = new JLabel("NICKNAME");
		nicknameText.setHorizontalAlignment(SwingConstants.LEFT);
		nicknameText.setForeground(Color.WHITE);
		nicknameText.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 15));
		nicknameText.setBounds(10, 60, 270, 24);
		loginPanel.add(nicknameText);
		
		nicknameField = new JTextField();
		nicknameField.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 11));
		nicknameField.setBounds(10, 100, 280, 30);
		loginPanel.add(nicknameField);
		nicknameField.setColumns(10);
		
		JLabel passwordText = new JLabel("PASSWORD");
		passwordText.setHorizontalAlignment(SwingConstants.LEFT);
		passwordText.setForeground(Color.WHITE);
		passwordText.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 15));
		passwordText.setBounds(10, 150, 270, 24);
		loginPanel.add(passwordText);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 11));
		passwordField.setColumns(10);
		passwordField.setBounds(10, 190, 280, 30);
		loginPanel.add(passwordField);
		
		// LOGIN OR SIGNUP SECTION
		
		loginButton = new JButton("LOGIN");
		loginButton.addActionListener(this);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 19));
		loginButton.setFocusable(false);
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		loginButton.setBackground(new Color(180, 160, 220));
		loginButton.setBounds(10, 250, 108, 30);
		loginPanel.add(loginButton);
		
		JLabel orText = new JLabel("OR");
		orText.setHorizontalAlignment(SwingConstants.CENTER);
		orText.setForeground(Color.WHITE);
		orText.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 18));
		orText.setBounds(132, 254, 34, 24);
		loginPanel.add(orText);
		
		signupButton = new JButton("SIGNUP");
		signupButton.addActionListener(this);
		signupButton.setForeground(Color.WHITE);
		signupButton.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 19));
		signupButton.setFocusable(false);
		signupButton.setFocusPainted(false);
		signupButton.setBorderPainted(false);
		signupButton.setBackground(new Color(180, 160, 220));
		signupButton.setBounds(182, 250, 108, 30);
		loginPanel.add(signupButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			
			String password = String.valueOf(passwordField.getPassword());
			if (FacadeUI.getInstance().loginUser(nicknameField.getText(), password)) {
				HomePage frame = new HomePage();
				frame.setVisible(true);
				this.dispose();
			}
			else {
				System.out.println("Login Failed"); // Debug
				JOptionPane.showMessageDialog(null, "Nickname and/or password not valid", "Log in failed", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if(e.getSource() == signupButton) {
			
			String password = String.valueOf(passwordField.getPassword());
			if(FacadeUI.getInstance().signupUser(nicknameField.getText(), password)) {
				System.out.println(passwordField.getSelectedText()); // Debug
				System.out.println("Signup successful"); // Debug
				HomePage frame = new HomePage();
				frame.setVisible(true);
				this.dispose();
			}
			else {
				System.out.println("Signup Failed"); // Debug
				JOptionPane.showMessageDialog(null, "An account with this nickname already exists", "Sign up failed", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}
