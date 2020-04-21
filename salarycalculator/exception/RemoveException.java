package il.ac.hit.salarycalculator.exception;

/**
 * Exception That handle for RemoveException
 */
public class RemoveException extends Exception {

    /**
     * Ctor with parameters
     */
    public RemoveException() {
        super();
    }

    /**
     * Ctor
     * @param message message that represent the exception
     * @param cause warp the throwable
     */
    public RemoveException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Ctor
     * @param message message that represent the exception
     */
    public RemoveException(String message) {
        super(message);
    }
}
