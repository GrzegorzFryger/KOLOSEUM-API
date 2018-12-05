package workerboard.model;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import workerboard.model.enums.ToDoCardState;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class ToDoCardHistory  {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String text;

    private String modifiedBy;

    private Date modifiedDate;

    @Enumerated(STRING)
    private ToDoCardState action;

    public ToDoCardHistory() {
    }


    public ToDoCardHistory(String title, String text, String modifiedBy, Date modifiedDate, ToDoCardState action) {
        this.title = title;
        this.text = text;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ToDoCardState getAction() {
        return action;
    }

    public void setAction(ToDoCardState action) {
        this.action = action;
    }

}
