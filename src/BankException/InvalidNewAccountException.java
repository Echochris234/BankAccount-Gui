package BankException;


public class InvalidNewAccountException extends Exception {
	InvalidNewAccountException(){
		super("Error: The Account Exist Try Another Account Number");
	}
	
	public InvalidNewAccountException(int acct){
		super("Error: The Account: " +acct+" Exist Try Another Number");
		
	}
}
