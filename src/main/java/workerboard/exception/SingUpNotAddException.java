package workerboard.exception;

public class SingUpNotAddException extends Exception {

    private String message;

    public SingUpNotAddException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
