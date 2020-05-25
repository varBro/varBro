package com.varbro.varbro.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phoneNumber;
    private int salary;
    private Department department;
    @ManyToMany
    private Set<Role> roles;

    public static enum Department {
        PRODUCTION,
        LOGISTICS,
        DISTRIBUTION,
        HR,
        FINANCE,
        IT
    }

    public User() {
    }

    public User(String name, String surname, String password, String email, String phoneNumber, int salary) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public long getUserId() {
        return id;
    }

    public void setUserId( long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> Roles) {
        this.roles = roles;
    }

}
