package com.kaivse;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Ivan Kalinin 16.10.2022
 */
public class Bank {
    String bankName;
    ArrayList<User> users;
    ArrayList<Account> accounts;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public User addNewUser(String firstName, String lastName, String password) {
        User newUser = new User(firstName, lastName, password, this);
        this.users.add(newUser);

        Account newAccount = new Account("string", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);
    }

    public static String getAccountUniqueID() {
        final int maxLength = 4;
        String uuid = UUID.randomUUID().toString();
        String requestId = uuid.replaceAll("[^0-9]", "");
        String subString = requestId.substring(0, maxLength);

        return subString;
    }

    public static String getUserUniqueID() {
        final int maxLength = 4;
        String uuid = UUID.randomUUID().toString();
        String requestId = uuid.replaceAll("[^0-9]", "");
        String subString = requestId.substring(0, maxLength);

        return subString;
    }

    public User checkUserLogin(String loginID, String password) {
        for (User u : this.users) {
            if (u.getLoginID().compareTo(loginID) == 0 && u.validatePass(password)) {
                return u;
            }
        }
        return null;
    }
}
