package AccountTypes;
import BankException.*;
import BehindScenes.*;
import Info.*;
import Transactions.*;

import java.util.Calendar;

public class CheckingAccount extends Account { 


	
	// no args
	public CheckingAccount() {
		super();
	}

	// copy constructor
	public CheckingAccount(Account acct) {
		super(acct);

	}

	// args constructor
	public CheckingAccount(Depositor info, int acctNum, String type) {
		super(info, acctNum, type);
	}


	public CheckingAccount(Depositor depo, int acctNum, String type, double balance, boolean stat) {
	super(depo,acctNum,type,balance,stat);
	}

	// overridden to string
	public String toString() {
		String str;
		str = (super.toString());

		return str;
	}

	// implimented deposit
	public String makeDeposit(DepositSlip slip) {

		double depot = slip.getDepo();
		DateInfo date = new DateInfo(slip.getDate());
		double oldBlnce = getBalance();

		// if the account on the slip is true start deposit
		// finds account positon
		Transactions trans = new Transactions();
		// fill transaction using info from deposit slip
		trans.setDate(date);
		trans.setTransType("Deposit");
		trans.setTransAmnt(depot);
		try {
			// if account status is open do
			if (getAcctStats() != true) {
				throw new ErrorClosedAcct(slip.getAcctNum());
			}else {
				// if the deposit amount is less than 0
				if (depot <= 0) {
					// invalid deposit amount
					// sets fail reason
					throw new InvalidAmount(depot);

				} else {
					// the account is valid to deposit
					deposit(depot);
//					addTrans(trans);
				}
			} 
		}catch (Exception e) {

				// trans.setFailReason("The Deposit is a negative or $0 Transaction");
				trans.setFailReason(e.getMessage());
				trans.setSuccessIndi(false);
			}finally {
				addTrans(trans);
		}

		// returnr reason
		return printReciept(oldBlnce, trans);
	}



	// makes withdrawal
	public String makeWithdrawal(WithdrawalSlip slip) {
		double oldBlnce = getBalance();
		double withdraw = slip.getWith();
		DateInfo date = new DateInfo(slip.getDate());

		// if the account on the slip is true start deposit
		// finds account positon
		Transactions trans = new Transactions();
		// fill using info from deposit slip
		trans.setTransType("Withdrawal");
		trans.setTransAmnt(withdraw);
		trans.setDate(date);
		// if returned pos is greater or equal to 0
		// begin deposit
try {
		// if account status is open do
		if (getAcctStats() != true) {
			throw new ErrorClosedAcct(slip.getAcctNum());
		}	
				// if the deposit amount is less than 0
				if (withdraw <= 0) {

					// deposit negative or zero
					throw new InvalidAmount(withdraw);

				} else if (getBalance() - withdraw < 0) {

					throw new InsufficientFunds(withdraw);

				} else {
					// if balance is under 2500
					// add a 1.50 charge
					// to current withdrawal
					if (getBalance() < 2500) {
						System.out.println("Balance under 2500");
						// adds 1.50 to withdraw and sets new value
						Transactions fee = new Transactions();
						// fill using info from deposit slip
						fee.setTransType("Fee");
						fee.setTransAmnt(1.50);
						fee.setDate(date);

						// sets withdraw amount to trans
						//fee.setTransAmnt(1.50);

						addTrans(fee);
						// withdraws amount
						withdraw = withdraw + 1.50;
						withdraw(withdraw);
		//				addTrans(trans);
					}else {
						withdraw(withdraw);
			//			addTrans(trans);
				}

			}
		} catch (Exception e) {
			trans.setFailReason(e.getMessage());
			trans.setSuccessIndi(false);
			
		}finally {
			addTrans(trans);

	} 

// returns what happened
		return printReciept(oldBlnce, trans);
	}
		
		// return reason;

