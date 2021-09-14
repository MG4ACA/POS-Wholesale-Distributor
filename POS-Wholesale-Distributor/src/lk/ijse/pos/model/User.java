package lk.ijse.pos.model;

public class User {
    private String user_id;
    private String user_name;
    private String password;
    private boolean active_state;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", active_state=" + active_state +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive_state() {
        return active_state;
    }

    public void setActive_state(boolean active_state) {
        this.active_state = active_state;
    }

    public User(String user_id, String user_name, String password, boolean active_state) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.active_state = active_state;
    }
}
