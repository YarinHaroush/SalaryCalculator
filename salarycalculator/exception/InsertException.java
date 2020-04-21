package il.ac.hit.salarycalculator.exception;

/**
 * Exception That handle for InsertException
 */
public class InsertException extends Exception {

    /**
     * Ctor with parameters
     */
    public InsertException() {
        super();
    }

    /**
     * Ctor
     * @param message message that represent the exception
     * @param cause warp the throwable
     */
    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Ctor
     * @param message message that represent the exception
     */
    public InsertException(String message) {
        super(message);
    }
}
