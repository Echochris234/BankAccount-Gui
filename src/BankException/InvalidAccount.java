package BankException;


public class InvalidAccount extends Exception {
	
	InvalidAccount(){
		super("Invalid Account: The Account Does Not Exist");
	}
	
	public InvalidAccount(int acct){
		super("Invalid Account: The Account: " +acct+" Does Not Exist");
		
	}

}
