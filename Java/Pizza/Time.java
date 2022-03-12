/* 
	Time

	Time is an object created for TestOfTime in order to take information in and to
	store it and manipulate it in various ways. The code first defaults Time values
	of minutes and hours as 0. Then the user in TestOfTime can choose to perform
	various tests such as formatting, difference between two times, incrementing a 
	minute to the minute variable, seeing if one time is later than another, copying
	the Time values and seeing if they have the same address, or seeing if two times
	equal eachother.


	* Programmer: Jason Shirley
	* Date: 02/03/2017
	
	* Updated 03/16/17 by Jason Shirley
	* A second constructor that made the code unsecure by allowing users
	* to change the value of Time has been deleated. This will ensure that
	* that data is more secure. 
	
*/




public class Time 
{
	private int minute;
	private int hour;
	
	public Time () // Constructor defaults hour and minute to 0
	
	{
		this.hour = 00;
		this.minute = 00;
	}
	


	/*	setTime
	
		setTime is a mutator method for a users input from TestOfTime for hour and minute.
		The user is given a prompt to enter in an hour and minute. Set time then
		checks if the hours are within 23 and minutes are within 59. If they are
		in range the method changes the values for hour and minute to the times
		inputed. If not the user is presented a message saying whether the hour,
		minute, or both are out of bounds. The value returns a String value for 
		the time.
	*/
	
	public String setTime (int newHour,int newMinute )
	
	{	
		String testTime = null;
		
		if ( newHour < 24 && newMinute < 60 )
		{
			this.hour = newHour;
			this.minute = newMinute;
		}
		
		if (newHour > 24 && newMinute > 60)
		{
			testTime = ("hour and Minutes out of bounds");
		}
		
		else if (newHour >= 24)
		{
			testTime = ("hour out of bounds (0-23)");
		}
		
		else if (newMinute >= 60)
		{
			testTime = ("minutes out of bounds (0-59)");
		}

	

	return testTime;
	
	}
	
	public String toString ( ) // sets Time to a String value
	
	{	
	
		return "Hour " + this.hour + " Minute " + this.minute;
	} 
	
	/*	ampmTime
		The user enters a time from TestOfTime in the 24 hour form. ampmTime formats the time to
		12 hour standard and calculates whether the time is AM (before 12) or 
		PM (after 12) and also whether the time is 12 noon or 00 midnight. ampmTime
		also buffers minute if the minute is less than 10 with a 0 (e.g. 7 = 07).
		The displayed time shows the time in 12 hour standard along with AM or 
		PM (e.g. 11 hour 00 minute = 11:00 Am, 13 hour 00 minute = 1:00 PM). The
		method returns a String value for the values saved to the variable for
		the formatted time. 
	
	*/
	
	public String ampmTime ( )
	
	{
		String amPmTime = "";
		String amPmHour = "" + this.hour;
		String amPmMinute = "" + this.minute ;
		
		if (this.minute < 10)
		{
			amPmMinute = "0" + this.minute;
		}
		
		if (this.hour >= 12)
		{
			if (this.hour == 12)
			{
				amPmTime = amPmHour + ":" + amPmMinute + " PM";
			}
			if ( this.hour > 12 )
			{
				amPmHour = "" + (this.hour - 12);
				amPmTime = amPmHour + ":" + amPmMinute + " PM";
			}
		}
		
		if (this.hour < 12)
		{
			amPmTime = amPmHour + ":" + amPmMinute + " AM"; 
		} 
		
		if (this.hour == 12 & this.minute == 00 )
		{
			amPmTime = "Noon";
		}
		
		if ( this.hour == 00 & this.minute == 00 )
		{
			amPmTime = "Midnight";
		}
		
		else if (this.hour == 00 & this.minute > 0)
		{
			amPmTime = "12" + ":" + amPmMinute + " AM";
		}
		
		return amPmTime;	
	
	}	 
	
	/*	milTime
	 
		milTime formats the users input from TestOfTime to 24 hour military time.
		The user enters a time from TestOfTime, milTime then starts by formatting
		the minutes entered by checking if the input for minute is less than 10. 
		If the input is less than 10 the method adds a 0 buffer to the minute (e.g. 7 = 07)
		and saves the formatted minutes to the variable militaryMinutes. The method 
		then checks hour to see if it is less than 10. If the input for hour is 
		less than 10 the method adds a 0 buffer to hour (e.g. 7 = 07) and saves 
		the formatted hour to the variable militaryHour. The method then saves
		the value for hour and minutes in a String variable called militaryTime.
		if the hour is greater than ten no formatting is done to the time and
		the value is saved into militaryTime along with minutes, even if minutes
		was formatted. The method returns the value for the variable saved for
		the formatted military time. 
	*/
	
