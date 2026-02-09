package it.unipv.posfw.orbit.payment;

import it.unipv.posfw.orbit.exception.PaymentFailedException;

public interface IPaymentMethod {
	public boolean pay()  throws PaymentFailedException ;
}
