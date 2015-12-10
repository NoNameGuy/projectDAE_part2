/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author franc
 */
public class MyConstraintViolationException extends Exception {

    /**
     * Creates a new instance of <code>MyConstraintViolationException</code>
     * without detail message.
     */
    public MyConstraintViolationException() {
    }

    /**
     * Constructs an instance of <code>MyConstraintViolationException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MyConstraintViolationException(String msg) {
        super(msg);
    }
}
