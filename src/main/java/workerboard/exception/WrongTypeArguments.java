package workerboard.exception;

public class WrongTypeArguments extends Exception {

    private String message;

    public WrongTypeArguments(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}


