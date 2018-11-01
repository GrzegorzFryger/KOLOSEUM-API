package workerboard.model.dto;

public class ToDoCardCreateDto {
    private String title;
    private String text;
    private Long userId;

    public ToDoCardCreateDto() {
    }

    public ToDoCardCreateDto(String title, String text, Long userId) {
        this.title = title;
        this.text = text;
        this.userId = userId;
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
}
