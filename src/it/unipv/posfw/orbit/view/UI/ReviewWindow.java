package it.unipv.posfw.orbit.view.UI;

import javax.swing.*;
import java.awt.*;
import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.game.Review;

public class ReviewWindow {
	
	private JFrame frame;
    private User user;
    private Game game;

    public ReviewWindow(User user, Game game) {
        this.user = user;
        this.game = game;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Recensisci: " + game.getTitle());
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(30, 30, 35)); 

        // title
        JLabel titleLabel = new JLabel("Lascia una recensione", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(30, 30, 35));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // slider to to choose the vote
        JLabel ratingLabel = new JLabel("Voto: 5/5");
        ratingLabel.setForeground(Color.LIGHT_GRAY);
        ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JSlider ratingSlider = new JSlider(1, 5, 5);
        ratingSlider.setBackground(new Color(30, 30, 35));
        ratingSlider.setForeground(Color.WHITE);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        
        // listener that change the label when the slider is moved
        ratingSlider.addChangeListener(e -> ratingLabel.setText("Voto: " + ratingSlider.getValue() + "/5"));

        // comment area
        JLabel commentLabel = new JLabel("Il tuo commento:");
        commentLabel.setForeground(Color.LIGHT_GRAY);
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
        JButton submitButton = new JButton("PUBBLICA RECENSIONE");
        submitButton.setBackground(new Color(180, 160, 220)); // Button Purple
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setFocusable(false);
        submitButton.setPreferredSize(new Dimension(400, 50));
        
        submitButton.addActionListener(e -> {
            int rating = ratingSlider.getValue();
            String comment = commentArea.getText();
            
            if (comment.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Scrivi un breve commento prima di inviare.");
                return;
            }

            // creation of the review object
            Review newReview = new Review(user.getId(), game.getId(), rating, comment);
            
            // using the FacadeDb to save the review
            try {
                FacadeDB.getInstance().saveReview(newReview);
                JOptionPane.showMessageDialog(frame, "Recensione pubblicata con successo!");
                frame.dispose(); // close the window
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Errore nel salvataggio: " + ex.getMessage());
            }
        });

        frame.add(submitButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
