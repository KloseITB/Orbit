package it.unipv.posfw.orbit.payment;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.exception.PaymentFailedException;

public interface IPaymentMethod {
	public void pay(int id, User user)  throws PaymentFailedException ;
}
