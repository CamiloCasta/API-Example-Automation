package co.com.prueba.api.exceptions;

public class ResponseStatusIncorrectException extends AssertionError {

    public ResponseStatusIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseStatusIncorrectException() {
        super("Response Status Incorrect");
    }
}
