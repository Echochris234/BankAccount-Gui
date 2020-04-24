package Transactions;
public class DepositSlip {
	private int acctNum;
	private double depositAmnt;
	private DateInfo transDate;
	private int renewal;
	
	// No Args constructor
	public DepositSlip() {
		acctNum = 0;
		depositAmnt = 0.0;
		transDate= new DateInfo();
		renewal = 0;
		
	}
	// Args Constructor
	public DepositSlip(int acct, double amount, DateInfo date) {
		acctNum = acct;
		depositAmnt = amount;
		transDate = new DateInfo(date);
		renewal =0;
	}
	
	
	// sets account Num
	public void setAcctNum(int depositTo) {
		acctNum = depositTo;
	}
	
	// returns acctNum
	public int getAcctNum() {
		return acctNum;
	}
	
	public void setDepo(double amount) {
			depositAmnt= amount;
	}
	
	public double getDepo() {
		return depositAmnt;
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
