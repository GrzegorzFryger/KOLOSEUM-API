package workerboard.model.enums;

import java.io.Serializable;

public enum UserRole implements Serializable {
    ROLE_SELLER(1, "ROLE_SELLER"),
    ROLE_ADMIN(2, "ROLE_ADMIN"),
    ROLE_MANAGER(3, "ROLE_MANAGER"),
    ROLE_ANALYST(4, "ROLE_ANALYST"),
    ROLE_UNSPECIFIED(5, "ROLE_UNSPECIFIED");

    int id;
    String name;


    UserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
