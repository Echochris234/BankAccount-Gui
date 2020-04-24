package Transactions;
public class WithdrawalSlip {
	private int account;
	private double withdrawalAmnt;
	private DateInfo transDate;
	private int renewal;
	

	// No Args constructor

	// Args Constructor
	public WithdrawalSlip(int acct, double amount, DateInfo date) {
		account = acct;
		withdrawalAmnt = amount;
		transDate = date;
		renewal =0;
	}
/*	public WithdrawalSlip(CheckClass check) {
		account = check.getAcct();
		withdrawalAmnt = check.getAmnt();
		transDate = check.getDate();
		renewal =0;
	}*/
	// sets account Num
	public void setAcctNum(int depositTo) {
		account = depositTo;
	}
	
	// returns acctNum
	public int getAcctNum() {
		return account;
	}
	
	public void setWith(double amount) {
			withdrawalAmnt= amount;
	}
	
	public double getWith() {
		return withdrawalAmnt;
	}
	
	public void setRenewal(int period) {
		renewal=period;
	}
	
	public int getRenewal() {
		return renewal;
	}
	
	public void setDate(int year, int month, int day) {
		transDate = new DateInfo(year,month,day);
	}
	
	public void setDate(DateInfo date) {
		transDate=date;
	}
	
	public DateInfo getDate() {
		return transDate;
		
		
	}

}
