import javax.swing.*;

import BankException.*;
import BehindScenes.Bank;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormatSymbols;
public class GUI extends JFrame implements ActionListener {
	JPanel buttonPanel, topPanel, operationPanel;
	JTextField display;

	JButton Deposit;
	JButton Withdrawal;
	JButton Balance;
	JButton Acct_Info;
	JButton Trans_History;
	JButton Close_Account;
	JButton Re_openAccount;
	JButton Clear_Check;
	JButton New_Account;
	JButton Delete_Account;
	JButton Quit;

	Bank accounts = new Bank();

	char choice;
	String opt;
	boolean cont = true;
	File trans = new File("../Transactions.txt");
	Scanner userIn = new Scanner(trans);
	// Scanner userIn = new Scanner(System.in);

	DecimalFormat dollar = new DecimalFormat("$#,###.##");
	PrintWriter output = new PrintWriter("outputWithclasses.txt");
	DecimalFormatSymbols ssnSymbols = new DecimalFormatSymbols();  

    // Use space not comma to thousands: 10 000 not 10,000.   
    

	public GUI() throws IOException {

		super("Mock Bank");
		DecimalFormatSymbols ssnSymbols = new DecimalFormatSymbols(); 
		
		
		atmMain.readAccts(accounts);
		atmMain.printAccts(accounts, output);
		atmMain.voidmenu();

		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4));
		buttonPanel.add(Deposit = new JButton("Deposit"));
		buttonPanel.add(Withdrawal = new JButton("Withrawal"));
		buttonPanel.add(Balance = new JButton("Balance"));
		buttonPanel.add(Acct_Info = new JButton("Information"));
		buttonPanel.add(Trans_History = new JButton("History"));
		buttonPanel.add(Close_Account = new JButton("Close Account"));
		buttonPanel.add(Re_openAccount = new JButton("Re-Open Account"));
		buttonPanel.add(Clear_Check = new JButton("Clear Check"));
		buttonPanel.add(New_Account = new JButton("New Account"));
		buttonPanel.add(Delete_Account = new JButton("Delete Account"));
		buttonPanel.add(Quit = new JButton("Quit"));

		Deposit.addActionListener(this);
		Withdrawal.addActionListener(this);
		Balance.addActionListener(this);
		Acct_Info.addActionListener(this);
		Trans_History.addActionListener(this);
		Close_Account.addActionListener(this);
		Re_openAccount.addActionListener(this);
		Clear_Check.addActionListener(this);
		New_Account.addActionListener(this);
		Delete_Account.addActionListener(this);
		Quit.addActionListener(this);

		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.add(new JTextField(45));
		// jtfResult.setHorizontalAlignment(JTextField.RIGHT);
		// jtfResult.setEditable(false);

		add(mainPanel);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame;
		String accountNum;
		int account;
		String result;
		double amount;
	
		
		
		
		char choice = 0;
		if(e.getSource()==Acct_Info)
			choice ='i';
		if (e.getSource() == Deposit)
		choice = 'd';
		
		if (e.getSource() == Withdrawal)
		choice = 'w';
		
		if(e.getSource()==Quit)
			choice ='q';
		
		
	

		System.out.println(choice);
		try {
			switch (choice) {

			// Case statement for Account Info
			case 'I':
			case 'i':
				String ss;
				int tempvalue;
				String temp;
				String formattedSsn;
			
				frame = new JFrame("Account Information");

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(300, 300); 
					ss = JOptionPane.showInputDialog(null, "What is Your ssn # ",
						JOptionPane.QUESTION_MESSAGE);
					//System.out.println(ss);
					if (ss.matches("[0-9]+"))  {
					
						formattedSsn = ss.substring(0, 3) + '-' + 
											  ss.substring(3, 5) + '-' + 
											  ss.substring(5, 9);
						
						

						
						//System.out.println(formattedSsn);
					
					}else {
						
						JOptionPane.showMessageDialog(null, ss+": Is not a valid account number! Try Again ", "WARNING", JOptionPane.WARNING_MESSAGE);
						//System.out.println("Try Again");
						break;
					}
					temp =atmMain.acctInfo(formattedSsn, accounts);
					JOptionPane.showMessageDialog(null, temp, "Account Information", JOptionPane.PLAIN_MESSAGE);
				//atmMain.acctInfo(ss, accounts,  output);
				break;

			case 'H':
			case 'h':
				atmMain.infoPlusTrans(accounts, userIn, dollar, output);
				break;

			// Case statement for Balance
			case 'B':
			case 'b':
				atmMain.balance(accounts, userIn, dollar, output);
				break;
			case 'D':
			case 'd':

				frame = new JFrame("Deposit");

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(300, 300); 
					accountNum = JOptionPane.showInputDialog(null, "What is Your Account # ",
						JOptionPane.QUESTION_MESSAGE);
					if (accountNum.matches("[0-9,.]+")&& !(accountNum== null))  {
					account = Integer.parseInt(accountNum);
					}else {
						
						JOptionPane.showMessageDialog(null, accountNum+": Is not a valid account number! Try Again ", "WARNING", JOptionPane.WARNING_MESSAGE);
						//System.out.println("Try Again");
						break;
					}

				String deposit = JOptionPane.showInputDialog(null,
						"How much would you like to deposit " + JOptionPane.QUESTION_MESSAGE);
				if (deposit.matches("[0-9,.]+"))  {
					amount = Double.parseDouble(deposit);
				try {	
					
					result=atmMain.depo(account, amount, accounts,output);
					JOptionPane.showMessageDialog(null, result, "Reciept", JOptionPane.PLAIN_MESSAGE);
					break;
				
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(null, deposit+": Is not a valid deposit amount! Try Again ", "WARNING", JOptionPane.WARNING_MESSAGE);
				//System.out.println("Try Again");
				break;
			}

			
				

			// Case statement for close accout
			case 'S':
			case 's':
				atmMain.closeAcct(accounts, userIn, output);

				break;
			// case statement for reopen account
			case 'R':
			case 'r':

				atmMain.reOpenAcct(accounts, userIn, output);
				// Default statement to filter any illegal inputs
				break;

			// case statement for withdrawal
			case 'W':
			case 'w':
				frame = new JFrame("Withdrawal");

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(300, 300);
				accountNum = JOptionPane.showInputDialog(null, "What is Your Account # ",
						JOptionPane.QUESTION_MESSAGE);
				if (accountNum.matches("[0-9,.]+"))  {
					account = Integer.parseInt(accountNum);
					}else {
						
						JOptionPane.showMessageDialog(null, accountNum+": Is not a valid account number! Try Again ", "WARNING", JOptionPane.WARNING_MESSAGE);
						//System.out.println("Try Again");
						break;
					}

				String withdraw = JOptionPane.showInputDialog(null,
						"How much would you like to withdraw " + JOptionPane.QUESTION_MESSAGE);
				if (withdraw.matches("[0-9,.]+"))  {
					amount = Double.parseDouble(withdraw);
				try {	
					
					//result=atmMain.with(account, amount, accounts,output);
					JOptionPane.showMessageDialog(null, atmMain.with(account, amount, accounts,output), "Reciept", JOptionPane.PLAIN_MESSAGE);
					break;
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(null, withdraw+": Is not a valid withdrawal amount! Try Again ", "WARNING", JOptionPane.WARNING_MESSAGE);
				//System.out.println("Try Again");
				break;
			}

			// case clear check
			case 'C':
			case 'c':
				atmMain.clearCheck(accounts, userIn, dollar, output);
				break;
			// case new account
			case 'n':
			case 'N':
				atmMain.newAcct(accounts, userIn, dollar, output);
				break;
			// case delete account
			case 'X':
			case 'x':
				atmMain.deleteAcct(accounts, userIn, dollar, output);
				break;

			case 'Q':
			case 'q':
				
				result = atmMain.quit(accounts, userIn, dollar, output);
				
				
				JTextArea textArea = new JTextArea();
			      textArea.setText(result);
			      textArea.setEditable(false);
			      JTextField text = new JTextField(15);
		
				 frame = new JFrame("Quit");
	

				// add the panel to a JScrollPane
				JScrollPane scrollBar=new JScrollPane(textArea); 
				
				// Then, add the jScrollPane to your frame
				//Add JScrollPane into JFrame 
				frame.add(text);
				frame.add(scrollBar);  
				  
				//Set close operation for JFrame  
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
				  
				//Set JFrame size  
				frame.setSize(800,800);  
				  
				//Make JFrame visible. So we can see it.  
				frame.setVisible(true);  
	
				
				
				 break;

			default:
				throw new InvalidMenuSelectionException(choice);
//			break;

			}
		} catch (InvalidMenuSelectionException | IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
		/*	output.println("");
			output.println("");
			output.println(ex.getMessage());
			output.println("");
*/
		}
	}
}

/*
 * if(e.getSource()== Deposit) { JFrame frame = new JFrame("Deposit");
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setSize(300,300);
 * String accountNum = JOptionPane.showInputDialog(null,
 * "What is Your Account # ", JOptionPane.QUESTION_MESSAGE); int account =
 * Integer.parseInt(accountNum);
 * 
 * 
 * String deposit = JOptionPane.showInputDialog(null,
 * "How much would you like to deposit " + JOptionPane.QUESTION_MESSAGE); int
 * amount =Integer.parseInt(deposit);
 * 
 * 
 * }
 * 
 */
