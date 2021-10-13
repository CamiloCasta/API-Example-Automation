package co.com.prueba.api.exceptions;

public class ResponseCodeIncorrect extends AssertionError {

    public ResponseCodeIncorrect(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseCodeIncorrect() {
        super("Response Code Incorrect");
    }
}
