package lab4.view.exception;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class InvalidCommandException extends RuntimeException {

    //Usually, define custom properties and constructors
    public InvalidCommandException(String message) {
        super(message);
    }

}
