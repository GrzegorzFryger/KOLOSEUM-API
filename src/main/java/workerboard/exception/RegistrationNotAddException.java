package workerboard.exception;

public class RegistrationNotAddException extends Exception {

    private String message;

    public RegistrationNotAddException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
