/*	This program is a test driver for the OrderedList class.
	
	The list contains string data, and is initialized from a
        text file. Operations supported by this driver are: 
		Add a value to the database (using insert)
		Delete a value from the database
		Look up a value (locate)
		Show all values (toString)
		        
        Written by Quick-draw McGraw on 1 April 2012
*/
import javax.swing.*;
import java.io.*;
import java.util.StringTokenizer;

public class Driver
{
	public static void main ( String args [ ] )
	{
		OrderedList l = new  OrderedList ( );
		
		if ( loadDataBase ( l ) )
		{
			processTransactions ( l );
		}
				
		System.exit ( 0 );
	}
	
	/*	loadDataBase opens a text file which has one line for 
		each value in the database, and then reads the lines and
		adds a record for each one. If there are any I-O problems
		this method returns false.
	*/
	
	static boolean loadDataBase ( OrderedList db )
	{
		boolean allOkay = true;

		try 
		{
			FileReader inFile = new FileReader ( "TestData.txt" );
			BufferedReader in = new BufferedReader ( inFile );
			
			String lineIn = in.readLine ( );
			
			while ( !( lineIn == null ) )
			{
				db.insert ( lineIn );
				
				lineIn = in.readLine ( );
			}
			inFile.close ( );
			
		} // end of try
		
		catch ( Exception problem )
		{
			System.out.println ("Trouble! " + problem);
			allOkay = false;
		}
		
		return allOkay;
	} // end of method loadDataBase
        
        
	/*	processTransactions 
		This method repeatedly presents a menu of choices to the user
		and solicits a choice. If the user makes a valid menu choice, 
		this method transfers control to a method that handles the choice,
		or exits if the choice is "Q to Quit." If the user enters a
		choice that is not in the menu, the error is displayed and the 
		user is asked to choose again.
	*/
	static void processTransactions ( OrderedList db )
	{
            String command, prompt;
		
            String stdPrompt = "Please enter\n\t\tA to Add a value\n" +
                "\t\tD to remove a value\n\t\tF to find a value\n\t\t" +
                "P to show all\n\t\tQ to Quit";
			
            String errorReport = "**** You entered ";
		
            command = JOptionPane.showInputDialog ( stdPrompt );
		
            while ( !( command.equalsIgnoreCase ( "q" ) ) )
            {
                prompt = stdPrompt;
       
                if ( command.equalsIgnoreCase ( "A" ) )
                {
                    addToList ( db );
                }
                else if ( command.equalsIgnoreCase ( "D" ) )
                {
                    delete ( db );
                }
                else if ( command.equalsIgnoreCase ( "F") )
                {
                    find ( db );
                }
                else if ( command.equalsIgnoreCase ( "P" ) )
                {
                    JOptionPane.showMessageDialog (
                        null, db.toString ( ), "Database",
                        JOptionPane.INFORMATION_MESSAGE );
                }
                else if ( !( command.equalsIgnoreCase ( "Q" ) ) )
                {
                    // modify the prompt to show that the user erred
				
                    prompt = errorReport + command + "****\n" + stdPrompt;
                }
                command = JOptionPane.showInputDialog ( prompt );
                
            } // end while
            
            System.exit ( 0 );
            
	} // end of method processTransactions
	
	/*	addToList
		This method solicits a string input and then uses the list class
		insert method to add the value to the list. If the insert method 
		returns false, display an slert for the user.
	*/
	static void addToList ( OrderedList l )
	{
            String newVal = JOptionPane.showInputDialog (
                            null, "Enter the new value:");

            if (!(l.insert ( newVal )))
			{	
				JOptionPane.showMessageDialog (
                        null, "Value was duplicate. Not added.", "Warning",
                        JOptionPane.WARNING_MESSAGE );
			}
	}

	/*	delete
		Ask the user what value to delete, then locate the position
		and call the delete method using the position. 
	*/
	static void delete ( OrderedList l )
	{
            String nameIn = JOptionPane.showInputDialog (
                            null, "Remove what value?");
		
            ListNode p = l.locate ( nameIn );

            if ( p != null )
            {
                l.delete ( p );
                JOptionPane.showMessageDialog (
                    null, nameIn + " has been deleted",
                    "Deletion Status", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog (
                    null, nameIn + " is not in the database",
                    "Deletion Status", JOptionPane.WARNING_MESSAGE);
            }
                    
	}

	/*	find
		This method asks the user to give a value to find, and then 
		calls the locate nethod. It then opens a dialog box to inform
		the user whether the valuse was found.
	*/
	static void find ( OrderedList l )
	{
            String searchName = JOptionPane.showInputDialog (
				null, "Find what value?");
		
            ListNode p = l.locate ( searchName );

            if ( p == null )
            {
                JOptionPane.showMessageDialog (
                    null, searchName + " is not in the database",
                    "Find Status", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog (
                    null, searchName + " was found",
                    "Find Status", JOptionPane.INFORMATION_MESSAGE);
            }
	}

}
