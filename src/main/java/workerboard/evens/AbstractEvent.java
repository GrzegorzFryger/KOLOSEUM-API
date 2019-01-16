package workerboard.evens;

public abstract class AbstractEvent<T> {

    private T aClass;

    public AbstractEvent(T aClass) {
        this.aClass = aClass;
    }

    public T getEventObject() {
        return this.aClass;
    }

}
