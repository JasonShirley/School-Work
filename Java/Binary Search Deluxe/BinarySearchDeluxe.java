import java.util.Comparator;
import edu.princeton.cs.algs4.Selection; 


/*
 * By Jason Shirley
 * CS318 Winter 2018 
 * BinarySearchDeluxe is used to find a the first index or last index of the prefix of a word in a sorted
 * Term array. This class can be used in accompany of classes that need to uses auto complete functionality. 
 */
public class BinarySearchDeluxe{
	/* Returns the index of the first key in a[] that equals the search key,
		or -1 if no such key
 	 */
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator){
		if (a == null || key == null || comparator == null){
			throw new NullPointerException();
		}
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi){
			int mid = lo + (hi-lo) / 2;
			if (comparator.compare(key, a[mid]) < 0){
				hi = mid - 1;
			}
			else if (comparator.compare(key, a[mid]) > 0){
				lo = mid + 1;
			}
			else {
				while(mid > 0 && comparator.compare(key, a[mid - 1]) == 0){
					mid --;
				}
				return mid; // found the first index of the prefix
			}
		}
		return -1;	// the index was not found
	}
	/* Returns the index of the last key in a[] that equals the search key,
		or -1 if no such key
	*/
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator){
		if (a == null || key == null || comparator == null){
			throw new NullPointerException();
		}
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi){
			int mid = lo + (hi-lo) / 2;
			if (comparator.compare(key, a[mid]) == 0){
			}
			if (comparator.compare(key, a[mid]) < 0){
				hi = mid - 1;
			}
			else if (comparator.compare(key, a[mid]) > 0){
				lo = mid + 1;
			}
			else{
				
				while(mid < a.length-1 && comparator.compare(key, a[mid + 1]) == 0){
					mid++;
				}
				return mid; // found the last index of the prefix
			}
		}
		return -1;	// index was not found
	}
	// for unit testing
	public static void main (String[] args){
		Term a = new Term("ac", 23145);
		Term b = new Term("acc", 343211);
		Term c = new Term("bcc", 23144);
		Term d = new Term("bccc", 1111111);
		Term e = new Term("bcccc", 55555);
		Term f = new Term("cbb", 34213);
		Term g = new Term("cbbb", 34523);
		Term h = new Term("bccccc", 12345);
		Term i = new Term("bcccccc", 342312);
		Term arr[] = new Term[12];
		arr[0] = a;
		arr[1] = b;
		arr[2] = c;
		arr[3] = d;
		arr[4] = e;
		arr[5] = f;
		arr[6] = g;
		arr[7] = h;
		arr[8] = i;
		//Selection.sort(arr, Term.byPrefixOrder(1));
		for (int j = 0 ; j < arr.length; j++){
			System.out.println(arr[j]);
			}
		System.out.println(firstIndexOf(arr, arr[0], Term.byPrefixOrder(1)));
	}
}
