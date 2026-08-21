package model;
public class Admin {

    private int adminId;
    private String adminName;
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(int adminId, String adminName, String username, String password) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.username = username;
        this.password = password;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}