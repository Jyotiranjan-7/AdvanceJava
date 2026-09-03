package entity;

public class Result {
    private String redgNo;
    private int java,dbms,os,ds;
    private double percentage;
    private String grade;

    public Result() {
    }

    public Result(String redgNo, int java, int dbms, int os, int ds, double percentage, String grade) {
        this.redgNo = redgNo;
        this.java = java;
        this.dbms = dbms;
        this.os = os;
        this.ds = ds;
        this.percentage = percentage;
        this.grade = grade;
    }

    public String getRedgNo() {
        return redgNo;
    }

    public void setRedgNo(String redgNo) {
        this.redgNo = redgNo;
    }

    public int getJava() {
        return java;
    }

    public void setJava(int java) {
        this.java = java;
    }

    public int getDbms() {
        return dbms;
    }

    public void setDbms(int dbms) {
        this.dbms = dbms;
    }

    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }

    public int getDs() {
        return ds;
    }

    public void setDs(int ds) {
        this.ds = ds;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
