package com.epam.pharmacy.model.entity;

import java.util.Objects;

public class Account {

    private final long id;
    private final String login;
    private final String password;
    private String role;
    private final String firstName;
    private final String lastName;
    private long balance;

    public Account(long id, String login, String password, String role, String firstName, String lastName, long balance) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return id == account.id &&
                balance == account.balance &&
                login.equals(account.login) &&
                password.equals(account.password) &&
                role.equals(account.role) &&
                firstName.equals(account.firstName) &&
                lastName.equals(account.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, firstName, lastName, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
