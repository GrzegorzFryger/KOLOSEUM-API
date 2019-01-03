package workerboard.evens;

public enum TypeNotification {

    POINT_FOR_TASK("received experience points for completing the task"),
    NEW_TASK("received the new task to do "),
    NEXT_LEVEL("The next level has been reached"),
    POINT_FOR_POLICY("received experience points for policy");


    private String message;

    TypeNotification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
