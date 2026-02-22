package it.unipv.posfw.orbit.payment;

public interface IPaymentMethod {
	public boolean pay(double amount);
}
