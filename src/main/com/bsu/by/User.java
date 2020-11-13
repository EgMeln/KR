package com.bsu.by;


import java.io.IOException;
import java.util.Objects;

public class User {
    private String name;
    private String login;
    private String email;
    private String password;
    private Role role;

    public User(String name, String login, String email, String password, Role role) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    User(String[] str) throws IOException {
        if (str.length != 5) {
            throw new IllegalArgumentException("Error: wrong number of fields");
        }
        this.name = str[0];
        this.login = str[1];
        this.email = str[2];
        this.password = str[3];
        this.role = Role.valueOf(str[4]);
    }

    public enum Role {
        ADMIN("ADMIN"),
        USER("USER");
        private String role;

        Role(String role) {
            this.role = role;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, email, password, role);
    }

    @Override
    public String toString() {
        return name + ';' +
                login + ';' +
                email + ';' +
                password + ';' +
                role + ';';
    }
}
