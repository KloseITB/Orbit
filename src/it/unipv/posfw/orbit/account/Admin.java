package it.unipv.posfw.orbit.account;

//IMPORTANT: CHECK YOUR PART OF CODE AND GET RID OF THIS CLASS SINCE IT WILL BE DELETED
public class Admin extends User {
	// constructors

	// constructors for new Admin
	public Admin(String nickname, String password) { 
		super(nickname, password);
	}
	
	// costructor for already existing Admin
	public Admin(int id, String nickname, String password, double balance) {
		super(id, nickname, password, balance);
	}
	
	// methods
	

	
	// created a different method do underline the difference between
	// banning a normal user and a publisher who
	// doesn't respect the platform's Term Of Service
	
	// -- UNIMPLEMENTED --
	public void permaBanPublisher (Publisher publisher) {
		publisher.setBanned(true, this);
		// delete all the games published by the banned publisher from the database
	}

	
	
	
}
