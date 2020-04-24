package Info;

import java.io.Serializable;

public class Name implements Serializable  {
	// HOLDS FIRST AND LAST NAME
	private String first;
	private String last;


	// NO ARGS CONSTRUCTOR
	public Name() {
		first = "";
		last = "";
	}
	//copy constructor
	public Name(Name myName) {

		this.last = myName.last;
		this.first = myName.first;
		
	}
	// returns string of the contents of the Name class
	public String toString() {
		String str = String.format("%-5s %-5s", getFirst(),getLast());
		return str;
	}

	// compares 2 name objects returns true or false
	public boolean equalsName(Name myName) {
		if(myName.getFirst().equals(getFirst())&&
			myName.getLast().equals(getLast()))
			return true;
			else
				return false;
	}
	// CONSTRUCTOR PASSED LAST AND FIRST NAME
	public Name(String lStr, String fStr) {

		first = fStr;
		last = lStr;
		
	}

	// setLast sets last to string passed to method
	public void setLast(String str) {
		last = str;

	}

	// setFirst sets first to string passed to method
	public void setFirst(String str) {
		first = str;

	}

	// getLast returns string stored in last
	public String getLast() {
		return last;

	}

	// getFirst returns string stored in first
	public String getFirst() {
		return first;

	}
}