package com.kaivse;

import java.util.ArrayList;

/**
 * @author Ivan Kalinin 16.10.2022
 */
public class Bank {
    String bankName;
    ArrayList<User> users;
    ArrayList<Account> accounts = new ArrayList<>();

    public Bank(String bankName) {
        this.bankName = bankName;
        this.users = new ArrayList<>();
    }

    public User addNewUser(String firstName, String lastName, String loginID, String password) {
        User newUser = new User(firstName, lastName, loginID, password);
        this.users.add(newUser);

        Account newAccount = new Account("string", newUser);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);
    }

    public User checkLoginUser(String loginID, String password) {
        for (User u : this.users) {
            if (u.getNewUniqueID().compareTo(loginID) == 0) {
                return u;
            }
        }
        return null;
    }
}
