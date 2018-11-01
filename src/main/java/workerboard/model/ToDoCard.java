package workerboard.model;

import workerboard.model.enums.ToDoCardState;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ToDoCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private ApplicationUser user;
    @Enumerated(EnumType.STRING)
    private ToDoCardState state;
    private LocalDate createdDate;

    public ToDoCard() {
    }

    public ToDoCard(String title, String text, ApplicationUser user, ToDoCardState state, LocalDate createdDate) {
        this.title = title;
        this.text = text;
        this.user = user;
        this.state = state;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public ToDoCardState getState() {
        return state;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }
}
