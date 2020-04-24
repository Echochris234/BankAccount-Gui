
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

import AccountTypes.*;
import BankException.*;
import BehindScenes.*;
import Info.*;
import Transactions.*;
import java.time.LocalDate;
import java.time.Month;




public class atmMain {

	public static void main(String[] args) throws Exception {
		GUI myprog = new GUI();
	
/*		
		 
	       
			Bank accounts = new Bank();
			
			char choice;
			String opt;
			boolean cont = true;
			File trans = new File("Transactions.txt");
			Scanner userIn = new Scanner(trans);
			// Scanner userIn = new Scanner(System.in);

			DecimalFormat dollar = new DecimalFormat("$#,###.##");
			PrintWriter output = new PrintWriter("outputWithclasses.txt");

			
			// readAccts method that returns the amount of read in accounts
			readAccts(accounts);
			

			printAccts(accounts, output);

			voidmenu();
			
		
			//List newList = new List();
			

		        
			
			do {
				System.out.print("Select an option: ");
			
				// User input to select operation from the menu
				
				
				opt =userIn.next();
				
				choice = opt.charAt(0);
				// System.out.flush();
				// Switch statements that cycle through menu
				
			try {			
				switch (choice) {

				// Case statement for Account Info
				case 'I':
				case 'i':
					accountInfo(accounts, userIn, dollar, output);
					break;

				case 'H':
				case 'h':
					infoPlusTrans(accounts, userIn, dollar, output);
					break;

				// Case statement for Balance
				case 'B':
				case 'b':
					balance(accounts, userIn, dollar, output);
					break;
				// Case statement for Quit
				case 'Q':
				case 'q':
					// prints the acconts
					printAccts(accounts, output);
					output.println(accounts.toString());
					output.flush();
					
					//BankAccountFile read = new BankAccountFile("Hello.dat");
					// for loop cycles through the active accounts
					for (int i = 0; i < accounts.getActiveAccts()-1 ; i++) {
						infoPlusTrans(accounts, userIn, dollar, output);

					}

					cont = false;
					break;

				// Case staement for Deposit
				case 'D':
				case 'd':
					deposit(accounts, userIn, dollar, output);
					break;

				// Case statement for close accout
				case 'S':
				case 's':
					closeAcct(accounts, userIn, output);
					break;
				// case statement for reopen account
				case 'R':
				case 'r':
					reOpenAcct(accounts, userIn, output);
					// Default statement to filter any illegal inputs
					break;

				// case statement for withdrawal
				case 'W':
				case 'w':
					withdrawal(accounts, userIn, dollar, output);
					break;
				// case clear check
				case 'C':
				case 'c':
					clearCheck(accounts, userIn, dollar, output);
					break;
				// case new account
				case 'n':
				case 'N':
					newAcct(accounts, userIn, dollar, output);
					break;
				// case delete account
				case 'X':
				case 'x':
					deleteAcct(accounts, userIn, dollar, output);
					break;
						
				default:
					throw new InvalidMenuSelectionException(choice);
//					break;
					
				}
				}catch(InvalidMenuSelectionException e) {
					
				output.println("");
				output.println("");
				output.println(e.getMessage());
				output.println("");
				
			}
			
			}while (cont == true);
				userIn.close();
			} 
*/
	}
		/*
		 * Input: account: Account number you would like to delete
		 * 
		 * 
		 * Process: - The method will take user input for a new account #
		 * 
		 * - FindAcct method searches array to check if the account exist
		 * 
		 * - If does not exist prints an error message
		 * 
		 * - otherwise check to see if the account has a balance greater than 0 if it
		 * does print an error message
		 * 
		 * - If balance is 0 and the account exist method creates a parallel Array of
		 * BankAccount objects newBank
		 * 
		 * - Initializes count to 0 and integer i
		 * 
		 * - for loop compares the account numbers of current account to index of the
		 * account to be deleted account[i].getAcctNum() != account[index].getAccount()
		 * 
		 * - if they are not equal copy the value of accounts[i] into newAccount[i]
		 * increment count by 1
		 *
		 * - set numAccts to 0
		 * 
		 * - for loop compares from 0<count+1 checks if the data stored in newBank has a
		 * null value
		 * 
		 * - if the value is not null copies the data in newBank to account and
		 * increment numAcctsnewBank[i] != null
		 * 
		 * - increment numAccts by 1
		 * 
		 * 
		 * - for loop that adds content of arrays to array-lists
		 * 
		 * - return the new number in numAccts
		 * 
		 * Output:
		 * 
		 * - Returns the new number of accounts
		 * 
		 */
		public static void deleteAcct(Bank accounts, Scanner userIn, 
				DecimalFormat dollar, PrintWriter output) {
			output.println("");
			output.println("");
			String oper = "Delete Account";
			DateInfo trans;


			int acctSearch;
			output.println(oper + ":");

			// Input for the user to enter account#
			System.out.print("Enter your Account: #");
			acctSearch = userIn.nextInt();
			// passes account to be searched for to deleteAcct method
			// returns a reason
			

				System.out.println("Enter the current Year");
				int yearOfTrans = userIn.nextInt();
				System.out.println("Enter the current Month");
				int monthOfTrans = userIn.nextInt();
				System.out.println("Enter todays date");
				int dayOfTrans = userIn.nextInt();
				trans = new DateInfo(yearOfTrans, monthOfTrans, dayOfTrans);

				// assigns cuenta a copy of the account to be deleted
				// for printing purposes
				// passed account number and dateinfo trans
				
				output.println(accounts.deleteAcct(acctSearch, trans));

		}

