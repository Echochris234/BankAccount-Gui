package BehindScenes;

import AccountTypes.*;
import BankException.*;
import Info.*;
import Transactions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.DecimalFormat;


public class Bank {
/*	// array of account objects
	private ArrayList<Account> accounts;
	private Account [] records = new Account[3];
	/*
	 * static variable to store total amount in all and each type of account decimal
	 * format for formatting
	 */
	private static double totalInCheck;
	private static double totalInAll;
	private static double totalInCd;
	private static double totalInSaving;
	static DecimalFormat dollar = new DecimalFormat("$#,###.##");
	
	private final int MAX_ACCTS = 100;
	// array of account objects
	private Account[] accounts;
	private int activeAccts;

	// no args constructor
	public Bank() throws IOException {
		accounts = new Account[MAX_ACCTS];
		activeAccts = 0;

	}


	public String toString() {
		String str;
		str = String.format("%6s %16s %13s %15s %n", "#ofAccts", "|Total in All", "|Total In CD",
				"|Total in Checking" + "|Total In Saving");
		str = str + String.format("%-13s %-14s %-12s %-17s %-1s ", getActiveAccts(), (getTotalAmnt()), (getTotalCd()),
				(getTotalCheck()), (getTotalSaving()));
		return str;
	}

	public void addAcct(Account newAccount) throws Exception {
		accounts[activeAccts] = newAccount;
		//file.moveFilePointer(file.fileSize());
		writeTo(accounts[activeAccts]);
		// increments activeAccts by 1
		activeAccts++;

	}

	// returns the total savings in bank
	public static String getTotalSaving() {
		return dollar.format(totalInSaving);
	}

	// returns value of totalInCd
	public static String getTotalCd() {
		return dollar.format(totalInCd);
	}

	// returns the value of totalInAll
	public static String getTotalAmnt() {
		return dollar.format(totalInAll);
	}

	// returns the value in totalInCheck
	public static String getTotalCheck() {
		return dollar.format(totalInCheck);
	}

	// deposits amount to variable depending on account type
	public static void add(String string, double deposit) {
		// adds to total
		totalInAll = totalInAll + deposit;
		// if the account passed is a cd
		if (string.equalsIgnoreCase("CD")) {
			// add the deposit amount to totalInCd
			totalInCd = totalInCd + deposit;
		}
		// else if Savings
		else if (string.equalsIgnoreCase("Saving")) {
			// deposit into totalInSaving
			totalInSaving = totalInSaving + deposit;
		}
		// else(Checking)
		else {
			// deposit to totalInCheck
			totalInCheck = totalInCheck + deposit;
		}

	}

	// subtracts from static value of all
	public static void sub(String string, double withdraw) {
		totalInAll = totalInAll - withdraw;

		// the cycles to which account type is passed
		// if cd sub cd
		if (string.equalsIgnoreCase("CD")) {
			totalInCd = totalInCd - withdraw;
		}
		// if savings sub saving
		else if (string.equalsIgnoreCase("Saving")) {
			totalInSaving = totalInSaving - withdraw;
		}

		// else (checking)
		else {
			// sub check
			totalInCheck = totalInCheck - withdraw;
		}

	}

	// returns account stored in array of accounts passed into index

	public Account getAcct(int index) {
		
	
		Account cuenta;
		// if the account equals check return polymorph checkingAccount
				if (accounts[index].equalsChecking())
					cuenta = new CheckingAccount(accounts[index]);
				// else if account equals a savings return polymorph savingsAccount
				else if (accounts[index].equalsSaving())
					cuenta = new SavingsAccount(accounts[index]);
				// else return CDAccount
				else
					cuenta = new CDAccount(accounts[index]);

		return cuenta;
		
		

	}

	
	// returns activeAccts
	public int getActiveAccts() {
		return activeAccts;
	}

