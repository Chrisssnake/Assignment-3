/*  Term
 *  
 * 	@author Reilly Downing
 * 	@author Christian Patterson
 * 	@author Isaac Rush
 * 	@author Patrick Hafner
 * 	@author Grady Bartlett 
 * 
 * 	Immutable data type that represents an autocomplete term.
 * 
 * 			* Supports comparing terms by three different orders
 *  
 * 				1.) lexicographic order by query string (natural order).
 * 
 * 				2.) descending order by weight (alternate order).
 * 
 * 				3.) lexicographic order by weight using only the first 
 * 					character (family of alternate orderings).
 */

import java.util.Comparator;

public class Term implements Comparable<Term> {
    private final String query;
    private final long weight;

    /**
     * Initializes a term with the given query string and weight.
     * @param query the query string
     * @param weight the weight of the term
     */
    public Term(String query, long weight) {
        if (query == null) throw new NullPointerException("query cannot be null");
        if (weight < 0) throw new IllegalArgumentException("weight cannot be negative");
        this.query = query;
        this.weight = weight;
    }

    /**
     * Compares the two terms in descending order by weight.
     * @return a comparator that compares terms by weight in descending order
     */
    public static Comparator<Term> byReverseWeightOrder() {
        return Comparator.comparingLong(Term::getWeight).reversed();
    }

    /**
     * Compares the two terms in lexicographic order by query.
     * @param r the number of characters to use for the comparison
     * @return a comparator that compares terms lexicographically by query
     * but using only the first r characters
     */
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) throw new IllegalArgumentException("r cannot be negative");
        return Comparator.comparing((Term term) -> term.getQuery().substring(0, Math.min(r, term.getQuery().length())));
    }

    /**
     * Compares the two terms in lexicographic order by query.
     * @param that the other term to compare to
     * @return a negative integer, zero, or a positive integer as this term
     * is less than, equal to, or greater than the other term
     */
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    /**
     * Returns a string representation of this term in the following format:
     * weight (i.e., ??.toString()), followed by a tab, followed by query.
     * @return a string representation of this term
     */
    public String toString() {
        return this.weight + "\t" + this.query;
    }

    /**
     * Gets the query string of this term.
     * @return the query string
     */
    public String getQuery() {
        return this.query;
    }

    /**
     * Gets the weight of this term.
     * @return the weight
     */
    public long getWeight() {
        return this.weight;
    }
}
