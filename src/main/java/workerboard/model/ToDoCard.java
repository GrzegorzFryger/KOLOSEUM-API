package workerboard.model;

import workerboard.model.dto.ToDoCardCreateDto;
import workerboard.model.enums.ToDoCardState;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ToDoCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    @OneToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser user;
    @Enumerated(EnumType.STRING)
    private ToDoCardState state;
    private LocalDate createdDate;
    private LocalDateTime lastModification;
    @Transient
    private List<ServiceMessage> messages = new ArrayList<>();

    public ToDoCard() {
    }

    private ToDoCard(String title, String text, ApplicationUser user, ToDoCardState state, LocalDate createdDate, LocalDateTime lastModyfication) {
        this.title = title;
        this.text = text;
        this.user = user;
        this.state = state;
        this.createdDate = createdDate;
        this.lastModification = lastModyfication;
    }

    public static ToDoCard createToDoCard(ToDoCardCreateDto toDoCardCreateDto, ApplicationUser user){
        return new ToDoCard(toDoCardCreateDto.getTitle(),toDoCardCreateDto.getText(), user, ToDoCardState.NEW, LocalDate.now(), LocalDateTime.now());
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

    public List<ServiceMessage> getMessages() {
        return messages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public void setState(ToDoCardState state) {
        this.state = state;
    }

    public LocalDateTime getLastModification() {
        return lastModification;
    }

    public void setLastModification(LocalDateTime lastModification) {
        this.lastModification = lastModification;
    }

    public void addMessage(ServiceMessage message){
        messages.add(message);
    }
}
