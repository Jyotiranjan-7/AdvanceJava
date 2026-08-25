package entity;

public class Student {
    private String redgNo;
    private String name;
    private String email;
    private String course;
    private String password;

    public Student() {
    }

    public Student(String redgNo, String name, String email, String course, String password) {
        this.redgNo = redgNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.password = password;
    }

    public String getRedgNo() {
        return redgNo;
    }

    public void setRedgNo(String redgNo) {
        this.redgNo = redgNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

