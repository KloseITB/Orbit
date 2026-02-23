package it.unipv.posfw.orbit.gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.unipv.posfw.orbit.gui.FacadeUI;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.game.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ReviewPage extends JFrame implements ActionListener, ChangeListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel header = new JPanel();
	private JLabel ratingInfo;
	private JSlider ratingSlider;
	private JButton submitButton;
	private JTextArea descriptionPane;
	private Game game;
	
	public ReviewPage(Game game) {
		
		// Setting the parameter as global variable
		this.game = game;
		
		// FRAME
		
		setIconImage(new ImageIcon(new Prefab().ORBIT_ICON).getImage());
		setTitle("Orbit - Review Game");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Prefab.FRAME_BG);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// HEADER
		
		header.setBackground(Prefab.HEADER);
		header.setBounds(0, 0, 800, 60);
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel reviewLabel = new JLabel("LEAVE A REVIEW");
		reviewLabel.setForeground(Color.WHITE);
		reviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reviewLabel.setBounds(20, 0, 206, 60);
		reviewLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 24));
		header.add(reviewLabel);
		
		// RATING SECTION
		
		JPanel ratingPanel = new JPanel();
		ratingPanel.setBackground(Prefab.PANEL_BG);
		ratingPanel.setBounds(10, 81, 497, 350);
		contentPane.add(ratingPanel);
		ratingPanel.setLayout(null);
		
		ratingInfo = new JLabel("Vote: 3/5");
		ratingSlider = new JSlider();
		ratingSlider.setValue(3);
		ratingSlider.addChangeListener(this);
		ratingSlider.setBounds(10, 60, 470, 50);
		ratingSlider.setForeground(Color.WHITE);
		ratingSlider.setOpaque(false);
		ratingSlider.setMajorTickSpacing(1);
		ratingSlider.setPaintLabels(true);
		ratingSlider.setPaintTicks(true);
		ratingSlider.setSnapToTicks(true);
		ratingSlider.setMinorTickSpacing(1);
		ratingSlider.setMinimum(1);
		ratingSlider.setMaximum(5);
		ratingPanel.add(ratingSlider);
		
		JLabel ratingText = new JLabel("RATING");
		ratingText.setHorizontalAlignment(SwingConstants.LEFT);
		ratingText.setForeground(Color.WHITE);
		ratingText.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		ratingText.setBounds(20, 20, 100, 30);
		ratingPanel.add(ratingText);
		
		JLabel descriptionText = new JLabel("ADD A DESCRIPTION");
		descriptionText.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionText.setForeground(Color.WHITE);
		descriptionText.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		descriptionText.setBounds(20, 130, 207, 30);
		ratingPanel.add(descriptionText);
		
		descriptionPane = new JTextArea();
		descriptionPane.setLineWrap(true);
		descriptionPane.setBackground(Prefab.GRAY_BG);
		descriptionPane.setForeground(Color.WHITE);
		descriptionPane.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 11));
		descriptionPane.setBounds(20, 170, 460, 160);
		descriptionPane.setBorder(BorderFactory.createCompoundBorder(descriptionPane.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		ratingPanel.add(descriptionPane);
		
		// GENERAL INFO SECTION
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Prefab.PANEL_BG);
		infoPanel.setBounds(530, 80, 230, 350);
		contentPane.add(infoPanel);
		infoPanel.setLayout(null);
		
		JLabel gameImage = new JLabel();
		gameImage.setIcon(game.getCoverImg());
		gameImage.setBounds(65, 15, 100, 140);
		infoPanel.add(gameImage);
		
		JLabel gameTitleInfo = new JLabel( game.getTitle().toUpperCase());
		gameTitleInfo.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitleInfo.setForeground(Color.WHITE);
		gameTitleInfo.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 16));
		gameTitleInfo.setBounds(10, 160, 210, 20);
		infoPanel.add(gameTitleInfo);
		
		ratingInfo.setForeground(Color.WHITE);
		ratingInfo.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 16));
		ratingInfo.setBounds(20, 210, 80, 20);
		infoPanel.add(ratingInfo);
		
		submitButton = new JButton("SUBMIT");
		submitButton.addActionListener(this);
		submitButton.setSize(150, 40);
		submitButton.setLocation(40, 290);
		submitButton.setBackground(Prefab.BUTTON_PURPLE);
		submitButton.setForeground(Color.WHITE);
		submitButton.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		submitButton.setFocusable(false);
		submitButton.setFocusPainted(false);
		submitButton.setBorderPainted(false);
		infoPanel.add(submitButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submitButton) {
			
			int rating = ratingSlider.getValue();
            String comment = descriptionPane.getText();
            User reviewer = FacadeUI.getInstance().getCurrentUser();

            // creation of the review object
            Review newReview = new Review(reviewer.getId(), game.getId(), rating, comment);
            
            // Using FacadeUI to save the review
            if (FacadeUI.getInstance().saveReview(newReview)) {
                JOptionPane.showMessageDialog(null, "Review submitted correctly.\n Thank you for your feedback!", "Review Submitted Correctly", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); // Close the window
            } else {
                JOptionPane.showMessageDialog(null, "An error occurred while submitting the review. Retry", "Review Error", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		ratingInfo.setText("Vote: " + ratingSlider.getValue() + "/5");
		
	}
}
