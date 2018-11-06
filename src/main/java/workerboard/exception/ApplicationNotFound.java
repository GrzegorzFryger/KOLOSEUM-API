package workerboard.exception;

public class ApplicationNotFound extends Exception {

    private String message;

    public ApplicationNotFound(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
