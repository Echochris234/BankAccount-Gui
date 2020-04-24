package BankException;


public class ErrorClosedAcct extends Exception {
		
		public ErrorClosedAcct() {
			super("The Account Is Closed,"
					+ " Transactions Cannot Be Performed");
		}
		
		public ErrorClosedAcct(int acctNum) {
			super("The Account :"+acctNum+" Is Closed."
					+ " \n Transactions Cannot Be Performed");
		}
}
