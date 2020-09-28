package db.entity;

/**
 * Role entity.
 *
 * @author K.Zakharova
 */
public class Role extends Entity {

    private static final long serialVersionUID = 5692708766041889396L;

    private String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Role [userRole=" + userRole + ", getId()=" + getId() + "]";
    }
}

