package it.unipv.posfw.orbit.gui;

import java.awt.Color;
import java.awt.Font;

import it.unipv.posfw.orbit.account.User;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton libraryButton;
	private JButton storeButton;
	private User currentUser = FacadeUI.getInstance().getCurrentUser();
	
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
		
		// GAMES RECENTLY PLAYED SECTION
		
		JPanel accountInfoPanel = new JPanel();
		accountInfoPanel.setBounds(634, 130, 605, 500);
		contentPane.add(accountInfoPanel);
		
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
