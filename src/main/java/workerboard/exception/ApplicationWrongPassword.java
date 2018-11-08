package workerboard.exception;

public class ApplicationWrongPassword extends Exception {

    private String message;

    public ApplicationWrongPassword(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

