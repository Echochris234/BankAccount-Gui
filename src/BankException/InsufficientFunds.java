package BankException;

import java.text.DecimalFormat;


public class InsufficientFunds extends Exception {
	

		static DecimalFormat dollar = new DecimalFormat("$#,###.##");
		/**This constructor uses a generic
	    error message. */

		public InsufficientFunds()
	 {
	    super("Error: Insuffient Funds To Perform Withdrawal");
	 }

	 /**
	    This constructor specifies the bad starting
	    balance in the error message.
	    @param The bad starting balance.
	 */

	 public InsufficientFunds(double amount)
	 {
	    super("Error: Insuffient Funds To Perform Withdrawal of :" +( dollar.format(amount)));
	 }

	}
