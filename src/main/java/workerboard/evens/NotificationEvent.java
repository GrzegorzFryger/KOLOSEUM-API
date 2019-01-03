package workerboard.evens;

public class NotificationEvent extends AbstractEvent<String> {

    private Long userId;

    public NotificationEvent(String aClass, Long userId) {
        super(aClass);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
