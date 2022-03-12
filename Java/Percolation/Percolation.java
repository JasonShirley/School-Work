/**************************************************************************************
 * Programmer: Jason Shirley							    						  *	
 * Date: 01/23/18								    								  *
 * CS318									    									  *
 * Percolation.java is a program that builds a WeightedQuickUnionUF object of client  *
 * defined size. The object can be used to build a percolation model. The program     *
 * calculates whether or not a site can be connected. If the site is connected from   *
 * the top row, then water can fill down from the top to the next closed site going   *
 * down. If there is a connection from the top to the bottom, percolation has occured.*
 *************************************************************************************/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;				      
public class Percolation {
    private int size;
	private WeightedQuickUnionUF qUnion;
	private int numOpenSites;
	private boolean[] openSite;
	private int topTree;
	private int bottomTree;
	public Percolation(int n){ 	    // create n­by­n grid, with all sites blocked
		if (n <= 0){
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		numOpenSites = 0;
		size = n;
		qUnion = new WeightedQuickUnionUF(n*n+2);
		openSite = new boolean[n * n+2];
		topTree = 0;	// Virtural top site. Used to which sites get filled
				// in the percolation model.
		openSite[topTree] = true;
		bottomTree = n*n + 1;	// Virtural bottom site. Used to determine
					// if a connection has been made from the 
					// top of the grid to the bottom of the grid.

	}
	// The open method opens a site. When the site is opened, the program determines 
	// whether or not a connection can occure from the top, bottom, left, or right 
	// of the site. The connection is made if and only if the neighboring site is 
	// open.
	public void open(int row, int col)  //open site (row, col) if not open already
	{
		isValid(row, col);		//Check if row, col is valid input.
		int location = xyTo1D(row, col);
		openSite[location] = true;
		numOpenSites++;
		// Is the new site on the top row? If so then make a connection to the 
		// virtural top site.
		if (location <= size){
			qUnion.union(location, topTree);
		}
		// Does the new site have a neighbor to the right that is open? If so then
		// connect the two sites.
		if ((location + 1) <= size*size && (openSite[location+1]) && col < size){
			qUnion.union(location, location+1);
		}
		// Does the new site have a neighbor below it that is open? If so then 
		// connect the two sites.
		if ((location + size) <= size*size && (openSite[location + size])){
				qUnion.union(location, location+size);
			}
		// Does the new site have a neighbor to the left that is open? If so 
		// then connect the two sites. 
		if ((location - 1) > 0 && (openSite[location-1]) && col > 1 ){
				qUnion.union(location, location -1);
		}
		// Does the new site have a neighbor to above it that is open? If so
		// then connect the two sites.
		if((location - size) > 0 && openSite[location - size]){
				qUnion.union(location, location-size);
		}
	}
	public boolean isOpen(int row, int col)  // is site (row, col) open?
	{
		isValid(row, col);
		int location = xyTo1D(row, col); 
		return openSite[location];
	}
	public boolean isFull(int row, int col)  // is site (row, col) full?
	{
		isValid(row, col);
		int location = xyTo1D(row, col); 
		// Is location connected to the top row? If so then connect the virtural top
		// to the virtural bottom.
		if ((location > (size*size)-size) && qUnion.connected(topTree,location)){
			qUnion.union(location, bottomTree);
		}
		return qUnion.connected(topTree, location);
	}
	public int numberOfOpenSites()           // number of open sites
	{
		return numOpenSites;
	}
	public boolean percolates(){              // does the system percolate?
		return qUnion.connected(topTree, bottomTree);
	}
	private int xyTo1D (int row, int col){	  // Convert from twoD to oneD array.
		isValid(row, col);
		int conversion = (size*(row-1))+col;
		return conversion;
	}
	private void isValid (int row, int col){  // Check if the site is in a valid range.
		if (row <= 0 || row > size*size || col <= 0 || col > size*size ){
		throw new IndexOutOfBoundsException("index out of bounds");
		}
	}
}
	


