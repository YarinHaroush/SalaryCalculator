package il.ac.hit.salarycalculator.exception;

/**
 * Exception That handle for GetAllException
 */
public class GetAllException extends Exception {

    /**
     * Ctor with parameters
     */
    public GetAllException() {
        super();
    }

    /**
     * Ctor
     * @param message message that represent the exception
     * @param cause warp the throwable
     */
    public GetAllException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Ctor
     * @param message message that represent the exception
     */
    public GetAllException(String message) {
        super(message);
    }
}
