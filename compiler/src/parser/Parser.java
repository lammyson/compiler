/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parser;

import java.io.FileWriter;

/**
 *
 * @author Andrew
 */
public interface Parser {
    public Program parse();
    public void printTree(FileWriter out);
}