	/*
	 * Input: -String of social to form for XXX-XX-XXXX
	 * 
	 * Process: -if account is found boolean found = true assign the value of index
	 * i to pos
	 * 
	 * -if found==true return pos(value index of i) -otherwise return -1
	 * 
	 * Output: -Index of the account object associated with the social
	 * 
	 */
	public int findSocial(String ssnSearch) {
		// Variable used to hold index of account number
		int pos = 0;

		/*
		 * boolean variable used to determine if the account entered match an account on
		 * file
		 */
		boolean found = false;

		// for loop to cycle through the accounts to find matching account

		for (int i = 0; i < getActiveAccts(); i++) {

			/*
			 * if account number is equal to the number in current [i] index return boolean
			 * true and assign the index to pos
			 */
			if ((getAcct(i)).equalsSsn(ssnSearch)) {
				found = true;
				pos = i;
			}

		}
		// if found is equal to true return pos variable containing index
		if (found == true)
			return pos;
		// else return -1
		else
			return -1;
	}

	/*
	 * Input: -acctSearch- account we are searching for
	 * 
	 * Process: -findAcct method is sent
	 * 
	 * --acctSearch- account we are searching for
	 * 
	 * -if account is found boolean found = true assign the value of index i to pos
	 * 
	 * -if found==true return pos(value index of i) -otherwise return -1
	 * 
	 * Output: Returns position of found account
	 * 
	 * 
	 */
	public int findAcct(int acctSearch) {
		// Variable used to hold index of account number
		int pos = 0;
		int i;

		/*
		 * boolean variable used to determine if the account entered match an account on
		 * file
		 */
		boolean found = false;

		// for loop to cycle through the accounts to find matching account
		for (i = 0; i < getActiveAccts(); i++) {

			/*
			 * if account number is equal to the number in current [i] index return boolean
			 * true and assign the index to pos
			 */
			if ((getAcct(i)).equalsAcctNum(acctSearch)) {
				found = true;
				pos = i;
			}

		}
		// if found is equal to true return pos variable containing index
		if (found == true)
			return pos;
		// else return -1
		else
			
			return -1;

	}

	// deletes account returns indicator if not
	public String deleteAcct(int acctSearch, DateInfo date) {
		// new transaction // fills with initial data
		Transactions trans = new Transactions();
		trans.setDate(date);
		trans.setTransType("Delete Account");
		String reason;
		try {
			int index = findAcct(acctSearch);
			if (index < 0) {
				throw new InvalidAccount(acctSearch);
				// searches for account to find index
			} else {
				// Passes new account copy to cuenta with index
				Account cuenta = (getAcct(index));
				// if the account is not open
				
				try {
					if (cuenta.getAcctStats() != false) {
						throw new ErrorDeleteAcct(cuenta.getAcctNum());
					} else {
						// but has a balance
						if (cuenta.getBalance() > 0) {

							throw new ErrorDeleteAcct(cuenta.getAcctNum(), cuenta.getBalance());

							// else good
						} else {
							Account[] newBank = new Account[MAX_ACCTS];
							// variable i for loop and count for counter
							int i, count = 0;
							// for loop
	
							for (i = 0; i < activeAccts; i++) {
								// if statement value of account[i].getAccount()
								// != account[index].getAccount()
								
								if (getAcct(i).equalsAcctNum(acctSearch)==false)	
								{
									// place contents of account[i] in newBank[i]
									newBank[i] = accounts[i];
									// increment count
									count++;
								}

							}
							// set numAccts to 0
							activeAccts = 0;
							
							// calls trunicate to erase the account
							trunicateFile();
							
							// for loop cycles from 0 to count+1
							for (i = 0; i < count + 2; i++) {
								// if newBank[i] does not have a null value
								if (newBank[i] != null) {
									
									// copy the contents of newBank[i] to account[numAccts]
									addAcct(newBank[i]);
									// increment numAccts by 1
									
								}
								
							}
						}
						reason = (("The Account: #" + acctSearch + " Has Been Deleted"));
					}
									
				}catch (Exception e) {
							// sets fail reason
							trans.setFailReason(e.getMessage());
							trans.setSuccessIndi(false);
							cuenta.addTrans(trans);
							// sets updated account
							//setAcct(index, cuenta);
							// return -1
							reason = cuenta.toString() + "\n" + e.getMessage();
							
						}
			}
		}catch (InvalidAccount e) {
			reason = e.getMessage();
		}
	return reason;

	}

