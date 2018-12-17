package workerboard.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ToDoCardCreateDto {
    @NotNull
    @NotBlank(message = "title not be null")
    private String title;

    private String text;
    @NotNull
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
