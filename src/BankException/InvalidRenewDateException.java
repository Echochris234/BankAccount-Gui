package BankException;


public class InvalidRenewDateException extends Exception {
	
	public InvalidRenewDateException() {
		super("The Renew Period Chosen Is Not a Valid Choice");
	}
	
	public InvalidRenewDateException(int months) {
		super("The Renew Period of: "+ months+" Months Is Not a Valid Choice");
	}

}
