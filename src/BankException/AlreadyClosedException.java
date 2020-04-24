package BankException;

public class AlreadyClosedException extends Exception {

	public AlreadyClosedException() {
		super("The Account Is Already Closed");
	}
	
	public AlreadyClosedException(int acctNum) {
		
		super("The Account :"+acctNum+" Is Already Closed.");
	}
}
