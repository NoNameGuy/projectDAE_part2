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
public class EntityAlreadyExistsException extends Exception {

    /**
     * Creates a new instance of <code>EntityAllReadyExists</code> without
     * detail message.
     */
    public EntityAlreadyExistsException() {
    }

    /**
     * Constructs an instance of <code>EntityAllReadyExists</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EntityAlreadyExistsException(String msg) {
        super(msg);
    }
}