	// clear check passed a CheckClass object
	public String clearCheck(CheckClass check) {
		double oldBlnce = getBalance();
		// date info gets passed date on check
		DateInfo checkDate = new DateInfo(check.getDate());
		double withdraw = check.getAmnt();
		// new date object for today
		// DateInfo tooday = new DateInfo (2019, 03, 20);

		// new transaction object
		Transactions trans = new Transactions();

		// fill using info from deposit slip
		trans.setTransType("Clear Check");
		trans.setTransAmnt(withdraw);
		trans.setDate(checkDate);
		// if returned pos is greater or equal to 0
		// begin deposit
					double fee = 2.50;
					Transactions eFee = new Transactions();
					eFee.setTransType("Fee");
					eFee.setTransAmnt(fee);
					eFee.setDate(checkDate);
		// if the type of the account is checking
		// if it is open
try {		
		
		if (getAcctStats() != true) {
			throw new ErrorClosedAcct(check.getAcct());
		}else {
			// new calendar object
			Calendar today = Calendar.getInstance();
			// clear
			today.clear();
			// fill in with todays info
			today.set(Calendar.YEAR, 2019);
			today.set(Calendar.MONTH, Calendar.MARCH);
			today.set(Calendar.DATE, 20);

			// calendar for check date
			Calendar checkDay = Calendar.getInstance();
			checkDay.clear();
			checkDay.set(Calendar.YEAR, checkDate.getYear());
			checkDay.set(Calendar.MONTH, (checkDate.getMonth() - 1));
			checkDay.set(Calendar.DATE, checkDate.getDay());
			
				// IF CHECK IS AFTER TODAY RETURNS reason -3 FOR TOO EARLY
				if (checkDay.after(today)) {
					throw new InvalidPostCheck(check.getAcct(), checkDate.toString());
					
					// ELSE IF PASSES SIX MONTHS METHOD ==0
					// VALID -2 ITS TOO LATE
				} else if (checkSixMonths(checkDay, today) == 0) {
					throw new InvalidExpiredCheck(check.getAcct(), checkDate.toString());
					
				} else {// successful check cleared

				

					/**
					 * WithdrawalSlip withdrawal = new WithdrawalSlip(getAcctNum(), withdraw,
					 * checkDate);
					 */
					if (check.getAmnt() <= 0) {

						throw new InvalidAmount(withdraw);
					}

					else if (getBalance() - withdraw < 0) {
						// if balance after withdrawl is < $0
						withdraw(fee);
						addTrans(eFee);
						throw new InsufficientFunds(withdraw);
						// trans.setFailReason("Insuffienct Funds in the Account"
						// + "OverDraft Fee: "+ dollar.format(fee));

						// checks to see if the account is a CD
						// cReason = makeWithdrawalCheck(withdrawal);

					} else {
						if (getBalance() < 2500) {
	
						System.out.println("Balance under 2500");
						// adds 1.50 to withdraw and sets new value
						
						// fill using info from deposit slip
						eFee.setTransType("Fee");
						eFee.setTransAmnt(fee);
						eFee.setDate(checkDate);

						// sets withdraw amount to trans
						

						//addTrans(eFee);
						// withdraws amount
						withdraw(fee);
						addTrans(eFee);
					}
					// if not under 2500 perform withdrawal
					withdraw(withdraw);
					//addTrans(trans);
				}

			}
		}
	}catch (Exception e) {
				
				trans.setFailReason(e.getMessage());
				trans.setSuccessIndi(false);
				//addTrans(trans);

			}finally {
				addTrans(trans);
			}
		return printReciept(oldBlnce, trans);

	}

	// METHOD PASSES 2 CALENDAR OBJECTS COMPARES
	// FOR CLEAR CHECK TO TEST IF THE CHECK IS BEFORE
	// 180 DAY LIMIT
	private int checkSixMonths(Calendar check, Calendar today) {
		// SUBTRACTS 6 MONTHS FROM TODAY CALENDAR
		today.add(Calendar.MONTH, -6);
		// IF CHECK IS 180 BEFORE TODAY RETURN 0 (Too late)
		if (check.before(today)) {
			return 0;
			// ELSE IF AFTER RETURN 1
		} else
			return 1;
	}

}