		/*
		 * Input: Bank accounts, Scanner userIn, DecimalFormat dollar, PrintWriter
		 * output
		 * 
		 * String- First Name/ Last Name/ Social/ Account Type
		 * 
		 * int- account number
		 * 
		 * Process: Program ask user to enter an account number if account exist print
		 * error
		 * 
		 * If account does not exist promt user to fill in the account information
		 * 
		 * Fills the next available index in BankAccount[]account with the information
		 * stored in methods variables
		 * 
		 * Output: Fills Bank Account account
		 * 
		 * Returns new numAccts
		 * 
		 * 
		 */
		public static void newAcct(Bank accounts, Scanner userIn, 
				DecimalFormat dollar, PrintWriter output) {
//			Account cuenta;
			DateInfo date;
			int acctSearch;
			String oper = "New Account";
			output.println(oper + ":");

			// Input for the user to enter account#
			System.out.print("Enter your Account: #");
			acctSearch = userIn.nextInt();

			// declares variables to hold temp values
			String tempLast, tempFirst, tempSocial, type;
			System.out.println("Enter the year the account was opened");
			int yearOpened = userIn.nextInt();
			System.out.println("Enter the month the account was opened");
			int monthOpened = userIn.nextInt();
			System.out.println("Enter the day the account was opened");
			int dayOpened = userIn.nextInt();
			date = new DateInfo(yearOpened, monthOpened, dayOpened);

			// holds last name
			System.out.print("Enter the last name: ");
			tempLast = userIn.next();

			// holds first name
			System.out.print("Enter the first name: ");
			tempFirst = userIn.next();

			// Declaration of Name class object every loop
			// passes last and first name
			Name names = new Name(tempLast, tempFirst);

			// Deceleration of Depositor class object every loop
			System.out.print("Enter Ssn# format- ###-##-####");
			tempSocial = userIn.next();
			// passes name object and social to constructor
			Depositor info = new Depositor(names, tempSocial);

			// Deceleration of Account class object every loop
			System.out.print("Enter the type of account: ");
			type = userIn.next();
			// passes info object, account to be searched temp balance
			// and the type of account to Account constuctor

			// opens the new account and prints the info
			output.println(accounts.openNewAcct(info, acctSearch,type, date));

		}
		

		/*
		 * Input: -Array of accounts -account to be searched for -date of check -amount
		 * of money to withdraw from check Process: -Searches to see if account exist
		 * 
		 * -if it does checks to see if it is a checking account
		 * 
		 * -if not terminates and prints error
		 * 
		 * -if account is a checking prompts for the date on the check
		 * 
		 * -sends calendar object(s) to compare date method
		 * 
		 * -determines whether check is valid/or invalid(late, early, good)
		 * 
		 * -if it is good ask for the value of the check
		 * 
		 * -if the account is overdrawn charges fee and bounces check
		 * 
		 * -if good performs clear check Output: -valid or invalid message
		 * 
		 * -transaction performed on account
		 */

		
		
