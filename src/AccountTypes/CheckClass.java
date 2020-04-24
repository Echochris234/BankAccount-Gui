package AccountTypes;
import Transactions.*;

//import java.util.Calendar;

public class CheckClass {
	private int account;
	private double checkAmnt;
	private DateInfo transDate;

	public CheckClass(int acct, double check, DateInfo date) {
		account = acct;
		checkAmnt = check;
		transDate = new DateInfo(date);
	}

	public DateInfo getDate() {
		return transDate;

	}

	public double getAmnt() {
		return checkAmnt;
	}

	public int getAcct() {
		return account;
	}

	public void setWith(double amount) {
		checkAmnt = amount;
	}

	public double getWith() {
		return checkAmnt;
	}

	public void setDate(DateInfo date) {
		transDate = date;
	}

}
