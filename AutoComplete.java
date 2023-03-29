import java.util.Arrays;

public class AutoComplete {
    private final Term[] terms;

    /**
     * Initializes the data structure from the given array of terms.
     */
    public AutoComplete(Term[] terms) {
        if (terms == null) {
            throw new NullPointerException("terms cannot be null");
        }
        for (Term term : terms) {
            if (term == null) {
                throw new NullPointerException("terms cannot contain null");
            }
        }
        // Sort the terms in lexicographic order
        Arrays.sort(terms);
        this.terms = terms;
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @param prefix is the input string from user
     * @return matches, an array of matching terms to the passed prefix
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new NullPointerException("prefix cannot be null");
        }
        int first = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        if (first == -1) {
            return new Term[0]; // No matches found
        }
        int last = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        Term[] matches = Arrays.copyOfRange(terms, first, last + 1);
        // Sort the matching terms in descending order by weight
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }
}