		public static String quit(Bank accounts, Scanner userIn, DecimalFormat dollar, PrintWriter output) throws IOException {
			String out="";
			// prints the acconts
			out+=printAccts(accounts, output)+"\n";
			out+= accounts.toString();
			//output.println(accounts.toString());
			//output.flush();
			
			//BankAccountFile read = new BankAccountFile("Hello.dat");
			// for loop cycles through the active accounts
			for (int i = 0; i < accounts.getActiveAccts()-1 ; i++) {
				out+=infoPlusTrans(accounts, userIn, dollar, output)+"/n";

			}
			return out;
		}
		public static void clearCheck(Bank accounts, Scanner userIn,
				DecimalFormat dollar, PrintWriter output) {
			int acctSearch;
			String oper = "Clear Check";
			double checkValue;
			output.println("");
			output.println("");
			output.println(oper + ":");
			// Input for the user to enter account#
			System.out.print("What Account are you clearing a check for: ");
			System.out.println("Enter your Account: #");
			acctSearch = userIn.nextInt();

				
				// stores the date of the check
				System.out.println("Enter the check Year");
				int yearOfCheck = userIn.nextInt();
				System.out.println("Enter the check Month");
				int monthOfCheck = userIn.nextInt();
				System.out.println("Enter check date");
				int dayOfCheck = userIn.nextInt();

				// date info object
				DateInfo checkDate = new DateInfo(yearOfCheck, monthOfCheck, dayOfCheck);
				// sends check slip information

				System.out.println("What is the value of the check");
				checkValue = userIn.nextDouble();
				// clear check class passed acctsearch checkvalue and chekd date
				CheckClass check = new CheckClass(acctSearch, checkValue, checkDate);

				// clear check passed checkClass object check
				output.println(accounts.clearCheck(check));

			}

		/*
		 * Input: -The account number you would like to access
		 * 
		 * -withdrawal- The amount of money you would like to withdraw
		 * 
		 * 
		 * Process:
		 * 
		 * -The user enters the account number associated with their account
		 * 
		 * -Find account method initializes to search it account is valid
		 * 
		 * -if invalid the method prints in error
		 * 
		 * -otherwise ask the user how much they would like to withdraw'
		 * 
		 * -if the amount is less then or equal to zero prints an error message
		 * 
		 * -otherwise if withdrawal is more than the amount stored in the balance array
		 * prints error message
		 * 
		 * -other wise perform the withdrawal and assign the new balance to the index
		 * balance of the account
		 *
		 * 
		 * Output: -Account number
		 * 
		 * -Old balance
		 * 
		 * -Withdrawal amount
		 * 
		 * -New balance
		 */
		public static void withdrawal(Bank accounts, Scanner userIn, 
				DecimalFormat dollar, PrintWriter output) {
			int acctSearch;
			double withdraw;
			output.println("");
			output.println("");
			String oper = "Withdrawal";

			output.println(oper + ":");

			// Input for the user to enter account#
			System.out.print("Enter your Account: #");
			acctSearch = userIn.nextInt();
			//index = accounts.findAcct(acctSearch);

				// stores the date of the transaction
				System.out.println("Enter the current Year");
				int yearOfTrans = userIn.nextInt();
				System.out.println("Enter the current Month");
				int monthOfTrans = userIn.nextInt();
				System.out.println("Enter todays date");
				int dayOfTrans = userIn.nextInt();
				
				// date info object
				DateInfo transDate = new DateInfo(yearOfTrans, monthOfTrans, dayOfTrans);
				// sends deposit slip information
				
				System.out.print("How much would you like to Withdraw: ");
				// enter a withdraw amount
				withdraw = userIn.nextDouble();

				// withdrawalSlip passed account amount and date
				WithdrawalSlip withdrawal = new WithdrawalSlip(acctSearch, withdraw, transDate);
				// if a cd set renewal
				
				// if the account is a CD
			
					// input choice equivalent to the following
					int choice = userIn.nextInt();
		
						// sets renewal inside of slip
						withdrawal.setRenewal(choice);
						
						// performs withdrawl prints info
	 					output.println(accounts.makeWithdrawal(withdrawal));
	 
		}
			

		
		
