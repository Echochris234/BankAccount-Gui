package Transactions;
import java.io.Serializable;
import java.util.Calendar;

// HOLDS YEAR/MONTH/DAY/ AND MATURITY LENGTH
public class DateInfo implements Serializable {
	private int year;
	private int month;
	private int dayOfMonth;
	private int matureLength;

	//NO ARGS CONSTRUCTOR
	public DateInfo() {
		year = 0;
		month = 0;
		dayOfMonth = 0;
		matureLength = 0;

	}
	

	//CONSTRUCTOR PASSED YEAR MONTH DAY AND MATURITY LENGTH
	public DateInfo(int yeer, int mnth, int day) {
		year = yeer;
		month = mnth;
		dayOfMonth = day;
	}
	//Copy Constructor
	public DateInfo(DateInfo date) {
		year = date.year;
		month = date.month;
		dayOfMonth = date.dayOfMonth;
		matureLength = date.matureLength;
		
	}
	
	//CONSTRUCTOR Overloaded
		public DateInfo(String yeer, String mnth, String day, String length ) {
		year = Integer.parseInt(yeer);
		month = Integer.parseInt(mnth);
		dayOfMonth = Integer.parseInt(day);
		matureLength = Integer.parseInt(length);
	}
		//CONSTRUCTOR Overloaded
		public DateInfo(int yeer, int mnth, int day, int length ) {
		year = yeer;
		month = mnth;
		dayOfMonth = day;
		matureLength = length;
	}
	
	//SETS YEAR
	public void setYear(int yeer) {
		year = yeer;
	}
	
	
	// OVERLOADED METHOD SETS YEAR
	public void setYear(String yeer) {
		year = Integer.parseInt(yeer);
	}
	
	//RETURNS YEAR
	public int getYear() {
		return year;
	}
	
	
	//SETS MONTH
	public void setMonth(int mnth) {
		month = mnth;
	}
	
	public void setDate(DateInfo date) {
		year = date.getDay();
		month = date.getMonth();
		dayOfMonth = date.getDay();
		//matureLength = date.getMatureLength();
	}
	
	//OVERLOADED METHOD SETS MONTH
	public void setMonth(String mnth) {
		month = Integer.parseInt(mnth);
	}
	//RETURNS MONTH
	public int getMonth() {
		return month;
	}


	//SETS day
	public void setDayOfMonth(int day) {
		dayOfMonth = day;
	}

	//OVERLOADED METHOD SETS DAY
	public void setDayOfMonth(String day) {
		dayOfMonth = Integer.parseInt(day);
	}

	//RETURNS DAY
	public int getDay() {
		return dayOfMonth;
	}


	//RETURNS MATURE LENGTH
	public int getMatureLength() {
		return matureLength;
	}
	//SETS LENGTH TO MATURELENGTH
	public void setMatureLength(int length) {
		matureLength=length;
	}
	
	//Sets Length to MatureLength overloaded
	public void setMatureLength(String length) {
		matureLength=Integer.parseInt(length);
	}
	
	
	
	//SETS THE DATES 
	public void setMaturityDate(DateInfo date) {
		year=date.getYear();
		month=date.getMonth();
		dayOfMonth=date.getYear();
		matureLength = date.getMatureLength();
		//matureLength=maturityLength;

	}
	
	
	//SETS THE DATES OVERLOADED
	public void setMaturityDate(String yearOpened,String monthOpened ,
					String dayOpened, String maturityLength) {
		year=Integer.parseInt(yearOpened);
		month=Integer.parseInt(monthOpened);
		dayOfMonth=Integer.parseInt(dayOpened);
		matureLength=Integer.parseInt(maturityLength);
	}

	// to string method for the date
	public String toString() {
		String str;
		str = String.format("%-1s/%-1s/%-10s",
				getMonth(),getDay(),
				getYear());
		return str;
	}
	
	// returns the date cd matures based on string passed
	public String getMatureInfo() {
		String str;
		int yeer,mnth,dey;
		//creates a calendar to hold mature date
		Calendar matureDate = Calendar.getInstance();
		matureDate.clear();
		//sets the date of current Cd
		matureDate.set(Calendar.MONTH, getMonth());
		matureDate.set(Calendar.YEAR, getYear());
		matureDate.set(Calendar.DATE, getDay());
		// ADDS TO THE MONTH OF matureDate THE LENGTH OF MATURITY
		matureDate.add(Calendar.MONTH, +getMatureLength());
		yeer = matureDate.get(Calendar.YEAR);
		mnth =  matureDate.get(Calendar.MONTH);
		dey = matureDate.get(Calendar.DATE);
		
		DateInfo mature = new DateInfo(yeer, mnth, dey);
		
		if(mature.getMonth()== 0)
			mature.setMonth(1);
		str = String.format("%-1s/%-1s/%-10s",
				mature.getMonth(),mature.getDay(),
				mature.getYear());

			// to show fail
			return str;
		
	}

	//CHECK CD METHOD CHECK IF THE CD ACCOUNT IS VALID
	public int checkCD() {
		//CREATES CALENDAR CLASS AND SETS DATE
		//STORED IN PRIVATE FIELDS
		Calendar cD = Calendar.getInstance();
		cD.clear();
		cD.set(Calendar.MONTH, getMonth());
		cD.set(Calendar.YEAR, getYear());
		cD.set(Calendar.DATE, getDay());
		
		//CREATES A CALENDAR CLASS WITH TODAYS DAY
		Calendar today = Calendar.getInstance();
		today.clear();
		today.set(Calendar.YEAR, 2019);
		today.set(Calendar.MONTH, Calendar.MARCH);
		today.set(Calendar.DATE, 20);
			
		// ADDS TO THE MONTH OF CD THE LENGTH OF MATURITY
		cD.add(Calendar.MONTH, +getMatureLength());
		//CHECKS TO SEE IF THE CD AGED TO TODAYS DATE TO PERFORM OPERATIONS
		// if CD matured DATE IS BEFORE today or after RETURN -1
		if (cD.before(today)||cD.equals(today)) {
			return 1;
			
			// else  if the mature date is after today RETURN 0
			//false
		} else
			return -1;
	}
	
	// returns the date cd matures based on string passed
		public String matureToString() {

			return toString();

		}



}
