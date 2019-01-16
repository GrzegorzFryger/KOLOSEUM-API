package workerboard.exception;

public class UserWrongPassword extends Exception {

    private String message;

    public UserWrongPassword(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

