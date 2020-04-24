package BankException;

import java.text.DecimalFormat;

public class ErrorDeleteAcct extends Exception{
	static DecimalFormat dollar = new DecimalFormat("$#,###.##");
	
	public ErrorDeleteAcct() {
		super("The Account Is open,"
				+ " Close the Account before Deleting");
	}
	
	public ErrorDeleteAcct(int acctNum) {
		super("The Account :"+acctNum+" Is Open."
				+ " \n Close the Account before Deleting");
		
	}
	
	public ErrorDeleteAcct(int acctNum, double balance) {
		super("There is a balance of: "+ ( dollar.format(balance))
				+ " In Account: #"+acctNum
				+ "\n Clear the Balance on the Account in order to delete");
	}
}
