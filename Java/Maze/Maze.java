/*	Maze class

    This class represents a rectangular maze based on an input file.
    The file includes a line with the height and width of the maze,
    a line with the coordinates of the start, a line with the 
    coordinates of the finish, and n=height lines of characters,
    each line representing one row of the maze. 'X' is a wall,
    and space is--well, a space!

    Instance variables are a 2-D array of char representing the 
    maze, a Position for the start, a Position for the end, 
    a Position for the player's current location, and
    a moves counter that keeps track of how many moves have been made.

    This file also includes the definition for the helper class Position.

    Programmer/Designer: Dr. Boo
    Date: 11 April 2016
*/
import java.io.File;
import java.util.Scanner;

public class Maze
{
    private char [] [] map;
    private int height;         // row count
    private int width;          // column count
    private Position start;
    private Position finish;
    private Position current;   // current location
    private int moveCount;

    /*  default constructor uses the file name 'maze.txt'
        to initialize the maze.
    */
    public Maze ( )     // default constructor
    {
        this ("maze.txt");
    }

    /*  custom constructor allows caller to specify another file
        name to initialize the maze. The file must of course be in
        the correct format.

        Create a scanner using the file name, and process the lines
        described in class documentation to initialize the array, 
        start, and end.
    */
    public Maze (String sourceFile)
    {
		int row, col;	// placeholder coordinates
		this.moveCount = 0;
		String lineIn;
		String progress = "Starting";
		String mapRows [] ;

        try
        {
            Scanner s = new Scanner (new File(sourceFile));

			progress = "Getting height and width";

			this.height = s.nextInt();
			this.width = s.nextInt();

			mapRows = new String [this.height];
			this.map = new char[this.height] [this.width];

			progress = "height and width good. Getting start";

			row = s.nextInt(); col = s.nextInt(); 
			this.start = new Position (row, col);

			progress = "Got start. Getting finish";

			row = s.nextInt(); col = s.nextInt(); 
			this.finish = new Position (row, col);

			progress = "Getting maze map";

			lineIn=s.nextLine();		// skip blank line

			for ( row=0; row < this.height; row++)
			{
				lineIn = s.nextLine();	// get next row of map
System.out.println(lineIn);
				for ( col=0; col<this.width; col++)
				{
System.out.println(row+" "+col);
					this.map[row][col] = lineIn.charAt(col);
				}
			}
			s.close();

			this.current = this.start;

			mark ();	// show that this spot's been visited

        }
        catch (Exception ohDang)
        {
            System.out.println("Problem initializing array:" + ohDang
				+ "\n" + "Progess: " + progress);
        }
    }
	/*	accessors for height, width, current, start, finish, moveCount	*/

	public int getHeight( ) { return this.height; }

	public int getWidth( )	{ return this.width; }

	public Position getCurrent () { return this.current; }

	public Position getFinish() { return this.finish; }

	public Position getStart( ) { return this.start; }

	public int getMoveCount( ) { return this.moveCount; }

	//	mutator for current

	public void setCurrent (Position p)
	{
		this.current = p;
	}
	
	/*	mazeValue (Position)
	
		return the symbol for Position in maze. Options are X, space, 
		O (visited) and ? (invalid)
	*/
    public char mazeValue(Position p)
    {
        char value = '?';   // for out of bounds values
        int row = p.getRow();
        int col = p.getCol();

        if ( col >= 0 && col < this.width && row >= 0 && row < this.height )
        {
            // the position is within the bounds of the maze

            value = this.map[row][col];
        }
        return value;
    }

    /*  look (char) returns a character indicating what lies in the
        direction indicated by the given char ('E' 'S' 'W' 'N' or lc
        equivalents). X is wall, . is marked space, space is space, 
        and '?' means out-of-bounds.
    */
    public char look (char dir)
    {
        char view = '?';

        if (dir == 'E' || dir == 'e')
            view = mazeValue(this.current.right());
        else if (dir == 'W' || dir == 'w')
            view = mazeValue(this.current.left());
        else if (dir == 'N' || dir == 'n')
            view = mazeValue(this.current.up());
        else if (dir == 'S' || dir == 's')
            view = mazeValue(this.current.down());

        return view;
    }

    /*  go (char) moves current to the position in the direction indicated 
		by the given char ('E' 'S' 'W' 'N' or lc equivalents) if
		possible, and marks the new position. Return true if the
		move was possible, false otherwise.
    */
    public boolean go (char dir)
    {
        boolean success = false;

		Position newSpot = this.current;

        if (dir == 'E' || dir == 'e')
            newSpot = (this.current.right());
        else if (dir == 'W' || dir == 'w')
            newSpot = (this.current.left());
        else if (dir == 'N' || dir == 'n')
            newSpot = (this.current.up());
        else if (dir == 'S' || dir == 's')
            newSpot = (this.current.down());

		char destination = mazeValue(newSpot); // what's it like there?

		if (destination==' ' || destination=='.')  // safe move
		{
			this.current = newSpot;		// make the move
			mark();						// remember we've been here
			this.moveCount++;			// count the move
			success = true;
		}

        return success;
    }
	
	/*	showMaze (String) creates a string report that has a title preceded 
		by the label passed in, and a graphical representation of the maze.
		It puts marks the current position with '*' before printing 
		and restores the value of the map for that spot afterward.
	*/
	public String showMaze(String label)
	{
		char holdValue = this.map[current.getRow()][current.getCol()];
		this.map[current.getRow()][current.getCol()] = '*';

		String display = "\n\n"+label+"\n=*=*=*=*=* The Maze *=*=*=*=*=\n\n";

		for (int row = 0; row < this.height; row++)
		{
			display = display + "\t";

			for (int col = 0; col < this.width; col++)
			{
				display = display + this.map[row][col];
			}
			display = display + "\n";
		}
		display = display + "\n\n\n";

		this.map[current.getRow()][current.getCol()]= holdValue;

		return display;
	}

	/*	mark()

		change the character in the maze map to '.' to mark as visited.
	*/
	private void mark ()
	{
		this.map [this.current.getRow()] [this.current.getCol()] = '.';
	}
}   // end of Maze class

 class Position
{
    private int r;
    private int c;

    public Position( )
    {
        this.r = 0;
        this.c = 0;
    }

    public Position (int newR, int newC)
    {
        this.r = newR;
        this.c = newC;
    }

	public int getRow( )
	{
		return this.r;
	}

	public int getCol()
	{
		return this.c;
	}

    public Position left( ) // returns a position one to left of this one
    {
        return new Position (this.r, this.c-1);
    }
    
    public Position right ( )  // returns the position to the right of this one
    {
        return new Position (this.r, this.c+1);
    }

    public Position up ( )  // returns the position above this one
    {
        return new Position (this.r-1, this.c);
    }

    public Position down()  // guess what? returns the position below this one
    {
        return new Position (this.r+1, this.c);
    }

	public boolean equals (Position that)
	{
		return this.r == that.r && this.c == that.c;
	}

	public String toString()
	{
		return "( " + this.r + ", " + this.c + ")";
	}
}   // end of Position class
    
    
    
    
    
    
class Stack 		// a helper class that stores a stack of Maze Positions
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
