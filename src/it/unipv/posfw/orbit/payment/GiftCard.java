package it.unipv.posfw.orbit.payment;

public class GiftCard {
	
	// Parameters
	
	private String code;
	private double value;
	
	// Constructors
	
	public GiftCard(String code, double value) {
		this.code = code;
		this.value = value;
	}

	
	// Getters and Setters
	
	public String getCode() {
		return code;
	}

	public double getValue() {
		return value;
	}

}
