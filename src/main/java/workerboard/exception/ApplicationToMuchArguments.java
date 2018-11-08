package workerboard.exception;

public class ApplicationToMuchArguments extends Exception {

    private String message;

    public ApplicationToMuchArguments(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}


