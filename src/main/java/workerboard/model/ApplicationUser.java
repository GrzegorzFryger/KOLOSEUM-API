package workerboard.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.NaturalId;
import workerboard.model.dto.ViewsForApplicationUser;
import workerboard.model.enums.UserRole;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "application_user")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewsForApplicationUser.ExtendedByID.class)
    private Long id;
    @JsonView(ViewsForApplicationUser.Basic.class)
    private String firstName;
    @JsonView(ViewsForApplicationUser.Basic.class)
    private String lastName;

    @NaturalId
    @JsonView(ViewsForApplicationUser.Basic.class)
    private String email;

    private String password;

    @JsonView(ViewsForApplicationUser.Basic.class)
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public ApplicationUser() {
    }

    public ApplicationUser(String firstName, String lastName, String email, String password, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
