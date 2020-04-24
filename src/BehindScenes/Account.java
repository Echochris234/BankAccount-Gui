package BehindScenes;

import AccountTypes.*;
import Info.*;
import Transactions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
//import java.util.Calendar;
import java.text.DecimalFormat;

public abstract class  Account implements Serializable {
	// HOLDS DEPOSTITOR OBJECT ACCTNUM, BALANCE
	// ACCT TYPE AND DATEINFO CLASS
	private Depositor info;
	private int acctNum;
	private double balance;
	private String type;
	private DateInfo date;
	private boolean accountStatus;
	private int termOfCD;
	//TransFile tFile;
	// private Transactions transactions;
	//private ArrayList<Transactions> transactions;
	private Transactions[] trans;
	private final  int TRANS_SIZE = 100;
	private int transInAcct;
	
	DecimalFormat dollar = new DecimalFormat("$#,###.##");


	
	// NO ARGS CONSTRUCTOR
	public Account() {
		info = new Depositor();
		acctNum = 0;
		balance = 0.0;
		type = "";
		//date = new DateInfo();
		accountStatus = true;
		//transactions = new ArrayList<>();
		trans = new Transactions [TRANS_SIZE];
		transInAcct=0;

	}


	// ARGS CONSTRUCTOR
	public Account(Depositor person, int acct, String acctheld) {
		info= new Depositor(person);
		acctNum = acct;
		balance = 0;
		type=(acctheld);
		date = new DateInfo();
		accountStatus = true;
		trans = new Transactions [TRANS_SIZE];
		transInAcct=0;
		//transactions = new ArrayList<>();
	}

	// duplicate 
	public Account(Account myAcct) {
		info = new Depositor(myAcct.info);
		acctNum = myAcct.acctNum;
		balance = myAcct.balance;
		type = myAcct.type;
		date = new DateInfo(myAcct.getDate());
		accountStatus = myAcct.accountStatus;
		transInAcct=myAcct.transInAcct;
		trans = myAcct.trans;
		//trans = myAcct.trans;
		//transactions = myAcct.transactions;
		//transactions = myAcct.transactions= new ArrayList<Transactions>();

		
		
	}
	// method for reading from binary file
	public Account(Depositor depo, int acctNum, String type, double balance, boolean stat) {
		info= new Depositor(depo);
		this.acctNum = acctNum;
		this.balance = balance;
		this.type=(type);
		date = new DateInfo();
		accountStatus = stat;
		}


public void setStatus(boolean valid) {
	accountStatus =valid;
}
	// checks to see if the account is a cd
	public boolean equalsCD() {
		// get type and compare to string passed
		// if equals
		if(getType().equalsIgnoreCase("CD"))
			// return true
			return true;
		// else false
		else
			return false;
	}
	
	public boolean equalsSaving() {
		if(getType().equalsIgnoreCase("Saving"))
			return true;
		else
			return false;
	}
	
	public boolean equalsChecking() {
		if (getType().equalsIgnoreCase("Checking"))
			return true;
		else
			return false;
	}
	// checks to see if account numbers are equal
	public boolean equalsAcctNum(int accountNum) {
		
		if(getAcctNum()==(accountNum))
			return true;
		else
			return false;
	
	}
	
	// compares depositor objects by social
	public boolean equalsSsn(String ssn) {
		// if socials are equal return true
		//Depositor Ssn = new Depositor(cuenta.getInfo());//might remove
		if(getDepositor().getSocial().equals(ssn))
			return true;
		//else return false
		else
			return false;
	}
	
	// compares name object by first and last name
	public boolean equalsName(Account cuenta) {
		// passes name object in cuenta to temp name
		//Name tempName = new Name(cuenta.getInfo().getName());//deeper security?
		
		if(getDepositor().getName().equalsName(cuenta.getDepositor().getName()))
			return true;
		else 
			return false;
	}
	// turns account class to string
	public String toString() {
		// refrences
		String status;
		String str;
		// gets name object stored in depositor
		Name name = getDepositor().getName();
		// gets social
		Depositor ssn = getDepositor();
		
		// the account is open
		if(getAcctStats()==true)
			// set status to open
			status = "Open";
		else // else closed
			status = "Closed";
		 // decimal format for printing
		DecimalFormat dollar = new DecimalFormat("$#,###.##");
		// string format to str
		
		str =String.format("%-13s %-5s %-12s %-12s %-13s %1s %n %n", 
								name.toString(),ssn.toString(),getAcctNum(),
								dollar.format(getBalance()), getType(), status);
			return str;// return str
	}
	// returns account status open or closed
	public boolean getAcctStats() {
		return accountStatus;
	}
	
/*	// adds a transaction to the array
	public void addTrans(Transactions moves) {
		transactions.add(moves);

	}
*/
	
	public int getTransAmnt() {
		return transInAcct;
	}
	 
