/* By: Jason Shirley
  * CS318 Winter 2018
  *Term is used to create a Term object. Term has two parameters, query and weight.
  * The parameters represent the query of a search, and the weight of how often that
  * query is searched represented as a numerical value. Term can be used as a method
  * for sorting. A client has 3 options to choose from. The options are by the descending
  * weight order, by the number of characters to search in the prefix of a word, and 
  * by natural lexicographic order of the words. 
 */

import java.util.Comparator;
import edu.princeton.cs.algs4.Selection; 

public class Term implements Comparable<Term> {
	
	private final String tQuery;
	private final long tWeight;
	// Initializes a term with the given query string and weight.  
	public Term(String query, long weight){
		if (query == null){throw new NullPointerException();}
		else if (weight < 0) {throw new IllegalArgumentException();}
		tQuery = query;
		tWeight = weight;
	}
	
	// Compares the two terms in descending order by weight.
	public static Comparator<Term> byReverseWeightOrder(){
		return new Comparator<Term>(){
			@Override
			public int compare(Term a, Term b){
				return -(int)(a.tWeight - b.tWeight);	//return negative cast to ensure descending order
			}
		};
	}
	
	// Compares the two terms in lexicographic order but using only the first r characters of each query
	public static Comparator<Term> byPrefixOrder (final int r){
		if (r == 0) { throw new IndexOutOfBoundsException();}
		return new Comparator<Term>(){
			int prefixChars = r;
			@Override
			public int compare(Term a, Term b){
				if(a.tQuery.length() < prefixChars || b.tQuery.length() < prefixChars){ // make sure that the length of the prefix is not longer than the query
					return a.tQuery.compareToIgnoreCase(b.tQuery);
					}
				else{ // compare if query length is greater than prefix chars
					return a.tQuery.substring(0,prefixChars).compareToIgnoreCase(b.tQuery.substring(0,prefixChars));
				}
			}
		};
	}

	// Compares two terms in lexicographic order by query	
	@Override
	public int compareTo(Term a){
		return (this.tQuery.compareToIgnoreCase(a.tQuery));
	}
	// Returns a string representation of this term in the following format:
	// the weight, followed by a tab, followed by the query.
	public String toString(){
		return (tWeight + "\t" + tQuery);
	}
	// for unit testing
	public static void main (String args[]){
	Term a = new Term("JIM", 123456);
	Term b = new Term("accbtb", 343211);
	Term c = new Term("accbcv", 21233);
	Term d = new Term("acb", 1111111);
	Term e = new Term("faa", 55555);
	Term f = new Term("cbb", 34213);
	Term arr[] = new Term[6];
	arr[0] = a;
	arr[1] = b;
	arr[2] = c;
	arr[3] = d;
	arr[4] = e;
	arr[5] = f;
	for (int i = 0; i < arr.length; i++){
		System.out.println(arr[i].toString());
		}
	System.out.println("Presorted array^");
	Selection.sort(arr, Term.byPrefixOrder(2));
	for (int i = 0; i < arr.length; i++){
		System.out.println(arr[i].toString());
		}
	System.out.println("^ Test of byPrefixOrder");
	Selection.sort(arr, Term.byReverseWeightOrder());
	for(int i = 0; i < arr.length; i++){
		System.out.println(arr[i].toString());
		}
	System.out.println("^ Test of ReverseWeightOrder");
	Selection.sort(arr);
	for (int i = 0; i < arr.length; i++){
		System.out.println(arr[i].toString());
		}
	System.out.println("^ Test of compareTo");
	}
}

