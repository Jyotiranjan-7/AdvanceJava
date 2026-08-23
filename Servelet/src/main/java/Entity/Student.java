package Entity;

public class Student {

    private int id;
    private String name;
    private String registrationNumber;
    private String email;
    private String course;
    private String password;

    public Student() {
    }

    public Student(String name, String registrationNumber,
                   String email, String course,String password) {

        this.name = name;
        this.registrationNumber = registrationNumber;
        this.email = email;
        this.course = course;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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