		public static String  with(int accountNum, double amount, Bank accounts,PrintWriter output ) {
/*		
			List mylist = new List();
			mylist.add(d);
			mylist.add(Integer.toString(accountNum));
			mylist.add(Integer.toString(amount));
			
			return mylist;
			*/
		
		// variable initilization
			int acctSearch;
			//int reason;
			double deposit;

		
			// Input for the user to enter account#
	
			acctSearch = accountNum;
			
			LocalDate currentdate = LocalDate.now();
			
			
				// stores the date of the transaction
				System.out.println("Enter the current Year");
				int yearOfTrans = currentdate.getYear();
				
				System.out.println("Enter the current Month");
				int monthOfTrans = currentdate.getMonthValue();
				
				System.out.println("Enter todays date");
				int dayOfTrans = currentdate.getDayOfMonth();
				
				// date info object
				DateInfo transDate = new DateInfo(yearOfTrans, monthOfTrans, dayOfTrans);
				
				// sends deposit slip information
				// enter a deposit
				deposit = amount;
				
				WithdrawalSlip slip = new WithdrawalSlip(acctSearch, deposit, transDate);
		
				// if the account is  CD
				
					// choose one of the following renewal dates
				
						// sets renewal to slip and makes deposit
						//depo.setRenewal(0);
						//]output.println(accounts.makeWithdrawal(slip));
						return(accounts.makeWithdrawal(slip));
		}
		
		
		
		
		
		
		
				//else if not a cd perform withdrawal
		/*
		 * Input: Bank accounts| Scanner userIn| DecimalFormat dollar| PrintWriter
		 * output|
		 * 
		 * Account cuenta- holds the account int acctSearch- account to search for int
		 * reason- holds an integer return int index- holds integer return of position
		 * int check-holds integer return of a check double deposit-holds deposit amount
		 * double oldBlnce- holds balance before deposit
		 * 
		 * Process: -The user enters the account number associated with their account
		 * 
		 * -Find account method initializes to search it account is valid
		 * 
		 * -if invalid the method prints in error
		 * 
		 * -otherwise ask the user how much they would like to deposit
		 * 
		 * -if the amount is less then or equal to zero prints an error message
		 * 
		 * 
		 * -other wise perform the deposit and assign the new balance to the index
		 * balance of the account
		 *
		 * 
		 * Output: -Account number -Old balance -deposit amount -New balance
		 
		public static void deposit(Bank accounts, Scanner userIn, 
				DecimalFormat dollar, PrintWriter output) {
			// variable initilization
			int acctSearch;
			//int reason;
			double deposit;

			output.println("");
			output.println("");
			String oper = "Deposit";

			output.println(oper + ":");

			// Input for the user to enter account#
			System.out.print("Enter your Account: #");
			acctSearch = userIn.nextInt();
			
				// stores the date of the transaction
				System.out.println("Enter the current Year");
				int yearOfTrans = userIn.nextInt();
				System.out.println("Enter the current Month");
				int monthOfTrans = userIn.nextInt();
				System.out.println("Enter todays date");
				int dayOfTrans = userIn.nextInt();
				
				// date info object
				DateInfo transDate = new DateInfo(yearOfTrans, monthOfTrans, dayOfTrans);
				
				// sends deposit slip information
				// enter a deposit
				deposit = userIn.nextDouble();
				
				DepositSlip depo = new DepositSlip(acctSearch, deposit, transDate);
		
				// if the account is  CD
				
					// choose one of the following renewal dates
					int choice = userIn.nextInt();
			
						// sets renewal to slip and makes deposit
						depo.setRenewal(choice);
						output.println(accounts.makeDeposit(depo));

				}
		
*/		
		
		
		public static String  depo(int accountNum, double amount, Bank accounts,PrintWriter output ) {
/*		
			List mylist = new List();
			mylist.add(d);
			mylist.add(Integer.toString(accountNum));
			mylist.add(Integer.toString(amount));
			
			return mylist;
			*/
		
		// variable initilization
			int acctSearch;
			//int reason;
			double deposit;

		
			// Input for the user to enter account#
	
			acctSearch = accountNum;
			
			LocalDate currentdate = LocalDate.now();
			
			
				// stores the date of the transaction
				System.out.println("Enter the current Year");
				int yearOfTrans = currentdate.getYear();
				
				System.out.println("Enter the current Month");
				int monthOfTrans = currentdate.getMonthValue();
				
				System.out.println("Enter todays date");
				int dayOfTrans = currentdate.getDayOfMonth();
				
				// date info object
				DateInfo transDate = new DateInfo(yearOfTrans, monthOfTrans, dayOfTrans);
				
				// sends deposit slip information
				// enter a deposit
				deposit = amount;
				
				DepositSlip depo = new DepositSlip(acctSearch, deposit, transDate);
		
				// if the account is  CD
				
					// choose one of the following renewal dates
				
						// sets renewal to slip and makes deposit
						depo.setRenewal(0);
						//output.println(accounts.makeDeposit(depo));
						return(accounts.makeDeposit(depo));
		}
		
