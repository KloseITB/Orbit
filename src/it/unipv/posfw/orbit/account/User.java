package it.unipv.posfw.orbit.account;

import it.unipv.posfw.orbit.exceptions.*;
import it.unipv.posfw.orbit.library.Library;
import it.unipv.posfw.orbit.payment.*;

public class User {
	
	// Parameters
	
	private String nickname;
	private String password;
	private boolean isBanned;
	private boolean isLoggedIn;
	
	protected Library library;
	private double balance;
	
	// Constructors 

	public User(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
		isBanned = false;
		isLoggedIn = false;
		balance = 0;
	}
	
	// Class Methods
	
	public void addFunds(double amount, PaymentOptions paymentChosen) {
		SingletonPaymentManager paymentManager = SingletonPaymentManager.getInstance();
		try {
			balance += paymentManager.Pay(amount, paymentChosen);
		}
		catch(AmountNotValidException ave) {
			// say via UI that the inserted amount isn't valid
		}
		catch(PaymentMethodNotValidException pmve) {
			// say via UI that the payment method isn't valid
		}
	}
	
	public void removeFunds(double amount) {
		balance -= amount;
	}
	
	// Getter & Setter
	
	protected void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	protected boolean isBanned() {
		return isBanned;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}


}
