/*	The Godfather's Pizza Company Deliveries Database

	Maintain a database of information about pizzas delivered for The 
	Godfather's Pizza Company.

	This program is being developed in stages. Early stages will
	employ a limited database for testing purposes.

	Initial version: manual input of all information, which includes
	order number (up to six digits) and customer last name. After entry 
	of information, allow user to look up order by number, display all
	information, or quit.

	Programmer:	Sargent Pepperoni
	Date:		01/08/2014
	
	Revised by: Jason Shirley
	Date:		02/19/17
	
	Revised search method by Jason Shirley 02/05/17
	* Reviesed search to do a binary instead of a linnear search
	
	Added sortValues method by Jason Shirley 02/05/17 
	* sortValues now sorts the inputs numerically from lowest number to 
	* highest number along with corresponding name
	
	Revised search method by Jason Shirley 02/19/17
	* search had issues where when looking for orders that 
	* were less than array length, it would enter infinte loop. 
	* Adjusted search to end infinite loop issue.
	
	Revised code to implement PizzaRec class 02/19/17
	* PizaRec now handles information entered by the user. The code now 
	* creates an empty array for PizzaRec to store values into and PizzaRec
	* now does the work of storing and returning the values entered by the
	* user. 
	
	Revised loadDataBase to upload file 03/10/17
	* loadDataBase now takes file inputs and reads the information from the 
	* file and prints off the information in terminal. It also will catch
	* corrupted data types and report it in terminal. 
*/

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class jshirleyP5
{
	public static void main (String [] args)
	{
		PizzaRec dataInformation [] = new PizzaRec [100]; // creates empty 5 space
		//array called dataInformation for PizzaRec to store information into

		// end is index after last used position

		int end = loadDataBase (dataInformation); 

		if (end > 0)		// there is something to work with
		{
			sortValues (dataInformation, end);
			processTransactions (dataInformation, end);
				 
		}// ends if

		System.exit(0);
	}
	
	/*	loadDataBase

		Given empty int and String arrays for storing pizza order data, 
		request user to input order numbers and matching names to 
		populate the arrays. Return the umber of entries (the index of 
		the position following the last one used).


	* Revised 02/19/2017 by Jason Shirley
	* loadDataBase now makes use of PizzaRec by storing the variables into 
	* the array for PizzaRec. When dataInformation is called in other 
	* methods, the infomation in the array is used. 
	
	* Revised 03/10/17 by Jason Shirley
	* loadDataBase now is able input files and report what is in the file 
	* into terminal. It can print out the customers name, pizza type, order
	* time, delivery time, and time to deliver pizza. The method uses Time 
	* to test the times whether they are in bounds of hour 0-23 and minute 
	* 0-59 and converts a 24 hour time to ampm. PizzaRec saves the customers
	* information and formats it to a printable state.  
	*/	
	
	static int loadDataBase (PizzaRec [] dataInformation)
	{
		int i = 0;
		Scanner in;
		boolean ioProblem = false;
		String currentLine [ ];
		int orderNum = 0;
		int pizzaType = 0;
		int orderHour = 0;
		int orderMinute = 0;
		int deliveryHour = 0;
		int deliveryMinute = 0;
		int savedOrderTime = 0;
		int savedDeliveryTime = 0;
		int count = 1;
		Time deliverySetTime = new Time ();
		Time orderSetTime = new Time ();
		Time differenceTime= new Time ();
		




	try
	{
		in = new Scanner (new File ("PizzaDB.txt"));
		while (in.hasNext())
		{					
			currentLine = in.nextLine().split("#");
			
			orderNum  = Integer.parseInt(currentLine[0]);
			pizzaType = Integer.parseInt(currentLine[2]);
			orderHour  = Integer.parseInt(currentLine[3]);			
			orderMinute = Integer.parseInt(currentLine[4]);
			deliveryHour = Integer.parseInt(currentLine[5]);
			deliveryMinute = Integer.parseInt(currentLine[6]);
						
			String orderTime = orderSetTime.setTime(orderHour, orderMinute);
			String deliveryTime = deliverySetTime.setTime(deliveryHour, deliveryMinute);
		
		
			if (orderTime == null && deliveryTime == null)
			{
				orderTime = orderSetTime.ampmTime();
				deliveryTime = deliverySetTime.ampmTime();
				differenceTime = orderSetTime.difference (deliverySetTime);
				dataInformation [i] = new PizzaRec (orderNum, currentLine[1], pizzaType, orderTime, deliveryTime, differenceTime);
				i++;
			}

		
			else
			{

				if ( orderTime != null && deliveryTime != null ) 
				{
					System.out.println ("---->ERROR: LINE " + (count) + " order has " + orderTime + " delivery has " + deliveryTime);
				}
				else if (deliveryTime  != null )
				{
					System.out.println ("---->ERROR: LINE " + (count) + " delivery has " + deliveryTime);
				}
				else if (orderTime != null ) 
				{
					System.out.println ("---->ERROR: LINE " + (count) + " order has " + orderTime);
				}
			}
			count ++;

	}
	
	in.close ( );
		
	}
	catch ( IOException problem )
	{
		ioProblem = true;
		System.out.println ("I-O exception: " + problem);
	}
	return i; 
	}// ends loadDataBase 
	
	
	/* sortValues
	
	sortValues is a method used to sort the users input from loadDataBase
	of the orderNumber[] and custName[]. The method sorts the orderNumber[]
	numerically from lowest number to highest number using a selection sort
	while simultaneously sorting the customers name assicated with the order
	number. 
	
	added by Jason Shirley 02/08/16

	* Revised on 02/19/2017 by Jason Shirley
	* sortValues now uses PizzaRec to call for the information stored into
	* dataInformation array and sorts the information numerically with 
	* customer name assoicated with it from lowest number to highest number. 
	*/
			
	static void sortValues (PizzaRec[] hist, int last)
	{
		PizzaRec tempRecord; 
		int minPos;

		int i, j;		// loop indices

		for(i = 0; i< last-1; i++)
		{
			minPos = i;
			for(j = i+1; j<last; j++)
			{
				if (hist[j].getOrder() < hist[minPos].getOrder()) 
				// this one is smaller--remember its position
				{
					minPos=j;
				}
			}
			if (minPos != i)		// swap the two 
			{
				tempRecord = hist [minPos];
				hist [minPos] = hist [i];
				hist [i] = tempRecord;
			}
		}
	}  // end of sort method

		

	 
	/*	processTransactions

		Given arrays containing the order numbers and corresponding names,
		and the number of values actually being used, offer the user a 
		choice of actions: showAll values, which calls a method that 
		displays a report of the entire database; LookUpOrder,
		which calls a method that lets the user indicate a value to search; 
		and Quit, which termnates processing. 
		

		Any invalid choice is ignored.
		
		* Revised on 02/19/2017 by Jason Shirley
		* processTranactions now uses PizzaRec [] dataInformation to send
		* stored values to other methods. 
	*/

	

	static void processTransactions(PizzaRec [] dataInformation, int last)
	{
		String choice = JOptionPane.showInputDialog(null,
			"Please enter \n'L' to Look up an order number,\n\t" +
			"'S' to Show all orders, or\n\t'Q' to end");

		while ( !(choice.equalsIgnoreCase("Q") ) )
		{
			if (choice.equalsIgnoreCase("S") )
			{
				showAll( dataInformation, last );
			}
			else if (choice.equalsIgnoreCase("L") )
			{
				lookUpOrder ( dataInformation, last );
			}
			choice = JOptionPane.showInputDialog(null,
				"Please enter \n'L' to Look up an order number\n\t" +
				"'S' to Show all orders, or\n\t'Q' to end");
		}  // end while
	}  // end processTransactions method
	

	static void showAll (PizzaRec [] dataInformation, int last )
	{
		System.out.println ( "\tThe Godfathers Pizza" + 
		"\n\t    Order History" + "\n********************************" + "\n" +
		"\n");
		for (int i = 0; i < last; i++)
			{
			System.out.println ( dataInformation[i].toString ());
			

			
			}// end of for loop 
		System.out.println ("\t End Of Report");
	} // end showAll method 
	
	
	/*	lookUpOrder

		Given int and String arrays storing oder data, and the number 
		of positions of the arrays that are in use, ask the user to enter 
		an order number to look up. If the entry is not null, call a search
		method to locate the value and	show database entries for the 
		position if it was found or report the failure.
		
		* Revised by Jason Shirley on 02/19/17
		* lookUpOrder now uses the PizzaRec [] dataInformation array
		* to look up order information using the information stored in
		* the array.  
	*/
	
	
	static void lookUpOrder(PizzaRec [] dataInformation, int last)
	{
		int searchOrder;		
		String customer;

		String inputValue = JOptionPane.showInputDialog ( null,
			"Enter the order you want to look up ('cancel' to skip)");
		
		if (inputValue != null)		// look for it
		{
			searchOrder = Integer.parseInt (inputValue);

			int position = search (last, dataInformation, searchOrder);

			if (position >= 0)	// success
			{
				customer = dataInformation [position].getCustomer();

				JOptionPane.showMessageDialog(null,"Order: "+searchOrder
					+ "   Customer: " + customer);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Order " + inputValue +
					" is not listed");
			}  // end if
		} // end if
	} // end lookUpOrder
	
	/* Search
	The search method uses information passed to it by lookUpOrder to find
	a customers information using the order number. The order number is 
	passed to the search method and finds the index that holds the order 
	number and passes returns that value to lookUpOrder to find a customers
	information.
	
	added by Jason Shirley 01/26/17
	
	revised by Jason Shirley 02/02/17
	*The search method has been updated to use a binary search instead of 
	*a linnear search. Using this method will prove to be more useful in 
	*future additions to orgainzing the information inputed.
	
	revised by Jason Shirley 02/19/17
	* search now uses information stored in PizzaRec [] dataInformation
	* to perform the search. Infinite loop error was also fixed.
	*/
	static int search (int last, PizzaRec [] dataInformation, int searchOrder)
	{
		int startPosition= 0;
		int lastPosition = last-1;
		int middlePosition = (startPosition + lastPosition)/2;
		while ( startPosition <= lastPosition && dataInformation[middlePosition].getOrder() != searchOrder )
		{
			if ( dataInformation[middlePosition].getOrder() < searchOrder )
			{
				startPosition = middlePosition + 1;
			}
			else
			{
				lastPosition = middlePosition -1;
			}
				middlePosition = (startPosition + lastPosition)/2;		
				
		}// ends while loop
		if (startPosition > lastPosition)
		{
			middlePosition = -1;
		}	
		return middlePosition;
	}//ends for loop added by Jason Shirley 01/27/17	

} // end of program
	
		

