package it.unipv.posfw.orbit.gui;

import java.awt.Color;
import java.awt.Font;

import it.unipv.posfw.orbit.account.Admin;
import it.unipv.posfw.orbit.account.Publisher;
import it.unipv.posfw.orbit.account.Role;
import it.unipv.posfw.orbit.account.User;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel accountInfoPanel;
	private JButton libraryButton;
	private JButton storeButton;
	private String nickname;
	private Role userRole;
	private JLabel accountType;
	private JTextField userField;
	private JTextField gameField;

	public HomePage() {


		User currentUser = FacadeUI.getInstance().getCurrentUser();
		nickname = currentUser.getNickname();
		userRole = currentUser.getRole();

		// FRAME

		setIconImage(new ImageIcon(new Prefab().ORBIT_ICON).getImage());
		setTitle("Orbit - Front Page");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Prefab.DEFAULT_WINDOW_WIDTH, Prefab.DEFAULT_WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(20, 18, 23));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// HEADER

		JPanel header = Prefab.createHeader();
		contentPane.add(header);

		libraryButton = Prefab.createHeaderButton("LIBRARY");
		libraryButton.addActionListener(this);
		header.add(libraryButton);

		storeButton = Prefab.createHeaderButton("STORE");
		storeButton.addActionListener(this);
		header.add(storeButton);

		// LATEST GAMES UPDATE SECTION

		JLabel newsLabel = new JLabel("UPDATES");
		newsLabel.setSize(149, 40);
		newsLabel.setLocation(50, 80);
		newsLabel.setForeground(new Color(255, 255, 255));
		newsLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 30));
		newsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(newsLabel);

		JPanel gridNewsPanel = new JPanel();
		gridNewsPanel.setBounds(50, 130, 550, 500);
		gridNewsPanel.setOpaque(false);
		contentPane.add(gridNewsPanel);
		gridNewsPanel.setLayout(new GridLayout(3, 1, 0, 10));

		JPanel updatePanel0 = Prefab.createGameUpdateLabel("added new guns", 
				"We added everyone's favourite Beretta M9 and H&K G11 avaliable for purchase in the shop");
		gridNewsPanel.add(updatePanel0);

		JPanel updatePanel1 = Prefab.createGameUpdateLabel("fixed game-breaking exploits", 
				"Naughty gamers started exploiting bugs to have an advantage. We fixed them");
		gridNewsPanel.add(updatePanel1);

		JPanel updatePanel2 = Prefab.createGameUpdateLabel("added cakes", 
				"We added cakes to our cooking game");
		gridNewsPanel.add(updatePanel2);

		// WELCOME SECTION

		JLabel welcomeLabel = new JLabel("WELCOME BACK, ");
		welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 30));
		welcomeLabel.setBounds(624, 80, 267, 40);
		contentPane.add(welcomeLabel);

		JLabel userNickLabel = new JLabel(nickname.toUpperCase());
		userNickLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userNickLabel.setForeground(Prefab.ACCENT_YELLOW);
		userNickLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 30));
		userNickLabel.setBounds(889, 80, 350, 40);
		contentPane.add(userNickLabel);

		// ACCOUNT-SPECIFIC ACTIONS SECTION

		accountInfoPanel = new JPanel();
		accountInfoPanel.setOpaque(false);
		accountInfoPanel.setBounds(634, 130, 600, 500);
		contentPane.add(accountInfoPanel);
		accountInfoPanel.setLayout(null);

		JLabel accountInfoText = new JLabel("YOUR ACCOUNT TYPE IS:");
		accountInfoText.setHorizontalAlignment(SwingConstants.LEFT);
		accountInfoText.setVerticalAlignment(SwingConstants.TOP);
		accountInfoText.setForeground(Color.WHITE);
		accountInfoText.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		accountInfoText.setBounds(20, 20, 252, 20);
		accountInfoPanel.add(accountInfoText);

		accountType = new JLabel();
		accountType.setHorizontalAlignment(SwingConstants.LEFT);
		accountType.setVerticalAlignment(SwingConstants.TOP);
		accountType.setForeground(Prefab.ACCENT_YELLOW);
		accountType.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		accountType.setBounds(273, 20, 150, 20);
		accountInfoPanel.add(accountType);

		getAccountPanel();
	}


	// Helpers

	private void getAccountPanel() {
		
		User loggedUser = FacadeUI.getInstance().getCurrentUser();

		if(userRole == Role.ADMIN) {
			accountType.setText("ADMIN");
			Admin adminAccount = (Admin) loggedUser;
			JLabel userLabel = new JLabel("INSERT A USER NICKNAME TO BAN");
			userLabel.setForeground(Color.WHITE);
			userLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 18));
			userLabel.setBounds(20, 80, 340, 20);
			accountInfoPanel.add(userLabel);

			userField = new JTextField();
			userField.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 12));
			userField.setBounds(20, 120, 315, 30);
			accountInfoPanel.add(userField);
			userField.setColumns(10);

			JButton userBanBtn = new JButton("BAN USER");
			userBanBtn.setForeground(Color.WHITE);
			userBanBtn.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
			userBanBtn.setFocusable(false);
			userBanBtn.setFocusPainted(false);
			userBanBtn.setBorderPainted(false);
			userBanBtn.setBackground(new Color(180, 160, 220));
			userBanBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// Check if a user with the inserted nickname exists
					if(FacadeUI.getInstance().findUser(userField.getText())) {
						User selectedUser = FacadeUI.getInstance().getUserFromId(userField.getText());
						adminAccount.banUser(selectedUser);
					}
					else {
						JOptionPane.showMessageDialog(
								null,
								"The nickname inserted doesn't correspond to any valid user",
								"User invalid",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			userBanBtn.setBounds(370, 115, 150, 40);
			accountInfoPanel.add(userBanBtn);

			JLabel gameLabel = new JLabel("INSERT A GAME ID TO BAN");
			gameLabel.setForeground(Color.WHITE);
			gameLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 18));
			gameLabel.setBounds(20, 190, 300, 20);
			accountInfoPanel.add(gameLabel);

			gameField = new JTextField();
			gameField.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 12));
			gameField.setColumns(10);
			gameField.setBounds(20, 230, 315, 30);
			accountInfoPanel.add(gameField);

			JButton gameBanBtn = new JButton("BAN GAME");
			gameBanBtn.setForeground(Color.WHITE);
			gameBanBtn.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
			gameBanBtn.setFocusable(false);
			gameBanBtn.setFocusPainted(false);
			gameBanBtn.setBorderPainted(false);
			gameBanBtn.setBackground(Prefab.BUTTON_PURPLE);
			gameBanBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int gameId = Integer.parseInt(gameField.getText());
					
					if(FacadeUI.getInstance().findGameById(gameId)){
						adminAccount.banPublishedGame(gameId);
						JOptionPane.showMessageDialog(
								null,
								"Game banned successfully. From now on it will be delisted from the Orbit Catalog",
								"Game banned",
								JOptionPane.INFORMATION_MESSAGE
								);
					}
					else {
						JOptionPane.showMessageDialog(null, "Insert a valid game ID", "ID not valid", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
			gameBanBtn.setBounds(370, 225, 150, 40);
			accountInfoPanel.add(gameBanBtn);
		}
		else if(userRole == Role.PUBLISHER) {
			accountType.setText("PUBLISHER");
			Publisher publisherAccount = (Publisher) loggedUser;

			JButton PublishGameBtn = new JButton("PUBLISH GAME");
			PublishGameBtn.setForeground(Color.WHITE);
			PublishGameBtn.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
			PublishGameBtn.setFocusable(false);
			PublishGameBtn.setFocusPainted(false);
			PublishGameBtn.setBorderPainted(false);
			PublishGameBtn.setBackground(Prefab.BUTTON_PURPLE);
			PublishGameBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(publisherAccount.getLicense()) {
						PublishingPage frame = new PublishingPage();
						frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null,
								"You need to buy a license before being able to publish a game",
								"License needed",
								JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
			PublishGameBtn.setBounds(20, 100, 200, 40);
			accountInfoPanel.add(PublishGameBtn);

			JButton buyLicenseBtn = new JButton("BUY LICENSE");
			if(publisherAccount.getLicense()) {
				buyLicenseBtn.setEnabled(false);
				buyLicenseBtn.setBackground(Prefab.GRAY_BG);
			}
			else {
				buyLicenseBtn.setEnabled(true);
				buyLicenseBtn.setBackground(Prefab.BUTTON_PURPLE);
			}
			buyLicenseBtn.setForeground(Color.WHITE);
			buyLicenseBtn.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
			buyLicenseBtn.setFocusable(false);
			buyLicenseBtn.setFocusPainted(false);
			buyLicenseBtn.setBorderPainted(false);
			buyLicenseBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					publisherAccount.setLicense(true);
					buyLicenseBtn.setEnabled(false);
					buyLicenseBtn.setBackground(Prefab.GRAY_BG);
					
				}
			});
			buyLicenseBtn.setBounds(20, 160, 200, 40);
			accountInfoPanel.add(buyLicenseBtn);
		}
		else {
			accountType.setText("USER");
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == libraryButton) {

			LibraryPage frame = new LibraryPage();
			frame.setVisible(true);
			this.dispose();
		}

		if(e.getSource() == storeButton) {
			StorePage frame = new StorePage();
			frame.setVisible(true);
			this.dispose();
		}

	}
}
