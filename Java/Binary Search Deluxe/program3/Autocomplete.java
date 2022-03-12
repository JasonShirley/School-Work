/* By: Jason Shirley
 * CS318 Winter 2018
 * Autocomplete is used to find words in an array of Term. The class starts by instantiating a new 
 * array that is a copy of a Term array to keep the original array secure. Once the new array is built,
 * the class can then find all the matches of a word within a range of a prefix. The class can then be used
 * to narrow down the search by using args commands to declare the top n number of searches in a qurey by
 * descending order of weight
 */

import java.util.Comparator; 
import java.util.Arrays;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
public class Autocomplete {

	private final Term[] autocomplete; // Term array to copy Term array that is being passed in

	// Initializes the data structure from the given array of terms
	public Autocomplete(Term[] terms){
		if (terms == null){ throw new NullPointerException();}
		autocomplete = Arrays.copyOf(terms, terms.length); // copy array passed in
		Quick.sort(autocomplete); // sort the array to make BinarySearchDeluxe work properly
	}
	
	// Returns all terms that start with the given prefix,
	// in descending order of weight. 
	public Term[] allMatches(String prefix){
		if (prefix == null ){ throw new NullPointerException();}
		Term prefixTerm = new Term(prefix, 0);
		Term[] matches = new Term[0];	//Array to hold successful matches
		int prefixLen = prefix.length();
		int firstIndex = BinarySearchDeluxe.firstIndexOf(autocomplete, prefixTerm, Term.byPrefixOrder(prefixLen));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(autocomplete, prefixTerm, Term.byPrefixOrder(prefixLen));
		if ((firstIndex > -1) && (lastIndex > -1)){
			// put all matches in the range of first index and last index 
			matches = Arrays.copyOfRange(autocomplete, firstIndex, lastIndex + 1); 
			// sort the matches array by descending weight order
			Selection.sort(matches, Term.byReverseWeightOrder()); 
		}
		return matches;
	}
	
	// Returns the number of matches with the given prefix
	public int numberOfMatches(String prefix){
		if (prefix == null){ throw new NullPointerException();}
		Term prefixTerm = new Term(prefix, 0);
		int prefixLen = prefix.length();
		int firstIndex = BinarySearchDeluxe.firstIndexOf(autocomplete, prefixTerm, Term.byPrefixOrder(prefixLen));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(autocomplete, prefixTerm, Term.byPrefixOrder(prefixLen));
		return lastIndex - firstIndex + 1;
	}
		
	public static void main (String[] args){
		if (args.length  == 0){
				System.out.println("No text file detected.");
				System.out.println("Usage: Autocomplete input_file number_of_matches_to_display");
				System.exit(0);
		}
	    // read in the terms from a file
	    String filename = args[0];
	    In in = new In(filename);
	    int N = in.readInt();
	    Term[] terms = new Term[N];
	    for (int i = 0; i < N; i++){
	    	long weight = in.readLong();
	    	in.readChar();
	    	String query = in.readLine();
	    	terms[i] = new Term(query, weight);
	    }
	    // read in queries from standard input and print out the top k matching terms
	    int k = Integer.parseInt(args[1]);
	    Autocomplete autocomplete = new Autocomplete(terms);
	    while (StdIn.hasNextLine()){
	    	String prefix = StdIn.readLine();
	    	Term[] results = autocomplete.allMatches(prefix);
	    	for (int i = 0; i < Math.min(k, results.length); i++)
	    		StdOut.println(results[i]);
	    	}
	    }

}
	

