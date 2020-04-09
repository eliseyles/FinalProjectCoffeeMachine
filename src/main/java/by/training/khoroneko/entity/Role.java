package by.training.khoroneko.entity;

public enum Role {
    USER(1),
    ADMIN(2),
    GUEST(3);

    private int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role getRoleById(int id) {
        Role[] roles = Role.values();
        for (Role role : roles) {
            if (role.getId() == id) {
                return role;
            }
        }
        return null;
    }
}
