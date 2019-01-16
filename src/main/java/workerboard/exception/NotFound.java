package workerboard.exception;

public class NotFound extends Exception {

    private String message;

    public NotFound(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
