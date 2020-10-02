package db;

import db.entity.User;

/**
 * Role entity.
 *
 * @author K.Zakharova
 */
public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();

        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}