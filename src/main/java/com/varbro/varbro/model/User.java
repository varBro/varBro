package com.varbro.varbro.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phoneNumber;
    private int salary;
    private String status;
    @Enumerated(EnumType.STRING)
    private Department department;
    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToMany
    @JoinTable(name ="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;

    public enum Department {
        PRODUCTION,
        LOGISTICS,
        DISTRIBUTION,
        HR,
        FINANCE,
        IT
    }

    public enum Position {
        MANAGER,
        ADMIN
    }

    public User() {
    }

    public User(String name, String surname, String password, String email, String phoneNumber, int salary, Department department) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.department = department;
        this.position = null;
        this.status = "1";
    }

    public User(String name, String surname, String password, String email, String phoneNumber, int salary, Department department, Position position) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.department = department;
        this.position = position;
        this.status = "1";
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