	public void addTrans(Transactions moves) {
		
		trans[transInAcct]=moves;
		transInAcct++;
		System.out.println("There are "+transInAcct+" transactions");


	}
	public void serializeTransaction() throws FileNotFoundException,IOException {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream("t"+acctNum+".dat");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(trans);
			System.out.println("total trans"+transInAcct);
			
			System.out.println("You have printed the object");
		}
		finally {
			if(objectOutputStream != null) {
			objectOutputStream.close();
			}
		}
		
	}
	
	
	/**
	 *  deserailize the Transactions
	 * @return the transactions as a string
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NullPointerException
	 */
	
	public String deserializeTransaction() throws FileNotFoundException,IOException,
										ClassNotFoundException,NullPointerException {
		// creates a a fileinputstream
		// creates objectinputstream
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		String str="";
		// creates an array of transactions
		Transactions[] newTrans;
		try {
			// opens a the file pertaining to the account
			fileInputStream = new FileInputStream("t"+acctNum+".dat");
			// attaches inputstream to objectinputstream
			objectInputStream = new ObjectInputStream(fileInputStream);
			
			// new Trans equals seralized object read in and type cast
		newTrans = (Transactions[]) objectInputStream.readObject(); 	
			System.out.println("You have read the object");
			// sets transInAcct to 0
			transInAcct=0;
			// from 0 to max trans size
			for(int i = 0;i<TRANS_SIZE;i++)
			{
				// if the trans[i] is not null
				if(newTrans[i]!=null) {
					// pass the transaction to string on str
					str =str+String.format("%-1s %n",newTrans[i].toString());
					//str=str+newTrans[i].toString();
					//System.out.println(str);

					// increase transInAcct by 1
				transInAcct++;
				}
			}
			// catch null pointer
		}catch(NullPointerException e) {
			e.printStackTrace();
			
		}
		// finally
		finally 
		{
			// if objectInputStream is != null
			if(objectInputStream != null) 
			{
				// close te file
				objectInputStream.close();			
			}
			//temp = str;
		}
		
		// return str
		return(str);
		
		
		
	}
	// returns transaction at index
	public Transactions getTrans(int i) {
		return trans[i];
	}

	// closes account returns int
	public boolean closeAccount() {

		if (getAcctStats() == true) {
			// set to false(closes account)
			// return boolean saying its closed
			return accountStatus = false;
		}
		// indicates account is already closed
		else
			return true;
	}

	
	
	// opens account
		public boolean openAccount() {
			// creates transaction instance and fills date and type
	
			// if account == false(closed)
			if (getAcctStats() == false) {
				// sets to true(opens account
				// returns boolean saying its open
				return accountStatus=true;
			
			}
			// indicates account is already open
			else
				
				return false;

		}
	// setacctNum sets acctNum to int passed to method
	public void setacctNum(int actNum) {
		acctNum = actNum;
	}

	// setacctNum sets acctNum to int passed to method
	public void setacctNum(String actNum) {
		acctNum = Integer.parseInt(actNum);
	}

	// getacctNum returns int value stored in acctNum
	public int getAcctNum() {
		return acctNum;
	}

	// setBalance sets balance to double passed to method
	public void setBalance(double blnce) {
		balance = blnce;
	}

	// setBalance sets balance to double passed to method
	public void setBalance(String blnce) {
		balance = Double.parseDouble(blnce);
	}

	// getBalance returns double stored in last
	public double getBalance() {
		return balance;
	}

	// setType sets type to string passed to method
	public void setType(String acttype) {
		type = acttype;
	}

	// getType returns string stored in type
	public String getType() {
		return type;
	}

	// setInfo sets Depositor objected passed into Depositor info
	public void setInfo(Depositor dInfo) {
		info = dInfo;
	}

	// getInfo returns Depositor object stored in info
	public Depositor getDepositor() {
		return new Depositor(info);
	}

	// setDate sets DateInfo object passed to date
	public void setDate(DateInfo dates) {
		date = dates;
	}

	// getDate returns DateInfo object stored in date
	public DateInfo getDate() {
		return new DateInfo (date);
	}

	// PERFORMS DEPOSIT
	public void deposit(double deposit) {
		balance = (balance + deposit);
		Bank.add(getType(), deposit);
		

	}
	// abstract deposit
	public abstract String makeDeposit(DepositSlip slip);
	

	// PERFORMS WITHDRAW
	protected void withdraw(double withdraw) {
		balance = (balance - withdraw);
		Bank.sub(getType(), withdraw);
	}

	
		
	
	//abstract makes withdrawal
	public abstract String makeWithdrawal(WithdrawalSlip slip); 

	
	
	
	
	// clear check passed a CheckClass object
	public String clearCheck(CheckClass check) {
		return "SuperClass ClearCheck Default";
	}
	
	public String printReciept(double oldBlnce, Transactions trans) {
		String str;
		//DecimalFormat dollar = new DecimalFormat("$#,###.##");
		if(trans.getSuccessIndi()==true) {
			
		str= String.format("%6s %16s %13s %15s %n", "Acct#", 
			"|Old Balance", "|"+trans.getTransType(),"|Balance");
		str = str + String.format("%-13s %-15s %-15s %-12s ", 
				getAcctNum(),dollar.format(oldBlnce),
				dollar.format(trans.getTransAmnt()),
				dollar.format(getBalance()));
		}else {
		
			str= String.format("%6s %16s %13s %15s %n", "Acct#", 
					"|Old Balance", "|"+trans.getTransType(),"|Balance");
				str = str + String.format("%-13s %-15s %-15s %-12s %n %-12s",  
					getAcctNum(),dollar.format(oldBlnce),dollar.format(trans.getTransAmnt()),
					dollar.format(getBalance()),trans.getFailReason());
		}
				
		
		return str;
		
	}
	
}