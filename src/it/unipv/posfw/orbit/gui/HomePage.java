package it.unipv.posfw.orbit.gui;

import java.awt.Color;
import java.awt.Font;

import it.unipv.posfw.orbit.account.Admin;
import it.unipv.posfw.orbit.account.Publisher;
import it.unipv.posfw.orbit.account.User;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
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
	private User currentUser = FacadeUI.getInstance().getCurrentUser();
	private JLabel accountType;

	public HomePage() {

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

		JLabel userNickLabel = new JLabel(currentUser.getNickname().toUpperCase());
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
		
		if(FacadeUI.getInstance().getCurrentUser() instanceof Admin) {
			accountType.setText("ADMIN");
		}
		else if(FacadeUI.getInstance().getCurrentUser() instanceof Publisher) {
			accountType.setText("PUBLISHER");
			
			JButton PublishGameBtn = new JButton("PUBLISH GAME");
			PublishGameBtn.setForeground(Color.WHITE);
			PublishGameBtn.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
			PublishGameBtn.setFocusable(false);
			PublishGameBtn.setFocusPainted(false);
			PublishGameBtn.setBorderPainted(false);
			PublishGameBtn.setBackground(Prefab.BUTTON_PURPLE);
			PublishGameBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			PublishGameBtn.setBounds(20, 100, 200, 40);
			accountInfoPanel.add(PublishGameBtn);

			JButton buyLicenseBtn = new JButton("BUY LICENSE");
			buyLicenseBtn.setForeground(Color.WHITE);
			buyLicenseBtn.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
			buyLicenseBtn.setFocusable(false);
			buyLicenseBtn.setFocusPainted(false);
			buyLicenseBtn.setBorderPainted(false);
			buyLicenseBtn.setBackground(new Color(180, 160, 220));
			buyLicenseBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
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
