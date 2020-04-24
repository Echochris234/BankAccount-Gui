package Info;

import java.io.Serializable;

// Depositor Class fills in personal information about the client
public class Depositor implements Serializable {

	// Private fields to protect data from corruption
	private String social;
	private Name name;

	// NO ARGS CONSTRUCTOR HOLDS NAME OBJECT AND SOCUAL
	public Depositor() {
		name = new Name();
		social = "";

	}

	// CONSTRUCTOR PASSED NAME OBJECT AND SOCIAL
	public Depositor(Name called, String ssn) {
		
		name = called;
		social = ssn;

	}

	// Copy Costructor
	public Depositor(Depositor myInfo) {
		social = myInfo.social;
		
		name= new Name(myInfo.name);
	}
	
	// returns the contents of the depositor class as string
	public String toString() {
		String str = String.format("%-13s", getSocial());
		return str;
	}
	
	// compares 2 depositor objects returns true or false
	public boolean equals(Depositor info) {
		if(info.getSocial().equals(getSocial()))	
			return true;
		else
			return false;
	}
	// setSocial sets social to string passed to method
	public void setSocial(String str) {
		social = str;
	}

	// getSocial returns string stored in social
	public String getSocial() {
		return social;
	}

	// SETS NAMES TO NAME
	public void setName(Name names) {
		name = new Name(names);
	}

	// //returns refrence to copy of Name ObjectR
	public Name getName() {
		return new Name(name);
	}
	

}
	