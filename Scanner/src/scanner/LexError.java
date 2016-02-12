package scanner;

/**
 * This class throws an error in CMinusScanner when the scanner arrives in
 * an error state
 * @author Andrew
 */
public class LexError extends RuntimeException {
    public LexError(String errorMsg) {
        super(errorMsg);
    }
}