package com.example.STA;

public class Employee {
    private int id;
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee(int id, Address address) {
        this.id = id;
        this.address = address;
    }

    public void display() {
        System.out.println("employee id" + id);
        System.out.println("employee Address" + address);
    }
}