package BankException;


public class InvalidExpiredCheck extends Exception{

	
	public InvalidExpiredCheck(){
		
		super("The Check Is Expired: Error");
	  

	 }
	
	public InvalidExpiredCheck(int acct, String date)
	 {
	    super("Error: The check is expired"
				+ " for account: #" + acct
				+ " Expired on: "+ date);
	 }
}
