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
public class ParticipantNotEnrolledException extends Exception {

    /**
     * Creates a new instance of <code>ParticipantNotEnrolledException</code>
     * without detail message.
     */
    public ParticipantNotEnrolledException() {
    }

    /**
     * Constructs an instance of <code>ParticipantNotEnrolledException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ParticipantNotEnrolledException(String msg) {
        super(msg);
    }
}
