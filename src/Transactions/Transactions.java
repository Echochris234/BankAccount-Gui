package Transactions;
//transaction class to hold info
import java.io.Serializable;
import java.text.DecimalFormat;

public class Transactions implements Serializable {
	//type of transactono
	private String transType;
	//transaction amount
	private double transAmnt;
	//date info for transaction date
	private DateInfo transDate;
	//indicator for sucesss of transaction
	private boolean successIndicator;
	//reason for failre
	private String failReason;
	DecimalFormat dollar = new DecimalFormat("$#,###.##");

//nor args constructor
	public Transactions(){
		transType="Create"
				+ "";
		transAmnt=0;
		transDate= new DateInfo();
		successIndicator=true;
		failReason="";

	}
	//args constructor
	public Transactions(String type, double amnt, DateInfo transDay) {
		
		
		transType=type;
		transAmnt=amnt;
		transDate= new DateInfo( transDay);
		successIndicator=true;
		failReason="";
		
	}
	// copy constructor
	public Transactions(Transactions myTrans) {
		transType= myTrans.transType;
		transAmnt = myTrans.transAmnt;
		transDate = new DateInfo(myTrans.transDate);
		successIndicator = myTrans.successIndicator;
		failReason = myTrans.failReason;
		
		
	}
		public Transactions(String transType2, double transAmnt2, DateInfo transDate2, boolean valid, String failReason2) {
			transType= transType2;
			transAmnt = transAmnt2;
			transDate = new DateInfo(transDate2);
			successIndicator = valid;
			failReason = failReason2;

	}
	public Transactions(String transType2, double transAmnt2, DateInfo transDate2, boolean valid) {
		transType= transType2;
		transAmnt = transAmnt2;
		transDate = new DateInfo(transDate2);
		successIndicator = valid;
		failReason="";
		}
	//sets date
	public void setTransDate(int year, int month, int day ) {
		//successIndicator=true;
			transDate= new DateInfo(year,month,day);
	}
	
	public void setDate(DateInfo date) {
		transDate=date;
	}
	
	//gets date information
	public DateInfo getTransDate() {
		return new DateInfo(transDate);
	}
	
		//returns trasaction type
	public String getTransType() {
		return transType;
	}
	
	//sets transaction type
	public void setTransType(String trans) {
		transType=trans;
		
	}
	//sets transaction amount
	public void setTransAmnt(double amnt) {
		transAmnt=amnt;
	}
	//gets transaction amount
	public double getTransAmnt() {
		return transAmnt;
	}
	
	// set fail reason
	public void setFailReason(String reason) {
		failReason=reason;
	}
	//returns fail reason
	public String getFailReason() {
		return failReason;
	}
	// gets success indicator
	public boolean getSuccessIndi() {
		return successIndicator;
	}
	//sets success indicator
	public void setSuccessIndi(boolean flag) {
		successIndicator = flag;
		
	}
	
	// returns contetns of the transaction as a string
	public String toString() {
		//decimal format for format print
		//DecimalFormat dollar = new DecimalFormat("$#,###.##");
		String str; // holds the string
		DateInfo date = new DateInfo(getTransDate()); // holds date info
		// formats string to be printed stores in str
			str = String.format("%-1s/%-1s/%-10s %-18s",
					date.getMonth(),date.getDay(),
					date.getYear(), getTransType());
			// if the success indi is true
			if(getSuccessIndi()==true) {
				// if it is not a 0 balance
				if(getTransAmnt()!=0)
					// concat the amount on to str
					str =	str + String.format("%-15s", dollar.format(getTransAmnt()));
				// else if success indi is false concat amount and fail reason
			} else {
				str = str+ String.format("%-10s %-1s", dollar.format(getTransAmnt()),
						getFailReason());
				}
	// return str
		return str;
	}		

}