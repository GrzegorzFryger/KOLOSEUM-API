package workerboard.model.dto;

import workerboard.model.enums.ToDoCardState;

public class ToDoCardUpdateDto {
    private String title;
    private String text;
    private Long userId;
    private Long cardToUpdateId;
    private ToDoCardState state;


    public ToDoCardUpdateDto() {
    }

    public ToDoCardUpdateDto(String title, String text, Long userId, Long cardToUpdateId, ToDoCardState state) {
        this.title = title;
        this.text = text;
        this.userId = userId;
        this.cardToUpdateId = cardToUpdateId;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCardToUpdateId() {
        return cardToUpdateId;
    }

    public ToDoCardState getState() {
        return state;
    }
}
