package lk.ijse.pos.dto;

public class UserDTO {
    private String user_id;
    private String user_name;
    private String password;
    private boolean active_state;

    public UserDTO() {
    }
    public UserDTO(String id, String pw) {
        this.user_id=id;
        this.password=pw;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
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

    public UserDTO(String user_id, String user_name, String password, boolean active_state) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.active_state = active_state;
    }
}
