package BankException;


public class InvalidSocialException extends Exception {
	
	public InvalidSocialException() {
		super("The Social Security Number Does Not Exist");
	}
	
	public InvalidSocialException(String ssn) {
		super("The Social :#"+ssn+" Does Not Exist");
	}

}
