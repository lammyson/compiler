package scanner;

/**
 * Scanner serves as an interface for CMinusScanner
 * @author Andrew
 */
public interface Scanner {
    public Token getNextToken();
    public Token viewNextToken();
}