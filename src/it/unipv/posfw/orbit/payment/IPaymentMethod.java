package it.unipv.posfw.orbit.payment;

import it.unipv.posfw.orbit.exceptions.PaymentFailedException;

public interface IPaymentMethod {
	public boolean pay()  throws PaymentFailedException ;
}