	public String clearCheck(CheckClass check) {
		// variable for acctNum holds account#
		int acctNum;
		// holds index if exist
		int index;
		// holds copy of account
		Account cuenta;
		// holds string to return
		String str;
		// gets account number from withdrawalSlip
		acctNum = check.getAcct();
		// passes acctNum to findAcct method
		index = findAcct(acctNum);
		try {
			// if index is less than 0
			if (index < 0) {
				throw new InvalidAccount(acctNum);

				// sets error to str
				// str=("Account: #" + acctNum+" Does not exist-Try Again");

				// else
			} else {
				if (getAcct(index).equalsChecking()) {
					// gets copy of account stored at index
					cuenta = new CheckingAccount(getAcct(index));

					// passes string to str from clearcheck in account
					str = cuenta.clearCheck(check);
					// updates the new account
					//setAcct(index, cuenta);
					accounts[index]=cuenta;
					// modifys the record in the file
					modifyRecord(index, accounts[index]);
				} else {
					// else if not a checkingz
					str = ("The Account #" + acctNum + " is not a checking account");
				}

			}
		} catch (Exception e) {
			str = e.getMessage();
		}
		// returns str to caller
		return str;

	}

	public String makeWithdrawal(WithdrawalSlip with) {
		// variable for acctNum holds account#
		int acctNum;
		// holds index if exist
		int index;
		Account cuenta;
		// holds copy of account
		// holds string to return
		String str;
		int choice = with.getRenewal();
		// gets account number from withdrawalSlip
		acctNum = with.getAcctNum();
		// passes acctNum to findAcct method
		index = findAcct(acctNum);
		// if index is less than 0
		try {
			if (index < 0) {
				throw new InvalidAccount(acctNum);
			} else {
				// passes a copy of the account stored at index to cuenta
				cuenta = getAcct(index);	
				if (cuenta.equalsCD()) {
					if (choice == 6 || choice == 12 || choice == 18 || choice == 24) {

						str = cuenta.makeWithdrawal(with);
						//setAcct(index, cuenta);
					} else {

						// throws invalid renew date 
						throw new InvalidRenewDateException();
					}

				} else {
					// performs withdrawal
					str = cuenta.makeWithdrawal(with);
					// sets updated account
					//setAcct(index, cuenta);
				}
				accounts[index]=cuenta;
				// updates file
				modifyRecord(index, accounts[index]);
			}

			// returns str to caller
		} catch (Exception e) {
			str = e.getMessage();
		}
		return str;
	}

	// checks to see if the account exist
	// if does passes account futher if not prints error
	public String makeDeposit(DepositSlip depo) {
		// variable for acctNum holds account#
		int acctNum;
		// holds index if exist
		int index;
		// holds copy of account
		Account cuenta;
		int choice = depo.getRenewal();
		// holds string to return
		String str;
		// gets account number from depositSlip
		acctNum = depo.getAcctNum();
		// passes acctNum to findAcct method
		index = findAcct(acctNum);
		// if index is less than 0
		try {
			if (index < 0) {
				throw new InvalidAccount(acctNum);
			} else {

				// passes a copy of the account stored at index to cuenta
				cuenta = getAcct(index);
				if (cuenta.equalsCD()) {
					if (choice == 6 || choice == 12 || choice == 18 || choice == 24) {

						str = cuenta.makeDeposit(depo);
						//setAcct(index, cuenta);
					} else {
						// invalid renew date optio
						throw new InvalidRenewDateException(choice);
					}

				} else {
					// performs withdrawal
					str = cuenta.makeDeposit(depo);


					
 				}
				
				// retrns the account to string to str
				accounts[index]=cuenta;
				
				//edits teh record
				modifyRecord(index, accounts[index]);
			}

			// returns str to caller
		} catch (Exception e) {
			str = e.getMessage();
		}
		return str;
	}



	public String openNewAcct(Depositor info, int acctNum, String type, DateInfo transDate) {
		int index;
		String str;
		Account cuenta;
		Transactions trans = new Transactions();
		trans.setDate(transDate);
		// sets trans to new account
		trans.setTransType("New Account");
		// sets trasaction date to today

		index = findAcct(acctNum);
		try {
			if (index > 0) {
				throw new InvalidNewAccountException(acctNum);

				// sets error to str
				// else
			} else {
				// if type equals checking
				if (type.equalsIgnoreCase("Checking")) {

					// create a polymorphed CheckingAccount object
					cuenta = new CheckingAccount(info, acctNum, type);

					// else if equals Saving 
					
				} else if (type.equalsIgnoreCase("Saving")) {

					// create polymorphed SavingsAccount
					cuenta = new SavingsAccount(info, acctNum, type);

					// else
				} else {

					// create a polymorphed CDAccount
					cuenta = new CDAccount(info, acctNum, type, transDate);

				}
				// add the transaction
				cuenta.addTrans(trans);

				// add the account
				addAcct(cuenta);
				//writeTo(cuenta);
				
				// retrns the account to string to str
				str = cuenta.toString();
			}
		} catch (Exception e) {
			str = e.getMessage();

		}
		// returns str to caller
		return str;
	}
	
