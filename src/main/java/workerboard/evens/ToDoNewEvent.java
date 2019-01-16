package workerboard.evens;

import workerboard.model.ToDoCard;

public class ToDoNewEvent extends AbstractEvent<ToDoCard> {

    public ToDoNewEvent(ToDoCard aClass) {
        super(aClass);
    }
}
