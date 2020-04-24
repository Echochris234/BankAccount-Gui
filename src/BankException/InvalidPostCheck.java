package BankException;


public class InvalidPostCheck extends Exception {

	public InvalidPostCheck(){
		
		super("The Check Is Post-Dated: Try Another Time");
	  

	 }
	
	public InvalidPostCheck(int acct, String date)
	 {
	    super("Error: The check is post-dated for"
				+ " account: #" + acct
				+ ". valid on: "+ date);
	 }
}
