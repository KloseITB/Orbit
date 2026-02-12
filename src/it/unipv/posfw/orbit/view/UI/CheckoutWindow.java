package it.unipv.posfw.orbit.view.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.view.FacadeUserInterface;
import it.unipv.posfw.orbit.view.UI.resources.Prefab;
import it.unipv.posfw.orbit.view.UI.resources.Res;
import java.awt.*;
import java.text.DecimalFormat;

public class CheckoutWindow {
	
	private final int WINDOW_SIZE = 800;

    public CheckoutWindow(Game game) {
        JFrame checkoutFrame = Prefab.frameOrbit("Checkout", WINDOW_SIZE, WINDOW_SIZE);
        checkoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checkoutFrame.setLayout(new BorderLayout());

        // CHECKOUT LABEL
        JLabel checkoutLabel = new JLabel("CHECKOUT");
        checkoutLabel.setForeground(Color.WHITE);
        checkoutLabel.setFont(new Font(Res.FONT_NAME, Font.BOLD, 36));
        checkoutLabel.setBorder(new EmptyBorder(20, 30, 10, 30));
        checkoutFrame.add(checkoutLabel, BorderLayout.NORTH);

        //CENTER PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        centerPanel.setOpaque(false); 
        
        // CREDIT CARD PAYMENT PANEL
        JPanel creditCardPanel = createOpaquePanel();
        creditCardPanel.setPreferredSize(new Dimension(300, 400));

        addLabel(creditCardPanel, "Credit card number");
        addTextField(creditCardPanel);
        addPadding(creditCardPanel, 15);
        addLabel(creditCardPanel, "Card Owner");
        addTextField(creditCardPanel);
        addPadding(creditCardPanel, 15);
        addLabel(creditCardPanel, "Expiration date");
        addTextField(creditCardPanel);
        
        creditCardPanel.add(Box.createVerticalGlue()); 
        addButton(creditCardPanel, "PAY");

        // RIGHT PANEL
        JPanel rightColumn = new JPanel();
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
        rightColumn.setOpaque(false);

        // TOP RIGHT PANEL
        JPanel topRightPanel = createOpaquePanel();
        topRightPanel.setPreferredSize(new Dimension(300, 190));
        
        // BALANCE LABEL
		double userBalance = FacadeUserInterface.getInstance().getSessionUser().getBalance();
		DecimalFormat df = new DecimalFormat("#.00"); 
        addLabel(topRightPanel, df.format(userBalance) + "€");
        addPadding(topRightPanel, 10);
        
        // GAME PRICE LABEL
        double gamePrice = game.getCurrentPrice();
        addLabel(topRightPanel, df.format(gamePrice) + "€");
        addPadding(topRightPanel, 40); 
        
        // BALANCE AFTER CHECKOUT LABEL
        double checkoutBalance = userBalance - gamePrice;
        addLabel(topRightPanel, "ACCOUNT BALANCE AFTER CHECKOUT:");
        addLabel(topRightPanel, df.format(checkoutBalance) + "€");

        // GIFT CARD PANEL
        JPanel giftCardPanel = createTransparentPanel();
        giftCardPanel.setPreferredSize(new Dimension(300, 190));
        
        addLabel(giftCardPanel, "Enter Gift card code");
        addTextField(giftCardPanel);
        addPadding(giftCardPanel, 15);
        addButton(giftCardPanel, "CONFIRM");

        // add panels to the right column
        rightColumn.add(topRightPanel);
        rightColumn.add(Box.createRigidArea(new Dimension(0, 20))); // Gap between red panels
        rightColumn.add(giftCardPanel);

        // add Main components to Center Panel
        centerPanel.add(creditCardPanel);
        centerPanel.add(rightColumn);

        checkoutFrame.add(centerPanel, BorderLayout.CENTER);

        // FRAME SETTINGS
        checkoutFrame.pack();
        checkoutFrame.setVisible(true);
    }

    // helper to create transparent Panels with BoxLayout
    private JPanel createTransparentPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        return panel;
    }
    
    // helper to create opaque Panels with BoxLayout
    private JPanel createOpaquePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Res.PANEL_BG);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        return panel;
    }

    // helper to create Labels
    private void addLabel(JPanel parent, String text) {
        JLabel label = Prefab.labelOrbit(text, Font.PLAIN, 14);
        label.setBorder(new EmptyBorder(3, 0, 3, 0)); // padding adjusted
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        parent.add(label);
        parent.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    // helper to create TextFields
    private void addTextField(JPanel parent) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); 
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        parent.add(textField);
    }

    // helper to create Purple Buttons
    private void addButton(JPanel parent, String text) {
        JButton btn = Prefab.buttonOrbit(text, 0, 0);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // fix for button width in BoxLayout to avoid stretching too wide or staying too small
        // we wrap it in a panel or set alignment carefully, but BoxLayout respects max size.
        parent.add(btn);
    }

    // helper for vertical spacing
    private void addPadding(JPanel parent, int height) {
        parent.add(Box.createRigidArea(new Dimension(0, height)));
    }
}