			// if closed
		/* Input:
		 * 		Bank array of Account objets,
		 * 		Scanner object
		 * 		PrintWriter
		 * Process:
		 * 		-User enters accont to search for index is returned
		 * 		
		 * 		-else the account does not exist
		 * 		
		 * 		-if exist prompt for DateInfo and
		 * 		
		 * 		-if it is open prints error message
		 * 		
		 * 		-else if closed set account to open 
		 *	Ouput: 
		 *		-Prints error message if not completed
		 *		 
		 *		-else prints success and sets account to open 
		 * 
		 * 
		 */
		public static void reOpenAcct(Bank accounts, Scanner userIn, PrintWriter output) throws IOException {
			DateInfo trans;
			int acctSearch;
			String oper = "Re-Open Account";

			output.println("");
			output.println(oper + ":");

			// Input for the user to enter account#
			System.out.print("Enter your Account: #");
			acctSearch = userIn.nextInt();


				// stores the date of the transaction
				System.out.println("Enter the current Year");
				int yearOfTrans = userIn.nextInt();
				System.out.println("Enter the current Month");
				int monthOfTrans = userIn.nextInt();
				System.out.println("Enter todays date");
				int dayOfTrans = userIn.nextInt();
				trans = new DateInfo(yearOfTrans, monthOfTrans, dayOfTrans);
				
				// Reopens account and prints confirmation or error message
				output.println(accounts.reOpenAcct(acctSearch,trans));
				
		}

		
		
		
		/* Input:
		 * 		Bank array of Account objets,
		 * 		Scanner object
		 * 		PrintWriter
		 * Process:
		 * 		-User enters accont to search for index is returned
		 * 		
		 * 		-else the account does not exist
		 * 		
		 * 		-if exist prompt for DateInfo and
		 * 		
		 * 		-if it is closed prints error message
		 * 		
		 * 		-else if open set account to closed 
		 *	Ouput: 
		 *		-Prints error message if not completed
		 *		 
		 *		-else prints success and sets account to closed 
		 * 
		 * 
		 */
		public static void closeAcct(Bank accounts, Scanner userIn, PrintWriter output) throws IOException {
			DateInfo trans;
			int acctSearch;
			String oper = "Close Account";

			output.println("");
			output.println(oper + ":");

			// Input for the user to enter account#
			System.out.print("Enter your Account: #");
			acctSearch = userIn.nextInt();


				// stores the date of the transaction
				System.out.println("Enter the current Year");
				int yearOfTrans = userIn.nextInt();
				System.out.println("Enter the current Month");
				int monthOfTrans = userIn.nextInt();
				System.out.println("Enter todays date");
				int dayOfTrans = userIn.nextInt();
				trans = new DateInfo(yearOfTrans, monthOfTrans, dayOfTrans);

				// closes the account prints confirmation or fail message
				output.println(accounts.closeAcct(acctSearch,trans));
				
		}

		/*
		 * Input: -Bank Account[] account object Array
		 * 
		 * -numAccts- total amount of accounts read in
		 * 
		 * -acctSearch- account we are searching for
		 * 
		 * Process: -findAcct method is sent
		 * 
		 * -Array Bank Account[] account
		 * 
		 * -numAccts
		 * 
		 * -Entered account number
		 * 
		 * -if account is found boolean found = true assign the value of index i to pos
		 * 
		 * -if found==true return pos(value index of i) -otherwise return -1
		 * 
		 * Output: Returns position of found account
		 * 
		 * 
		 */

