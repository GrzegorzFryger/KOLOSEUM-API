package workerboard.exception;

public class UserNotFound extends Exception {

    private String message;

    public UserNotFound(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
