package workerboard.exception;

public class ToMuchArguments extends Exception {

    private String message;

    public ToMuchArguments(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}