	// modifys the record at index witht he new account
	private void modifyRecord(int index, Account account) throws IOException {
		// opens up the file
		BankAccountFile file = new BankAccountFile("Hello.dat","rw");
		// mvs pointer to location 
		file.moveFilePointer(index);
		//writes the new account
		file.writeItem(account);
		//closes the file
		file.close();
		
	}
	
	// Write account to file
	public void writeTo(Account acct) throws Exception {
		
		// opens file
		BankAccountFile file = new BankAccountFile("Hello.dat","rw");
		//moves file
		file.moveFilePointer(activeAccts);
		//writes account
		file.writeItem(acct);
		// closes account
		file.close();

	}
	
	
	public String reOpenAcct(int acctSearch, DateInfo date) throws IOException {
		int index;
		String str;
		Account cuenta;
		Transactions trans = new Transactions();
		trans.setDate(date);
		// sets trans to new account
		trans.setTransType("Re-Open Account");

		// sets trasaction date to today
		index = findAcct(acctSearch);
		try {
			// if index is less than 0
			if (index < 0) {
				// sets error to str
				throw new InvalidAccount(acctSearch);
				// else
			} else {
				// passes account copy to cuenta
				cuenta = getAcct(index);
				cuenta.openAccount();

				try {
					if (cuenta.openAccount() != true) {
						// account is already open
						throw new AlreadyOpenException(acctSearch);

					} else {
						// the account is open again sends message
						str = ("The Account # " + cuenta.getAcctNum() + " is now open again");
						//cuenta.addTrans(trans);
						//setAcct(index, cuenta);
					}
					// catch any errors
				} catch (Exception e) {
					str = e.getMessage();
					trans.setFailReason(e.getMessage());
					trans.setSuccessIndi(false);
					
					// finally add the transaction and ipdate te file
				}finally {
					cuenta.addTrans(trans);
					accounts[index]=cuenta;
					modifyRecord(index, cuenta);
				}
				
			}

			
		} catch (InvalidAccount e) {
			str = e.getMessage();

		}
		// returns str to caller
		return str;

	}

	public String closeAcct(int acctSearch, DateInfo date) throws IOException {
		int index;
		String str;
		Account cuenta;
		Transactions trans = new Transactions();
		trans.setDate(date);
		// sets trans to new account
		trans.setTransType("Close Account");

		// sets trasaction date to today
		index = findAcct(acctSearch);
		// if index is less than 0
		try {
			// if index is less than 0
			if (index < 0) {
				// sets error to str
				throw new InvalidAccount(acctSearch);
				// else
			} else {
				// passes account copy to cuenta
				cuenta = getAcct(index);

				try {
					if (cuenta.closeAccount() != false) {
						// teh account is closed error
						throw new AlreadyClosedException(acctSearch);

					} else {
						str = ("The Account # " + cuenta.getAcctNum() + " is now closed");
						cuenta.addTrans(trans);
						//setAcct(index, cuenta);
					}
				} catch (Exception e) {
					str = e.getMessage();
					trans.setFailReason(e.getMessage());
					trans.setSuccessIndi(false);
					cuenta.addTrans(trans);
					//setAcct(index, cuenta);
				}
				// updates file
				accounts[index]=cuenta;
				modifyRecord(index, cuenta);
			}

		} catch (InvalidAccount e) {
			str = e.getMessage();

		}
		// returns str to caller
		return str;
	}
	
	/**
	 * Serialize the transactions to the account matching the social
	 * passed social
	 */ 
	public void serializeTrans(String social) throws FileNotFoundException, IOException {
		Account cuenta;
		// loops through account
		for(int i = 0; i<getActiveAccts();i++) {
			//
			cuenta=getAcct(i);
			// if the account equals the social
			if(cuenta.equalsSsn(social)){
				// call accounts method to serailize the transactions
				cuenta.serializeTransaction();
			}
			
		}
	}