		public static String infoPlusTrans(Bank accounts, Scanner userIn,
				DecimalFormat dollar, PrintWriter output) {


			String ssnSearch;
			String str = ("Info+Trans:\n");
			
			// Input for the user to enter account#
			System.out.print("Enter your social: #");
			ssnSearch = userIn.next();
						
			str+=( String.format("%-11s %-13s %-12s %-12s %-15s %-13s %n",
					"Name", "|SSN#", "|Acct#", "|Balance", "|AcctType", "|Status\n"));

			str+=(accounts.infoPlusTrans(ssnSearch));
		

/*
			output.println("");
			output.println("Info+Trans:");

			// Input for the user to enter account#
			System.out.print("Enter your social: #");
			ssnSearch = userIn.next();

			output.printf("%-11s %-13s %-12s %-12s %-15s %-13s %n",
					"Name", "|SSN#", "|Acct#", "|Balance", "|AcctType", "|Status");
			output.println("");
			output.println(accounts.infoPlusTrans(ssnSearch));
			output.println("");
			output.println("");
			
			output.flush();
		*/	
			return str;
		}

		/*
		 * Input: account-The account number you would like to access
		 * 
		 * 
		 * 
		 * Process: -The user enters the account number associated with their account
		 * 
		 * -Find account method initializes to search it account is valid
		 * 
		 * -if invalid the method prints in error
		 * 
		 * -otherwise prints account # and balance
		 *
		 * 
		 * Output: -Account number -Balance
		 */
		public static void balance(Bank accounts, Scanner userIn,
				DecimalFormat dollar, PrintWriter output) {
			DateInfo date;

			int acctSearch;

			output.println("");
			output.println("");
			output.println("Balance:");

			// Input for the user to enter account#
			System.out.print("Enter your Account: #");
			acctSearch = userIn.nextInt();
			
			// stores the date of the transaction
						System.out.println("Enter the current Year");
						int yearOfTrans = userIn.nextInt();
						System.out.println("Enter the current Month");
						int monthOfTrans = userIn.nextInt();
						System.out.println("Enter todays date");
						int dayOfTrans = userIn.nextInt();
						date = new DateInfo(yearOfTrans, monthOfTrans, dayOfTrans);
						output.println(accounts.Balance(acctSearch, date));
		output.println("");
				output.println("");
				
				output.println("");
				output.flush();
			}

		/*
		 * Input: -Bank Account[] account object Array
		 * 
		 * -numAccts- total amount of accounts read in
		 * 
		 * -acctSearch- account we are searching for
		 * 
		 * Process: -findAcct method is sent
		 * 
		 * -Array Bank Account[] account
		 * 
		 * -numAccts
		 * 
		 * -Entered account number
		 * 
		 * -if account is found boolean found = true assign the value of index i to pos
		 * 
		 * -if found==true return pos(value index of i) -otherwise return -1
		 * 
		 * Output: Returns position of found account
		 * 
		 * 
		 */

		public static String acctInfo(String formattedSsn, Bank accounts) {
			return(accounts.accountInfo(formattedSsn));
		}
		public static void accountInfo(Bank accounts, Scanner userIn,
				DecimalFormat dollar, PrintWriter output) {
			String ssnSearch;

			output.println("");
			output.println("Info:");

			// Input for the user to enter account#
			System.out.print("Enter your social: #");
			ssnSearch = userIn.next();

			output.println(accounts.accountInfo(ssnSearch));
			
			output.flush();
			
		}

		/*
		 * INPUT: PrintWriter object
		 * 
		 * PROCESS: PRINTS MENU
		 * 
		 * OUTPUT: PRINTED MENU
		 */
		public static void voidmenu() {
			// Prints the selection menu
			System.out.println("");
			System.out.printf("%52s", "SELECT ONE OF THE FOLLOWING OPTIONS");
			System.out.println("");
			System.out.println("");
			System.out.printf("%59s", "***********************************************");
			System.out.println(" ");
			System.out.printf("%26s", "Withdrawal: W");
			System.out.printf("%14s", "Deposit: D");
			System.out.printf("%18s", "New Account: N");
			System.out.println("");
			System.out.print("\t\t  Clear Check: C");
			System.out.println("\t\t\t  Information: I");
			System.out.println("");
			System.out.printf("%23s", "Balance: B");
			System.out.printf("%24s", "Delete Account: X");
			System.out.printf("%11s", "Quit: Q");
			System.out.println(" ");
			System.out.printf("%59s", "***********************************************");
			System.out.println("");
			System.out.println("");

		}

