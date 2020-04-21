package il.ac.hit.salarycalculator.exception;

/**
 * Exception That handle for DataBaseQuery
 */
public class DataBaseQueryException extends Exception {

    /**
     * Ctor with parameters
     */
    public DataBaseQueryException() {
        super();
    }

    /**
     * Ctor
     * @param message message that represent the exception
     * @param cause warp the throwable
     */
    public DataBaseQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Ctor
     * @param message message that represent the exception
     */
    public DataBaseQueryException(String message) {
        super(message);
    }
}