	/**
	 * Returns the balance in the account
	 * 
	 * passed acctnum and date
	 */
	public String Balance(int acctNum, DateInfo date) {
		int index;
		String str;
		Account cuenta;
		Transactions trans = new Transactions();
		trans.setDate(date);
		// sets trans to new account
		trans.setTransType("Balance");

		// sets trasaction date to today
		index = findAcct(acctNum);
		// if index is less than 0
		try {
			// if index is less than 0
			if (index < 0) {
				// sets error to str
				throw new InvalidAccount(acctNum);
				// else
			} else {
				// passes account copy to cuenta
				cuenta = getAcct(index);

				str = String.format("%6s %16s %13s %n", "Acct#", "|Balance", "|Type");
				str = str + String.format("%-16s %-15s %-15s", cuenta.getAcctNum(),
									dollar.format(cuenta.getBalance()),
						cuenta.getType());
				cuenta.addTrans(trans);
				//setAcct(index, cuenta);
			}
			accounts[index]=cuenta;
			

		} catch (Exception e) {
			str = e.getMessage();
		}
		return str;

	}
	
	public String infoPlusTrans(String ssn) {
		// findSocial method used to search array for social

	
		String str="";
		String tStr="";
		Account cuenta;
		int i;
		//int j;
		BankAccountFile read;
		int index ;
		index = (findSocial(ssn));
		try {
			
			// if statement to filter out invalid account
			if (index < 0) {
				
				throw new InvalidSocialException(ssn);

				// opens up file to read fom file
			} else {

				// opens file
				read = new BankAccountFile("Hello.dat","r");
				
				// loops through active accounts
				for (i = 0; i < getActiveAccts(); i++) {

					// move pointer to position of i
					read.moveFilePointer(i);
					//seralize the transactions pertaining to the ssn
					serializeTrans(ssn);
					// attach account stored in accounts[i] to cuenta
					cuenta = (read.readAccountObject());
					
					// if the ssnSearch matches up with social stored in cuenta
					if (cuenta.equalsSsn(ssn)) {
					
						// assigns cuenta.toString
						str =str+String.format("%s %n", cuenta.toString());
						// deserialize the transactions and assign them to tSTR
					tStr=cuenta.deserializeTransaction();
					//System.out.println(tStr);

				}
				
				
			}
				// close the file
				read.close();
				str= str+tStr;

		}
			//catch exception
		}catch (Exception e) {
			str = e.getMessage();
			
		}
		return str;

		}

	
	// trunicates the file to read in updated files from delete
	public void trunicateFile() throws IOException {
	    BankAccountFile file = new BankAccountFile("Hello.dat", "rw");
	      // Open the file for reading.
	    
	      //truncate the RandomAccessFile
	      file.erase(file.fileSize()-file.fileSize());
	    // move pointer
	      file.moveFilePointer(0);
	     
	      // Close the file.
	      file.close();

	}
	
	
	public String accountInfo(String ssn) {
		// findSocial method used to search array for social

		String str;
		Account cuenta;
	

		int index = (findSocial(ssn));
		try {
			// if statement to filter out invalid account
			if (index < 0) {
				throw new InvalidSocialException(ssn);

			} else {

				// attach account stored in accounts[i] to cuenta
				cuenta = (getAcct(index));

				// if the ssnSearch matches up with social stored in cuenta
				// print the account information

				str = String.format("%-1s %n", cuenta.toString());
			}

		} catch (Exception e) {
			str = e.getMessage();
		} 
		return str;
	}
	

	public String printAccts() throws IOException {
		Account cuenta;
		String str="";
		//read.moveFilePointer(0);
		BankAccountFile read = new BankAccountFile("Hello.dat","r");
		read.moveFilePointer(0);
			for (int i = 0; i < getActiveAccts(); i++) {
			read.moveFilePointer(i);
			cuenta = read.readAccountObject();
			//System.out.println(cuenta.toString()+"ello");
			str =str+String.format("%-1s %n", cuenta.toString());
			System.out.println(str);
			// Prints the account information for the accounts
			
		
	}
		read.close();

		return str;
	}

}