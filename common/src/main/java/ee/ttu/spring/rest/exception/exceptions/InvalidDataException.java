package ee.ttu.spring.rest.exception.exceptions;

/**
 * Created by Hans on 19.03.2017.
 */
public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message) {
        super(message);
    }
}
