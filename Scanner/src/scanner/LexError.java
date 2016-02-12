package scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
public class LexError extends RuntimeException {
    public LexError(String errorMsg) {
        super(errorMsg);
    }
}
