package it.unipv.posfw.orbit.payment;

import it.unipv.posfw.orbit.account.User;
import it.unipv.posfw.orbit.database.FacadeDB;
import it.unipv.posfw.orbit.exception.PaymentFailedException;
import it.unipv.posfw.orbit.game.Game;

public class DebitCard implements IPaymentMethod {

	@Override
	public void pay(int id, User user) throws PaymentFailedException {
		// Since we would have to connect to the debit card's bank system, which goes beyond our scope,
		// we will simulate the possibility of an error occurring by using Math.random()
		
		// We will simulate a 4% error rate
		final double ERROR_RATE = 0.04;
		double randomValue = Math.random();
		
		if(randomValue >= ERROR_RATE) {
			Game game = FacadeDB.getInstance().getGame(id);
			user.getLibrary().addGame(game);
		}
		else throw new PaymentFailedException();
	}

}
