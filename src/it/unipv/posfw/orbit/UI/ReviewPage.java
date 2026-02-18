package it.unipv.posfw.orbit.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.game.Review;

public class ReviewPage {
	
	private final int WINDOW_WIDTH = 400;
	private final int WINDOW_HEIGHT = 400;
	
	private JFrame frame;
    private User user;
    private Game game;

    public ReviewPage(User user, Game game) {
        this.user = user;
        this.game = game;
        initialize();
    }

    private void initialize() {
        frame = Prefab.frameOrbit(game.getTitle() + "Review", WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLayout(new BorderLayout());

        // title
        JLabel titleLabel = Prefab.labelOrbit("LEAVE A REVIEW", Font.BOLD, 25);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Prefab.HEADER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        // center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(30, 30, 35));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // slider to to choose the vote
        JLabel ratingLabel = new JLabel("Vote: 5/5");
        ratingLabel.setForeground(Color.WHITE);
        ratingLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        ratingLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        
        JSlider ratingSlider = new JSlider(1, 5, 5);
        ratingSlider.setOpaque(false);
        ratingSlider.setForeground(Color.WHITE);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        
        // listener that change the label when the slider is moved
        ratingSlider.addChangeListener(e -> ratingLabel.setText("Vote: " + ratingSlider.getValue() + "/5"));

        // comment area
        JLabel commentLabel = Prefab.labelOrbit("Describe your experience", Font.PLAIN, 14);
        commentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextArea commentArea = new JTextArea(5, 20);
        commentArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(commentArea);
        
        // added the element to the panel
        centerPanel.add(ratingLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(ratingSlider);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(commentLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(scrollPane);

        frame.add(centerPanel, BorderLayout.CENTER);

        // enter button
        JButton submitButton = Prefab.buttonOrbit("PUBLISH REVIEW", 0, 0);
        
        submitButton.addActionListener(e -> {
            int rating = ratingSlider.getValue();
            String comment = commentArea.getText();

            // creation of the review object
            Review newReview = new Review(user.getId(), game.getId(), rating, comment);
            
            // using the FacadeUI to save the review
            try {
            	FacadeUI.getInstance().saveReview(newReview);
                JOptionPane.showMessageDialog(frame, "Review published successfully!");
                frame.dispose(); // close the window
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error while saving: " + ex.getMessage());
            }
        });

        frame.add(submitButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
