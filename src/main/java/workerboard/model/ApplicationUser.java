package workerboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import workerboard.model.dto.ViewsForApplicationUser;
import workerboard.serivce.mapper.MapGenerate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application_user")
public class ApplicationUser implements MapGenerate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewsForApplicationUser.ExtendedByID.class)
    private Long id;
    @JsonView(ViewsForApplicationUser.Basic.class)
    private String firstName;
    @JsonView(ViewsForApplicationUser.Basic.class)
    private String lastName;

    @JsonView(ViewsForApplicationUser.Basic.class)
    private String email;


    private String password;

    @JsonView(ViewsForApplicationUser.Basic.class)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @JsonView(ViewsForApplicationUser.Basic.class)
    @OneToOne(
            mappedBy = "applicationUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )

    private Experience experience;

    public ApplicationUser() {
    }

    public ApplicationUser(String firstName, String lastName, String email, String password, List<Role> roles, Experience experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.experience = experience;
    }


    //set instance Application user for Experience
    public void setExperience(Experience experience) {
        if (experience == null) {
            if (this.experience != null) {
                this.experience.setApplicationUser(null);
            }
        }
        else {
            experience.setApplicationUser(this);
        }
        this.experience = experience;
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Experience getExperience() {
        return experience;
    }
}
