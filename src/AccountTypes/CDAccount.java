package AccountTypes;
import java.util.Calendar;


import BankException.*;
import BehindScenes.*;
import Info.*;
import Transactions.*;

public class CDAccount extends Account {
	// private Calendar maturityDate = Calendar.getInstance();

	// termOfCD int
	// and maturityDate dateinfo object
	//private int termOfCD;
	//private DateInfo maturityDate;

	// no args constructor
	public CDAccount() {
		//termOfCD = 0;
		//maturityDate = new DateInfo();
	}

	// args constructor
	public CDAccount(Depositor info, int acctNum, String type, DateInfo date) {
		super(info, acctNum, type);
		// maturityDate = date;

		setDate(date);

	}

	// copy consturctor
	public CDAccount(Account account) {

		super(account);

		//maturityDate = new DateInfo(account.getDate());

		//termOfCD = maturityDate.getMatureLength();

	}
	
	
	

	public CDAccount(Depositor depo, int acctNum, String type, DateInfo date, double balance, boolean stat) {
		super(depo, acctNum, type,balance,stat);
		
		setDate(date);
	}

	// sets date info to maturityDate
	public void setDate(DateInfo dates) {
		super.setDate(dates);
		//maturityDate = new DateInfo(dates);
	}

	// returns copy of maturityDate
	public DateInfo getDate() {
		return new DateInfo(super.getDate());
		//return new DateInfo(maturityDate);
	}

	// over rides toString() addding the matureDate
	public String toString() {
		String str;
		str = (super.toString() + matureToString());

		return str;
	}

	// CDAccount deposit
	public String makeDeposit(DepositSlip slip) {
		System.out.println("Deposit method within the CDAccount Class");
		// String str;
		double depot = slip.getDepo();
		double oldBlnce = getBalance();

		DateInfo date = new DateInfo(slip.getDate());
		Transactions trans = new Transactions();
		// if the account on the slip is true start deposit
		// finds account positon

		// fill transaction using info from deposit slip
		trans.setDate(date);
		trans.setTransType("Deposit");
		trans.setTransAmnt(depot);

		// if account status is open do
		try {
			if (getAcctStats() != true) {
				throw new ErrorClosedAcct(slip.getAcctNum());
			}
			try {
				// if the deposit amount is less than 0
				if (depot <= 0) {
					throw new InvalidAmount(depot);

				} else {// else if depsoit amount is valid

					if (checkCD(date) == -1) {

						throw new YoungCD(slip.getAcctNum());

						// the account has not matured yet

						// perfomrs the deposit other is 0

					} else {
						// if check15Days reutnrs less than or equal to 15

						if (check15Days(date) <= 15) {
							// performs deposit
							deposit(depot);
							
							date.setMatureLength(slip.getRenewal());
							// sets date to account
							setDate(date);

							// adds transaction
							addTrans(trans);
							// else error message missed 15 day window
						} else {

							throw new Error15Days(getAcctNum(), (check15Days(date)));

						}
					}

				}
			} catch (Exception e) {
				trans.setFailReason(e.getMessage());
				trans.setSuccessIndi(false);
				addTrans(trans);
			}
		} catch (ErrorClosedAcct e) {
			// the account is closed and no transactions allowed
			trans.setFailReason(e.getMessage());
			trans.setSuccessIndi(false);
			addTrans(trans);

		}
		// return the reciept
		return printReciept(oldBlnce, trans);
	}

	public String makeWithdrawal(WithdrawalSlip slip) {

		double oldBlnce = getBalance();
		double withdraw = slip.getWith();
		DateInfo date = new DateInfo(slip.getDate());

		// sets up transaction
		Transactions trans = new Transactions();
		// fill using info from deposit slip
		trans.setTransType("Withdrawal");
		trans.setTransAmnt(withdraw);
		trans.setDate(date);
		try {
			// if account status is open do
			if (getAcctStats() != true) {
				throw new ErrorClosedAcct(slip.getAcctNum());
			}

			// if the deposit amount is less than 0
			try {
				if (withdraw <= 0) {
					// invalid deposit amount
					throw new InvalidAmount(withdraw);

					// else if balance after withdrawal is less than 0
				} else if (getBalance() - withdraw < 0) {
					// error
					throw new InsufficientFunds(withdraw);

					// checks to see if the account is a CD
				}
				// if checkCD returns -1 the account is not mature
				if (checkCD(date) == -1) {
					throw new YoungCD(getAcctNum());

					// else it matured
				} else {
					// if check 15 days is less than or equal to 15
					if (check15Days(date) <= 15) {

						// performs withdraw
						withdraw(withdraw);
						
						date.setMatureLength(slip.getRenewal());
						// sets date to account
						setDate(date);
						
						//maturityDate.setMatureLength(slip.getRenewal());
						// adds transaction
						addTrans(trans);

						// else missd 15 day window
					} else {

						throw new Error15Days(getAcctNum(), (check15Days(date)));

					}
				}

			} catch (Exception e) {
				// the account is closed and no transactions allowed
				trans.setFailReason(e.getMessage());
				trans.setSuccessIndi(false);
				addTrans(trans);
			}

		} catch (ErrorClosedAcct e) {
			trans.setFailReason(e.getMessage());
			trans.setSuccessIndi(false);
			addTrans(trans);
		}

		// returns reciept
		return printReciept(oldBlnce, trans);
		// return reason;

	}

