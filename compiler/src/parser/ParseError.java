package parser;

/**
 * This class implements a ParseError. It is thrown whenever an error is found
 * in the parsing stage of the compiler.
 * @author Andrew
 */
public class ParseError extends RuntimeException {
    public ParseError(String errorMsg) {
        super(errorMsg);
    }
}
