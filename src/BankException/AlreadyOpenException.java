package BankException;

public class AlreadyOpenException extends Exception {


	public AlreadyOpenException() {
		super("The Account Is Already Open");
	}
	
	public AlreadyOpenException(int acctNum) {
		
		super("The Account :"+acctNum+" Is Already Open.");
	}
}