	// checks to see if the transaction is withi 15 days
	private int check15Days(DateInfo date) {
		// creates cd calender stores date stored in CD
		int totalCD, cdMonth, cdDay, cdYear;
		DateInfo maturityDate = new DateInfo(super.getDate());
		int totalTrans, tMonth, tDay, tYear;

		int mature= maturityDate.getMatureLength();
		Calendar cD = Calendar.getInstance();
		cD.clear();
		cD.set(Calendar.MONTH, maturityDate.getMonth());
		cD.set(Calendar.YEAR, maturityDate.getYear());
		cD.set(Calendar.DATE, maturityDate.getDay());

		// adds the term to compare the dates

		cD.add(Calendar.MONTH, +mature);
		cdMonth = 30 * cD.get(Calendar.MONTH);
		cdDay = cD.get(Calendar.DATE);
		cdYear = 365 * cD.get(Calendar.YEAR);
		totalCD = cdMonth + cdDay + cdYear;

		tMonth = 30 * date.getMonth();
		tDay = date.getDay();
		tYear = 365 * date.getYear();
		totalTrans = tMonth + tDay + tYear;

		return (totalTrans - totalCD);

	}

	// CHECK CD METHOD CHECK IF THE CD ACCOUNT IS VALID
	public int checkCD(DateInfo date) {
		
		DateInfo maturityDate = new DateInfo(super.getDate());
		int mature= maturityDate.getMatureLength();
		// CREATES CALENDAR CLASS AND SETS DATE
		// STORED IN date for trasaction
		Calendar trans = Calendar.getInstance();
		trans.clear();
		trans.set(Calendar.MONTH, date.getMonth());
		trans.set(Calendar.YEAR, date.getYear());
		trans.set(Calendar.DATE, date.getDay());

		// gets the date of the CD account to compare
		Calendar cD = Calendar.getInstance();
		cD.clear();
		cD.set(Calendar.MONTH, maturityDate.getMonth());
		cD.set(Calendar.YEAR, maturityDate.getYear());
		cD.set(Calendar.DATE, maturityDate.getDay());

		/*
		 * //CREATES A CALENDAR CLASS WITH TODAYS DAY Calendar today =
		 * Calendar.getInstance(); today.clear(); today.set(Calendar.YEAR, 2019);
		 * today.set(Calendar.MONTH, Calendar.MARCH); today.set(Calendar.DATE, 20);
		 */
		// ADDS TO THE MONTH OF CD THE LENGTH OF MATURITY
		cD.add(Calendar.MONTH, +mature);

		// CHECKS TO SEE IF THE CD AGED TO TODAYS DATE TO PERFORM OPERATIONS
		// if CD matured DATE IS BEFORE today or after RETURN -1
		if (trans.after(cD) || trans.equals(cD)) {

			return 1;
			// else if the mature date is after today RETURN -1
			// false
		} else
			return -1;
	}

	// returns the date cd matures based on string passed
	public String matureToString() {
		DateInfo maturityDate = new DateInfo(super.getDate());
		int termOfCD= maturityDate.getMatureLength();
		System.out.println("The super mature date is"+ super.getDate()+" The copy mature date is "+termOfCD);
		String str;
		int yeer, mnth, dey;
		// creates a calendar to hold mature date
		Calendar matureDate = Calendar.getInstance();
		matureDate.clear();

		// sets the date of current Cd
		matureDate.set(Calendar.MONTH, maturityDate.getMonth());
		matureDate.set(Calendar.YEAR, maturityDate.getYear());
		matureDate.set(Calendar.DATE, maturityDate.getDay());

		// passes year month and day to variables
		yeer = matureDate.get(Calendar.YEAR);
		mnth = matureDate.get(Calendar.MONTH);
		dey = matureDate.get(Calendar.DATE);

		// creates date info
		DateInfo mature = new DateInfo(yeer, mnth, dey);

		// if the month in mature == 0
		if (mature.getMonth() == 0)
			// set the month to 1
			mature.setMonth(1);

		// mature to string
		str = ("Updated On: " + mature);

		// ADDS TO THE MONTH OF matureDate THE LENGTH OF MATURITY
		matureDate.add(Calendar.MONTH, +termOfCD);

		// passes updated year month and day to variables
		int myeer = matureDate.get(Calendar.YEAR);
		int mmnth = matureDate.get(Calendar.MONTH);
		int mdey = matureDate.get(Calendar.DATE);

		// update mature dateInfo with new info
		mature = new DateInfo(myeer, mmnth, mdey);

		// if the month == 0 set the month to 1
		if (mature.getMonth() == 0)
			mature.setMonth(1);
		// str = str +("\tMatures On: "+mature+"\t Term Of CD: "+ termOfCD+"\n");

		// adds the mature date to str
		str = str + String.format("%nMatures On: %s  %n%s %s %n", mature, "Term Of CD: ", termOfCD);

		// return str
		return str;

	}

}