		/*
		 * Input: Account[] account- array of BankAccount objects
		 * 
		 * numAccts- total amount of accounts read in
		 * 
		 * Process: for loop loops through the Account [] array
		 * 
		 * prints information stored in each element of the array
		 *
		 * Output: Prints data stored in the BankAccount object Arrays index in a table
		 * 
		 */
		public static String printAccts(Bank accounts, PrintWriter output) throws IOException {
			String str= "";
			str+="\t        ACCOUNTS IN THE DATA BASE:\n";
			//output.println("\t        ACCOUNTS IN THE DATA BASE:");
			//output.println
			str+=("\t       ****************************\n");
			// Table Headings
			// Table Headings

			//output.println
			str+=( String.format("%-11s %-13s %-12s %-12s %-15s %-13s %n",
					"Name", "|SSN#", "|Acct#", "|Balance", "|AcctType", "|Status\n"));

			str+= accounts.printAccts();
			//output.println(accounts.printAccts());
			output.println(str);
			output.flush();
			return str;

		}

		/*
		 * ReadAccts Method Input: -Array of bank account objects
		 * 
		 * -int maxAccts representing total possible accounts Process: -Instantiates
		 * Depositor(info) object and Bank account object(bank)
		 * 
		 * -Reads in a line of data and tokenizes at the spaces Output: -Array of
		 * BankAccount objects gets filled
		 * 
		 * -Returns how many accounts where read in
		 * 
		 */
		public static void readAccts(Bank accounts) throws IOException {
			// local variable

			String line;
			File myFile = new File("../Initial_Data.txt");
			Scanner input = new Scanner(myFile);
			// Scanner input = new Scanner("System.in");

			// While loop that reads till EOF
			while (input.hasNext()) {
				// read in a line of data
				line = input.nextLine().trim();
				// splits line into array of tokens
				String[] tokens = line.split(" ");
				
				String lName = tokens[0];
				String fName = tokens[1];
				String ssn = tokens[2];
				
				int acctNum = Integer.parseInt(tokens[3]);
				double deposit = (Double.parseDouble(tokens[4]));
				String type = tokens[5];
				
				int yearOfTrans = Integer.parseInt(tokens[6]);
				int monthOfTrans = Integer.parseInt(tokens[7]);
				int dayOfTrans = Integer.parseInt(tokens[8]);
				DateInfo transDate = new DateInfo(yearOfTrans, monthOfTrans, dayOfTrans);
				//int renew = Integer.parseInt(tokens[9]);
				
				// Declaration of Name class object every loop
				Name names = new Name(lName, fName);

				// Deceleration of Depositor class object every loop
				Depositor info = new Depositor(names, ssn);
				
				// opens new account
				accounts.openNewAcct(info,acctNum,type,transDate);

				// passes the account made to cuenta
				//cuenta = accounts.getAcct(accounts.findAcct(acctNum));
				
				
				DepositSlip depo = new DepositSlip(acctNum, deposit, transDate);
				// depo.setRenewal(period);
				// cuenta.makeDeposit(depo);

				// if account equals cd
				//if(cuenta.equalsCD()) {

					System.out.println();
					System.out.println();
					// pass token[9] renewal tp choice
					int choice = Integer.parseInt(tokens[9]);
					// if choice equals one of these valid
				/*	if(choice == 6 ||
					   choice == 12||
					   choice == 18||
					   choice == 24) {
	*/					depo.setRenewal(choice);
					//	System.out.println("I set the renwal");
						// perform deposit
						accounts.makeDeposit(depo);
						// else invalid
	/**				}else {
						System.out.println("That is not a valid renewal period");
					}
					// else account is not a cd perform deposit
				}else

				accounts.makeDeposit(depo);
				// adds to static values
				
	*/
			}
			input.close();
		}
	
		

	}

