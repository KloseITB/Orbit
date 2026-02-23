package it.unipv.posfw.orbit.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.exception.PaymentFailedException;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.gui.FacadeUI;
import it.unipv.posfw.orbit.payment.CreditCard;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;

import java.text.ParseException;

public class CheckoutPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField creditCardField;
	private JFormattedTextField expDateField;
	private JTextField ownerField;
	private JTextField giftCardField;
	private JButton payButton;
	private JButton balanceButton;
	private JButton applyButton;
	private User currentUser = FacadeUI.getInstance().getCurrentUser();
	private Game game;

	public CheckoutPage(Game game) {
		// Setting the parameter as global variable
		this.game = game;
		
		// FRAME
		setIconImage(new ImageIcon(new Prefab().ORBIT_ICON).getImage());
		setTitle("Orbit - Checkout");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 800, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(20, 18, 23));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// HEADER
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 800, 60);
		header.setAlignmentY(Component.TOP_ALIGNMENT);
		header.setAlignmentX(Component.LEFT_ALIGNMENT);
		header.setBackground(new Color(40, 35, 50));
		FlowLayout fl_header = (FlowLayout) header.getLayout();
		fl_header.setAlignment(FlowLayout.LEFT);
		fl_header.setVgap(15);
		fl_header.setHgap(15);
		contentPane.add(header);
		
		JLabel checkoutLabel = new JLabel("CHECKOUT");
		checkoutLabel.setForeground(new Color(255, 255, 255));
		checkoutLabel.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 30));
		checkoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(checkoutLabel);
		
		// CREDIT CARD SECTION
		
		JPanel creditCardCheckout = new JPanel();
		creditCardCheckout.setBackground(new Color(66, 61, 71));
		creditCardCheckout.setBounds(10, 100, 400, 600);
		contentPane.add(creditCardCheckout);
		creditCardCheckout.setLayout(null);
		
		JLabel creditCardLabel = new JLabel("CREDIT CARD NUMBER");
		creditCardLabel.setBounds(20, 20, 189, 17);
		creditCardLabel.setForeground(new Color(255, 255, 255));
		creditCardLabel.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 16));
		creditCardCheckout.add(creditCardLabel);
		
		creditCardField = new JFormattedTextField();
		try {
		    // Defines a mask for a credit card number
		    MaskFormatter creditCardMask = new MaskFormatter("#### #### #### ####");
		    creditCardMask.setPlaceholderCharacter('-'); // Sets a placeholder symbol
		    creditCardMask.install(creditCardField); // Applies the mask
		    creditCardField.setFocusLostBehavior(JFormattedTextField.PERSIST); // Stops Swing from hiding the mask when the field loses focus
		    creditCardField.setText("---- ---- ---- ----");// Forces the empty mask to display immediately
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		creditCardField.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 14));
		creditCardField.setBounds(20, 50, 360, 30);
		creditCardCheckout.add(creditCardField);
		creditCardField.setColumns(10);
		
		JLabel expDateLabel = new JLabel("EXPIRATION DATE");
		expDateLabel.setForeground(Color.WHITE);
		expDateLabel.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 16));
		expDateLabel.setBounds(20, 120, 159, 17);
		creditCardCheckout.add(expDateLabel);
		
		expDateField = new JFormattedTextField();
		try {
		    // Defines a mask for a credit card number
		    MaskFormatter expDateMask = new MaskFormatter("##" + "/" + "##");
		    expDateMask.setPlaceholderCharacter('-'); // Sets a placeholder symbol
		    expDateMask.install(expDateField); // Applies the mask
		    expDateField.setFocusLostBehavior(JFormattedTextField.PERSIST); // Stops Swing from hiding the mask when the field loses focus
		    expDateField.setText("--/--");// Forces the empty mask to display immediately
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		expDateField.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 14));
		expDateField.setColumns(10);
		expDateField.setBounds(20, 150, 360, 30);
		creditCardCheckout.add(expDateField);
		
		JLabel secCodeLabel = new JLabel("SECURITY CODE");
		secCodeLabel.setForeground(Color.WHITE);
		secCodeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		secCodeLabel.setBounds(20, 220, 159, 17);
		creditCardCheckout.add(secCodeLabel);
		
		JFormattedTextField secCodeField = new JFormattedTextField();
		try {
		    // Defines a mask for a credit card number
		    MaskFormatter secCodeMask = new MaskFormatter("###");
		    secCodeMask.setPlaceholderCharacter('-'); // Sets a placeholder symbol
		    secCodeMask.install(secCodeField); // Applies the mask
		    secCodeField.setFocusLostBehavior(JFormattedTextField.PERSIST); // Stops Swing from hiding the mask when the field loses focus
		    secCodeField.setText("---");// Forces the empty mask to display immediately
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		secCodeField.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 14));
		secCodeField.setColumns(10);
		secCodeField.setBounds(20, 250, 360, 30);
		creditCardCheckout.add(secCodeField);
		
		JLabel ownerLabel = new JLabel("CARD OWNER");
		ownerLabel.setForeground(Color.WHITE);
		ownerLabel.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 16));
		ownerLabel.setBounds(20, 320, 159, 17);
		creditCardCheckout.add(ownerLabel);
		
		ownerField = new JTextField();
		ownerField.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 14));
		ownerField.setColumns(10);
		ownerField.setBounds(20, 350, 360, 30);
		creditCardCheckout.add(ownerField);
		
		payButton = new JButton("PAY NOW");
		payButton.addActionListener(this);
		payButton.setFocusable(false);
		payButton.setBackground(new Color(38, 157, 255));
		payButton.setForeground(Color.WHITE);
		payButton.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		payButton.setBounds(20, 540, 159, 40);
		payButton.setFocusPainted(false);
		payButton.setBorderPainted(false);
		creditCardCheckout.add(payButton);
		
		// BALANCE SECTION
		
		JPanel balanceUpdatePanel = new JPanel();
		balanceUpdatePanel.setBackground(new Color(66, 61, 71));
		balanceUpdatePanel.setBounds(450, 100, 320, 273);
		contentPane.add(balanceUpdatePanel);
		balanceUpdatePanel.setLayout(null);
		
		JLabel balanceLabel = new JLabel("ACCOUNT BALANCE: " + currentUser.getBalance() + "€");
		balanceLabel.setForeground(Color.WHITE);
		balanceLabel.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 16));
		balanceLabel.setBounds(20, 30, 281, 17);
		balanceUpdatePanel.add(balanceLabel);
		
		JLabel paymentLabel = new JLabel("PAYMENT: " + game.getCurrentPrice() + "€");
		paymentLabel.setForeground(Color.WHITE);
		paymentLabel.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 16));
		paymentLabel.setBounds(20, 80, 281, 17);
		balanceUpdatePanel.add(paymentLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 150, 300, 4);
		balanceUpdatePanel.add(separator);
		
		double updatedBalance = currentUser.getBalance() - game.getCurrentPrice();
		if(updatedBalance < 0){
			updatedBalance = 0;
		}
		
		JLabel balanceUpdateLabel = new JLabel("FINAL BALANCE: " + updatedBalance + "€");
		balanceUpdateLabel.setForeground(Color.WHITE);
		balanceUpdateLabel.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 16));
		balanceUpdateLabel.setBounds(20, 200, 300, 17);
		balanceUpdatePanel.add(balanceUpdateLabel);
		
		// GIFT CARD SECTION
		
		JLabel enterGiftCardLabel = new JLabel("ENTER COUPON CODE");
		enterGiftCardLabel.setBounds(450, 500, 271, 17);
		contentPane.add(enterGiftCardLabel);
		enterGiftCardLabel.setForeground(Color.WHITE);
		enterGiftCardLabel.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 16));
		
		
		giftCardField = new JTextField();
		giftCardField.setFont(new Font(Prefab.FONT_NAME, Font.PLAIN, 14));
		giftCardField.setColumns(10);
		giftCardField.setBounds(450, 528, 320, 30);
		contentPane.add(giftCardField);

		applyButton = new JButton("APPLY");
		applyButton.addActionListener(this);
		applyButton.setForeground(Color.WHITE);
		applyButton.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
		applyButton.setFocusable(false);
		applyButton.setFocusPainted(false);
		applyButton.setBorderPainted(false);
		applyButton.setBackground(new Color(180, 160, 220));
		applyButton.setBounds(450, 579, 134, 40);
		contentPane.add(applyButton);
		
				balanceButton = new JButton("USE BALANCE");
				balanceButton.setBounds(450, 394, 189, 40);
				contentPane.add(balanceButton);
				balanceButton.addActionListener(this);
				balanceButton.setForeground(Color.WHITE);
				balanceButton.setFont(new Font(Prefab.FONT_NAME, Font.BOLD, 20));
				balanceButton.setFocusable(false);
				balanceButton.setFocusPainted(false);
				balanceButton.setBorderPainted(false);
				balanceButton.setBackground(new Color(180, 160, 220));
		//contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{creditCardCheckout, header, checkoutLabel, creditCardLabel, creditCardField, expDateLabel, expDateField, ownerLabel, ownerField, payButton, balanceButton, secCodeField, secCodeLabel, balanceUpdatePanel, balanceLabel, paymentLabel, separator, balanceUpdateLabel, enterGiftCardLabel, giftCardField, applyButton}));

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == applyButton) {
			
			if(FacadeUI.getInstance().checkGiftCardCode(giftCardField.getText())) {
				currentUser.addGiftCardFunds(giftCardField.getText()); 
				// update the GUI to show the new balance
			}
			else {
				JOptionPane.showMessageDialog(null, "The inserted code is not valid or has been already used", "Code not valid", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if (e.getSource() == balanceButton) {
			 
			 try{
			  		game.buy(currentUser);
			  		currentUser.removeFunds(game.getCurrentPrice());
					this.dispose();
			  }
			  catch (PaymentFailedException pfe){
			  		JOptionPane.showMessageDialog(
			  		null, 
			  		"The account balance is insufficient. Consider adding funds via gift cards or buying the game directly using a credit card", 
			  		"Payment failed", 
			  		JOptionPane.ERROR_MESSAGE
			 		);
			 }
			 	 
		}
		
		if(e.getSource() == payButton) {
			CreditCard creditCard = new CreditCard(creditCardField.getText(), expDateField.getText(), ownerField.getText());
			 try{
				game.buy(currentUser, creditCard);
				this.dispose();
				}
				catch (PaymentFailedException pfe){
					JOptionPane.showMessageDialog(null, "Something went wrong with the bank system. Retry Later", "Payment failed", JOptionPane.ERROR_MESSAGE);
				}
			
		}
		
	}
}
