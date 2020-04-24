package BankException;


public class InvalidMenuSelectionException extends Exception {

	public InvalidMenuSelectionException() {
		super("The Selected Choice Is Not A Valid Option");
		
	}
	
	public InvalidMenuSelectionException(char choice) {
		super("The Selected Choice: "+choice+" Is Not A Valid Option"
				+ " Try again");
		
	}
}
