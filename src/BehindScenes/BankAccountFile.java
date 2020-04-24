package BehindScenes;
import java.io.*;
import java.text.DecimalFormat;

import AccountTypes.*;
import Info.*;
import Transactions.*;

public class BankAccountFile {
	static int count = 0;
	private final int RECORD_SIZE=189;
	private RandomAccessFile file;
	DecimalFormat dollar = new DecimalFormat("$#,###.##");

	/**
	 * The constructor opens a random access file for both reading and writing.
	 * 
	 */

	public BankAccountFile(String fileName,String type) throws FileNotFoundException {

		// Open the file for reading and writing.
		file = new RandomAccessFile(fileName, type);
		

	}

	/**
	 * The writeItem method writes the contents of an Account object
	 * to the file at the current file pointer position.
	 * 
	 */

	public void writeItem(Account acct) throws IOException {
		

		// Get the item's description.
		String first = acct.getDepositor().getName().getFirst();
		String last = acct.getDepositor().getName().getLast();
		// Write the description.
		if (first.length() > 20) {
			// If there are more than 20 characters in the
			// string, then write only the first 20.
			for (int i = 0; i < 20; i++)
				file.writeChar(first.charAt(i));

		} else {
			// Write the description to the file.
			file.writeChars(first);

			// Write enough spaces to pad it out
			// to 20 characters.
			for (int i = 0; i < (20 - first.length()); i++)
				file.writeChar(' ');
		}

		if (last.length() > 20) {
			// If there are more than 20 characters in the
			// string, then write only the first 20.
			for (int i = 0; i < 20; i++)
				file.writeChar(last.charAt(i));
		} else {
			// Write the description to the file.
			file.writeChars(last);

			// Write enough spaces to pad it out
			// to 20 characters.
			for (int i = 0; i < (20 - last.length()); i++)
				file.writeChar(' ');
		}

		String ssn = acct.getDepositor().getSocial();
		if (ssn.length() > 20) {
			// If there are more than 20 characters in the
			// string, then write only the first 20.
			for (int i = 0; i < 20; i++)
				file.writeChar(ssn.charAt(i));
		} else {
			// Write the description to the file.
			file.writeChars(ssn);

			// Write enough spaces to pad it out
			// to 20 characters.
			for (int i = 0; i < (20 - ssn.length()); i++)
				file.writeChar(' ');
		}

		String type = acct.getType();
		if (type.length() > 20) {
			// If there are more than 20 characters in the
			// string, then write only the fi rst 20.
			for (int i = 0; i < 20; i++)
				file.writeChar(type.charAt(i));
		} else {
			// Write the description to the file.
			file.writeChars(type);

			// Write enough spaces to pad it out
			// to 20 characters.
			for (int i = 0; i < (20 - type.length()); i++)
				file.writeChar(' ');
		}
		
		// writes acctNum and balance
		int acctNum = acct.getAcctNum();
		double balance =acct.getBalance();
		
		file.writeInt(acctNum);
		file.writeDouble(balance);

		// writes boolean acct status
		file.writeBoolean(acct.getAcctStats());
		
		//writes date information
		DateInfo date = new DateInfo(acct.getDate());
		
		file.writeInt(date.getMonth());
		file.writeInt(date.getDay());
		file.writeInt(date.getYear());
		file.writeInt(date.getMatureLength());
		


	}

	// get the bytenumber
	private long getByteNum(long recordNum) {
		return RECORD_SIZE * recordNum;
	}

	/**
	 * The readInventoryItem method reads and returns the record at the current file
	 * pointer position.
	 * 
	 * @return A reference to an InventoryItem object.
	 * @exception IOException When a file error occurs.
	 */

	public Account readAccountObject() throws IOException {
		Account cuenta;
	

		char[] charArray = new char[20];

//gets the first name
		for (int i = 0; i<20; i++)
			charArray[i]=file.readChar();

			String fName = new String (charArray);
			
		// Store the char array in a String.
		//String first = new String(charArray);

		// Trim any trailing spaces from the string.
		fName=fName.trim();


//gets the last name
		for (int i = 0; i < 20; i++) 
			//charArray[i] = file.readChar();
			charArray[i]=file.readChar();

		// Store the char array in a String.
		String last = new String(charArray);

		// Trim any trailing spaces from the string.
		last=last.trim();
		
		Name myName = new Name(last,fName);

		
		//gets social
		for (int i = 0; i < 20; i++) 
			//charArray[i] = file.readChar();
			charArray[i]=file.readChar();
	
		// Store the char array in a String.
		String ssn = new String(charArray);
		
		ssn=ssn.trim();
		Depositor depo = new Depositor(myName,ssn);
		
		
		//gets type
		for (int i = 0; i < 20; i++) 
			//charArray[i] = file.readChar();
			charArray[i]=file.readChar();
		
		
		
		// Store the char array in a String.
		String type = new String(charArray);
		
		type=type.trim();
   
		
		//gets account number
		int acctNum = file.readInt();
		
		
		//gets balance
		double balance = file.readDouble();
		
		
		
		// gets status		
		boolean stat = file.readBoolean();

		// if equals cd
		if(type.equalsIgnoreCase("CD")) {
			// read the date
			int month= file.readInt();
			int day = file.readInt();
			int year = file.readInt();
			int mature = file.readInt();
			DateInfo date = new DateInfo(year,month,day,mature);

			// creates new CDAccount
			cuenta = new CDAccount(depo,acctNum,type,date,balance,stat);
			
			
			
			// else creates CheckingAccount
		}else if(type.equalsIgnoreCase("Checking")) {
			cuenta = new CheckingAccount(depo,acctNum,type,balance,stat);
			
			
			// else creates SavingsAccount
		}else {
			cuenta = new SavingsAccount(depo,acctNum,type,balance,stat);
			

		}

		//close();

		// returns account
		return cuenta;
	}
	
	

	/**
	 * The moveFilePointer method moves the file pointer to a specified record.
	 *
	 */

	public void moveFilePointer(long recordNum) throws IOException {
		file.seek(getByteNum(recordNum));
	}

	/**
	 * The getNumberOfRecords method returns the number of records stored in the
	 * file.
	 */

	public long getNumberOfRecords() throws IOException {
		return file.length() / RECORD_SIZE;
	}

	/**
	 * returns file size
	 */
	
	public long fileSize() throws IOException {
		long size=file.length();
		return size;
	}
	
	
	/**
	 * The close method closes the file.
	 */

	public void close() throws IOException {
		file.close();

	}
	/**
	 * sets the new length
	 */
	public void erase (long num) throws IOException {
		file.setLength(num);
	}


}