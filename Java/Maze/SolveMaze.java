/*	SolveMaze.java
	Solve Maze is a program that is designed to solve mazes created by Dr. Richard Croft.
	His maze builder program builds a maze and the Solve Maze program is a driver to solve
	it. It reads the values in the maze and makes a decision on where to turn. While 
	traversing the maze it sets markers on every move it makes using a stack class. If the
	program runs into a wall it can pop the stack back to a position to before it got stuck.
	If it successfully traverses the maze, it will display a message to the effect. If there
	is no way out the program will return the mover marker back to the starting position and
	display a message that there was no way out of the maze.
	
	Programmed by: Jason Shirley
	Date: 05/04/17
*/

public class SolveMaze
{
	public static void main (String args[ ])
	{
		String mazeFileName = "Maze1.txt";

		if (args.length > 0)
		{
			mazeFileName = args[0];
		}

		Maze theMaze = new Maze (mazeFileName);

		Stack returns = new Stack();		// Stack variable is used for saving moved positions
							// or popping positions back to a position to before
							// the program got stuck.
		returns.push(theMaze.getCurrent());
		boolean ackbarsTrap = false;		// ackbarsTrap is used in case the program 
							// can't find a way out of the maze
		
		while(!(theMaze.getCurrent().equals(theMaze.getFinish())) && !ackbarsTrap)
		{

			boolean turns = false;		// turns is used for if a move was successfully made. 
							// It's defaulted to false to state that no moves were
							// made


			if (theMaze.look('w') == ' ')	// If a westward move is possible, set a marker
							// and move on in that direction.
			{
				theMaze.go('w');
				returns.push(theMaze.getCurrent());	
				turns = true;		// A move was made so set turns to true.
			}
			if (theMaze.look('n') == ' ')	// If a northward move is possible, set a marker
							// and move on in that direction.
			{
				theMaze.go('n');
				returns.push(theMaze.getCurrent());
				turns = true;		// A move was made so set turns to true. 
			}
			if (theMaze.look('s') == ' ')	// If a southward move is possible, set a marker
							// and move on in that direction.
			{
				theMaze.go('s');
				returns.push(theMaze.getCurrent());
				turns = true;		// A move was made so set turns to true.
			}
			if (theMaze.look('e') == ' ')	// If a eastward move is possible, set a marker
							// and move on in that direction.
			{
				theMaze.go('e');
				returns.push(theMaze.getCurrent());
				turns = true;		// A move was possible so set turns to true.
			}	

			if (turns == false)		// If turns is false, then a move wasn't possible.
			{


				if ( (theMaze.getStart() == returns.top()) )
				{

					ackbarsTrap = true;	// This means that the maze was impossible to solve.
				
				}
				else
				{
					returns.pop();		// We were cornered, so pop the stack.
					theMaze.setCurrent(returns.top());	// Once we pop, set the current position.	
				}
			}
			System.out.println(theMaze.showMaze(
			"AFTER MOVE "+theMaze.getMoveCount()));
		
		

			}// end while
		
			if ((theMaze.getStart() == returns.top()))
			{
				System.out.println("Cave In");
			}
			else 
			{
				System.out.println("Success");
			}

	}
}


/* The stack class is used to accompany the main class of Solve Maze in order to 
build a stack to save moved positions. We utilize the stack such that if a move was made,
we store it into the stack and if a move was not possible, we pop the stack to the next 
available move.
*/
class Stack 		
{
	private Position [ ] s;
	private int top;

	public Stack ( )
	{
		s = new Position [1000];
		top = -1;
	}

	public Stack (int size)
	{
		s = new Position [size];
		top = -1;
	}
		
	public Position top ()
	{
		return s[top];
	}
	
	public void push ( Position value)
	{
		if (top < s.length - 1)
		{
			top ++;
			s[top] = value;
		}
	}
	public void pop()
	{
		if (top > 0)
		{
			top--;
		}
	}
	public void makeNull()
	{
		top = -1;
	}
	public boolean isEmpty()
	{
		return top == -1;
	}
	
}

