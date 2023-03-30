/*  BinarySearchDeluxue
 *  
 * 	@author Reilly Downing
 * 	@author Christian Patterson
 * 	@author Isaac Rush
 * 	@author Patrick Hafner
 * 	@author Grady Bartlett 
 * 
 * 	Deals with binary searching a sorted array that contains 
 * 		more than one key equal to the search key
 * 
 * 			*returns indexes of first and last key 
 */

import java.util.Comparator;

public class BinarySearchDeluxe {
    
    /**
     * Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
     * @param a the array of keys to search
     * @param key the key to search for
     * @param comparator the comparator to use for comparing keys
     * @return the index of the first key in a[] that equals the search key, or -1 if no such key.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("Arguments cannot be null");
        }
        
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = comparator.compare(key, a[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else if (mid == 0 || comparator.compare(a[mid - 1], a[mid]) != 0) {
                return mid;
            } else {
                hi = mid - 1;
            }
        }
        
        return -1;
    }
    
    /**
     * Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
     * @param a the array of keys to search
     * @param key the key to search for
     * @param comparator the comparator to use for comparing keys
     * @return the index of the last key in a[] that equals the search key, or -1 if no such key.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("Arguments cannot be null");
        }
        
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = comparator.compare(key, a[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else if (mid == a.length - 1 || comparator.compare(a[mid], a[mid + 1]) != 0) {
                return mid;
            } else {
                lo = mid + 1;
            }
        }
        
        return -1;
    }
}
