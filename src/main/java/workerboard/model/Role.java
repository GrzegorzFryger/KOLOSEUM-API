package workerboard.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.NaturalId;
import workerboard.model.dto.ViewsForApplicationUser;
import workerboard.model.enums.UserRole;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Role implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NaturalId
    @Enumerated(EnumType.STRING)
    @JsonView(ViewsForApplicationUser.Basic.class)
    private UserRole name;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public Role(UserRole name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getName() {
        return name;
    }

    public void setName(UserRole name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
