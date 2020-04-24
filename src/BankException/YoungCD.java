package BankException;


public class YoungCD extends Exception{

public YoungCD(){
		
		super("The CD Account Has Not Matured");
	  

	 }
	
	public YoungCD(int acct)
	 {
		
		super("The CD Account :"+acct+" Has Not Matured");
	   
	 }

}
