package it.unipv.posfw.orbit.discount;


 /* RECORD TUTORIAL (In case you don't know how they work)
  * 
  * https://www.youtube.com/watch?v=gJ9DYC-jswo
  * 
  */
public record Discount(int percentage) {
	
	public double calculateDiscount (double currentPrice) {
		return currentPrice - (currentPrice * (percentage / 100.00));
	}
	

}