	public String milTime ( )
	
	{
		String militaryTime = "";
		String militaryHour = "";
		String militaryMinute = this.minute + "";
		if (this.minute < 10)
		{
			militaryMinute = "0" + this.minute ;
		}
		
		if (this.hour < 10)
		{
			militaryHour = "0" + this.hour;
			militaryTime = militaryHour + militaryMinute + " hours";
		}
		
		if (this.hour >= 10) 
		{
			militaryTime = this.hour + militaryMinute +" hours";
		}
		
		return militaryTime;
	
	}	
	
	/* difference
	
		TestOfTime gives the user two inputs. The first input is saved into this.hour
		and this.minute and the second input is saved into the value for that.hour
		and that.minute. The two times are then subtracted from eachother and given
		back to TestOfTime to display the results to the user. The method first
		formats the times for this and that to minutes. The method then checks if
		this converted minutes time is less than that converted minutes time. If
		the test passes the method takes the difference between the two, divides
		by 60 to format back into hours, and calculates the minutes by taking a modulus
		of the difference and giving the remainder which the remainder is minutes.
		If this converted minutes is greater than that hour minutes the calculations
		are the same but we are expected to be given a negative answer. Because of 
		this case we add the total minutes that are in a 24 hour time span which 
		evaulates to 1440 to the difference and devided by 60 to convert to minutes.
		To get the minutes we do the same, add 1440 to the difference, but modulus
		the answer to get the remainder which will be used for our formatted minutes
		for the difference calculations. The returned total is converted to Time. 
		
	* Updated 03/16/17 by Jason Shirley
	* difference has been updated to not make use of the now deleated unsecure
	* second constructor for Time.
	*/ 
		
	public Time difference ( Time that )
	
	{
		int differenceMinutes = 0; 
		int differenceHour = 0; 
		int differenceTime = 0;
		int thisHourMinutes = (this.hour * 60) + this.minute;
		int thatHourMinutes = (that.hour * 60) + that.minute;
		Time timeDifference = new Time ();
		
		if (thisHourMinutes > thatHourMinutes)
		{

			differenceTime = thatHourMinutes - thisHourMinutes;
			timeDifference.hour = (differenceTime + 1440)/60;
			timeDifference.minute = ((differenceTime + 1440)%60);
		}
		
		if (thisHourMinutes < thatHourMinutes)
		{
			
			differenceTime = thatHourMinutes - thisHourMinutes;
			timeDifference.hour = (differenceTime)/60;
			timeDifference.minute = (differenceTime)%60;
		}
		
		
		return timeDifference;
	}
	
	/* increment
		increment adds one to the minute given by the users input. If incremented
		minutes equals 60 the method sets the minutes back to zero and adds one 
		hour to hour. 
	*/
	
    public void increment ( ) 
	{
		this.minute++;
		if (this.minute == 60)
		{
			this.minute = 0;
			this.hour++;
		}
		if (this.hour == 24 && this.minute == 00)
		{
			this.minute = 0;
			this.hour = 0;
		}
	}	
	
	/*	later
		later is a tests two inputed from TestOfTime. We set a boolean test to 
		false starting out. It becomes true if the values for this hour plus 
		this minute are greater than that hour and that minute. It remains false 
		otherwise. The method returns the boolean value. 
	*/
	
	public boolean later ( Time that )
	
	{
		boolean test = false;
		
		if (this.hour == that.hour)
		{
			if (this.hour + this.minute > that.hour + that.minute)
			{
			return true;
			}
		}
		if (this.hour > that.hour)
		{
			return true;
		}
		return test; 
	}
	
	/*	equals
		equals checks to see if two inputed times are the same. The method starts
		with a false boolean variable and becomes true if the values for this hour
		and this minute are the same as that hour and that minute. The method returns
		the boolean value. 
	*/
	
	public boolean equals ( Time that )
	
	{
		boolean equals = false;
		if ((this.hour == that.hour) && (this.minute == that.minute))
		{
			equals = true;
		}
		return equals;
	}	
	
	/*	copy
		copy checks if the addresses of two time objects that the user inputs
		from TestOfTime are the same. The method starts with a boolean value that
		equals false and becomes true if the addresses of this hour and this minute
		are the same as that hour and that minute and returns the boolean value. 
	*/
		
	public Time copy ( )
	{
		Time dupe = new Time ( );
		dupe.hour = this.hour;
		dupe.minute = this.minute;

		return dupe;
	}
}	
