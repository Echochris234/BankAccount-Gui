package BankException;

import java.text.DecimalFormat;

public class InvalidAmount extends Exception {
	static DecimalFormat dollar = new DecimalFormat("$#,###.##");
	public InvalidAmount(){
	    super("Error: Invalid Transaction Amount Perform Transactions");

	 }
	
	public InvalidAmount(double amount)
	 {
	    super("Error: Invalid Transaction, Amount of: "+( dollar.format(amount)) +" Is a Negative or Zero Value");
	 }

}
