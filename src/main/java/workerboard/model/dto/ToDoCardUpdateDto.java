package workerboard.model.dto;

import workerboard.model.enums.ToDoCardState;

import javax.validation.constraints.NotNull;

public class ToDoCardUpdateDto {
    @NotNull
    private String title;
    @NotNull
    private String text;
    @NotNull
    private Long userId;
    @NotNull
    private Long cardToUpdateId;
    @NotNull
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCardToUpdateId() {
        return cardToUpdateId;
    }

    public void setCardToUpdateId(Long cardToUpdateId) {
        this.cardToUpdateId = cardToUpdateId;
    }

    public ToDoCardState getState() {
        return state;
    }

    public void setState(ToDoCardState state) {
        this.state = state;
    }

}
