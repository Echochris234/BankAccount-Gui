package AccountTypes;
import BankException.*;
import BehindScenes.*;
import Info.*;
import Transactions.*;

public class SavingsAccount extends Account {

	//private DateInfo maturityDate;
	// no args
	public SavingsAccount() {
		super();
	}

	// copy
	public SavingsAccount(Account acct) {
		super(acct);

	}

	// args
	public SavingsAccount(Depositor info, int acctNum, String type) {
		super(info, acctNum, type);

	}

	public SavingsAccount(Depositor depo, int acctNum, String type, double balance, boolean stat) {
		super(depo,acctNum,type,balance,stat);
	
	}

	// overridden to string
	public String toString() {
		String str;
		str = (super.toString());

		return str;
	}

	// impimented deposit
	public String makeDeposit(DepositSlip slip) {
		System.out.println("Savings Deposit");
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
			try {
				// if the deposit amount is less than 0
				if (withdraw <= 0) {

					throw new InvalidAmount(withdraw);
					// invalid deposit amount
					// deposit negative or zero
				} else if (getBalance() - withdraw < 0) {

					throw new InsufficientFunds(withdraw);
					// if balance after withdrawl is < $0

				} else {
					// else perform withdrawal
					// withdrawals value stored in minus
					withdraw(withdraw);
					addTrans(trans);
				}
			} catch (Exception e) {
				trans.setFailReason(e.getMessage());
				trans.setSuccessIndi(false);
				addTrans(trans);
			}

		} catch (ErrorClosedAcct e) {
			trans.setFailReason(e.getMessage());
			trans.setSuccessIndi(false);
			addTrans(trans);
		}
// returns what happened
		return printReciept(oldBlnce, trans);
		// return reason;
	}
}
