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
public class ParticipantEnrolledException extends Exception {

    /**
     * Creates a new instance of <code>ParticipantEnrolledException</code>
     * without detail message.
     */
    public ParticipantEnrolledException() {
    }

    /**
     * Constructs an instance of <code>ParticipantEnrolledException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ParticipantEnrolledException(String msg) {
        super(msg);
    }
}
