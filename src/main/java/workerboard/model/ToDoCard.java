package workerboard.model;

import workerboard.model.dto.ToDoCardCreateDto;
import workerboard.model.enums.ToDoCardState;
import workerboard.serivce.ToDoCardListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(ToDoCardListener.class)
public class ToDoCard  {

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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "to_do_card_id")
    private List<ToDoCardHistory> toDoCardHistories;
    private Date createdDate;


    public ToDoCard() {
    }

    private ToDoCard(String title, String text, ApplicationUser user, ToDoCardState state, Date createdDate,
                     List<ToDoCardHistory> toDoCardHistories) {
        this.title = title;
        this.text = text;
        this.user = user;
        this.state = state;
        this.createdDate = createdDate;
        this.toDoCardHistories = toDoCardHistories;

    }

    public static ToDoCard createToDoCard(ToDoCardCreateDto toDoCardCreateDto, ApplicationUser user){
        return new ToDoCard(toDoCardCreateDto.getTitle(),
                toDoCardCreateDto.getText(),
                user, ToDoCardState.NEW,
                new Date(), new ArrayList<>());
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

    public void setId(Long id) {
        this.id = id;
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

    public List<ToDoCardHistory> getToDoCardHistories() {
        return toDoCardHistories;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
