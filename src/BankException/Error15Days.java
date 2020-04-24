package BankException;


public class Error15Days extends Exception {

	public Error15Days(){
		
		super("The Transaction To The CD Exceeds "
				+ "15 Day Grace Period");
	  

	 }
	
	public Error15Days(int acctNum,int days)
	 {
	    super("Error: The CD Account: #"
				+ acctNum+ " Exceeds 15 Day Grace Period by "
				+days+ " Days");
	    
	 }
}
