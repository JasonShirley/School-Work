import edu.princeton.cs.algs4.Quick;
import java.util.Comparator; 
import java.util.Arrays;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
public class Autocomplete {
	private final Term[] autocomplete;

	// Initalizes the data structrue from the given array of terms
	public Autocomplete(Term[] terms){
		autocomplete = Arrays.copyOf(terms, terms.length);
		Quick.sort(autocomplete);
	}
	
	// Returns all terms that start with the given prefix,
	// in descending order of weight. 
	public Term[] allMatches(String prefix){
		Term test = new Term(prefix, 0);
		Term[] matches = new Term[0];	//Array to hold successfull matches
		int prefixLen = prefix.length();
		int firstIndex = BinarySearchDeluxe.firstIndexOf(autocomplete, test, Term.byPrefixOrder(prefixLen));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(autocomplete, test, Term.byPrefixOrder(prefixLen));
		if ((firstIndex > -1) && (lastIndex > -1)){
			// put all matches in the range of first index and last index 
			matches = Arrays.copyOfRange(autocomplete, firstIndex, lastIndex + 1); 
			// sort the matches array by descending weight order
			Selection.sort(matches, Term.byReverseWeightOrder()); 
		}
		return matches;
	}
	
	
	public int numberOfMatches(String prefix){


		}
	
	
	public static void main (String[] args){
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
	    // read in queries from standard imput and prin out the top k matching terms
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
	

