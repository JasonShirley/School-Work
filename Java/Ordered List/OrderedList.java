/* this is an object class for the program Driver.java
		
		
		* OrderedList takes a String input from Driver.java and does a few things with it.
		* Driver.java first sends the information to insert to set the value as a 
		* listNode. After the value is inserted the user can decided from Driver.java whether
		* they want to add a value (insert), delete a value (previous and delete), or find a
		* value (locate).
		
		Programmed by: Jason Shirley 
		Date: 04/16/17
*/


public class OrderedList
{
	private ListNode head; // defines head node
	
	public OrderedList ()
	{
		this.head = null; // sets default head node
	}
	

	/*
	insert
	
		* insert takes a String value that is passed from Driver.java and sets it as 
		* a listNode. This function checks to see if there is any values inside of the
		* list first. If there are no values it sets the value as the head of the list.
		* if there is a value it then checks if the next value needs to be inserted 
		* before the head. If the value isn't inserted before the head the function
		* then traverses the list to find where the value needs to be inserted. It checks
		* first if it needs to be inserted at the end of the list. If the value doesn't 
		* need to be inserted at the end of the list it insertes it in the proper location.
	*/
	
	public boolean insert (String value)
	{
		ListNode newNode = new ListNode(value);
		ListNode current = this.head;
		boolean test = false;

		if ( this.head == null ) // set value as head if the list is empty
			
		{
			current = newNode;
			this.head = current; 
			test = true;

		}
		else if 
		( current.getData().compareToIgnoreCase(value) > 0 ) // set value as new head if value passed needs
									// to be inserted as the new head
			
		{
				ListNode previousHead = this.head;
				this.head = newNode;
				newNode.setNext(previousHead); 
				test = true;
				
		}
		else 
		{	
			//check where value needs to be inserted
			while ( current.getNext( ) != null 
				  && current.getNext().getData().compareToIgnoreCase(value) <= 0) 
			{
				current = current.getNext ( );
			}
			if (current.getNext() == null && !(current.getData().equals(value))  ) 
			{
				current.setNext(newNode); // insert value at end
				test = true;
			}
			else if (!(current.getData().equals(value)) 
					&& (!(current.getNext().getData().equals(value)))) 
			{
				newNode.setNext ( current.getNext ( ) );
				current.setNext (newNode); // insert value at proper location
				test = true;
			}
	

		}
		return test;
	}

	// locate is used to find a value within the list.
	public ListNode locate ( String value )
	{
		ListNode current = this.head;
		while ( current != null && !(current.getData().equals(value)))
		{
			current = current.getNext();
		}
		return current;
	}

	// previous is used to find the value before the value searched. It sets
	// the pointer to the value before the value passed.
	
	public ListNode previous ( ListNode end )
	{
		ListNode current = this.head;
		
		while ( current != null && current.getNext( ) != end ) // search the list
		{
			current = current.getNext ( );
		}
		return current; // current will be null if end doesn't exist
	}
	/* 
	delete
	
		* delete is given a pointer node to remove from the list and 
		* the pointer node given from previous to redirect the link	
		* to go around the deleted node.
		* If the node that is deleated is at the head of the list,
		* then the node after the deleted head becomes the head.
	*/
	
	public void delete ( ListNode end )
	{	
		if (end == this.head) // then the new head is the node after head
		{
			this.head = this.head.getNext ( );
		}
		else
		{
			ListNode current = previous(end); // get the previous node
			current.setNext(end.getNext( ) ); // bypass the node to be deleted
		}
	}

	// toString sets the value as a String value to be printed in the Terminal window. 
   	public String toString ( )
   	{
   	     ListNode p = this.head;
   	     String s = "\n";
        
   	     while ( p != null )
   	     {
   	         s = s + p.getData ( ) + " " ;
   	         p = p.getNext ( );
   	     }
        
   	     return s;
   	 }


} // end of OrderedList class



// listNode is an object that is used for setting the values for listNode.

class ListNode
{
	private String data;
	private ListNode next;
	
	public ListNode () // Constructor node
	{
		this.next = null;
	}
	
	public ListNode (String value) // Constructor value
	{
		this.data = value;
	}
	
	public void setNext (ListNode value) // Mutator for next node.
	{
		this.next = value;
	}
	
	public void setData (String value) // Mutator for current node.
	{
		this.data = value;
	}
	
	public String getData () // Accessor for current node.
	{
		return this.data;
	}
	
	public ListNode getNext() // Mutator for next node.
	{
		return this.next;
	}
}	 

