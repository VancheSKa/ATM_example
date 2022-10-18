package com.kaivse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Kalinin 16.10.2022
 */
public class User {
    String name;
    String lastName;
    String loginID;
    String password;
    List<Account> accounts;
    final String ALGORITHM = "HmacSHA256";

    public User(String name, String lastName, String password, Bank bank) {
        this.name = name;
        this.lastName = lastName;

        try {
            HmacUtils.calculateHmac(password, ALGORITHM);
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
        }

        this.loginID = bank.getUserUniqueID();
        this.accounts = new ArrayList<>();
        System.out.printf("Тew user %s %s" + '\n'
                        + "with ID %s has been created.\n",
                lastName, name, this.loginID);
    }

    public String getLoginID() {
        return loginID;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void addTransactionToAcc(double amount, String comment) {
        this.accounts.get(0).addTransaction(amount, comment);
    }

    public boolean validatePass(String password) {
        try {
            return Boolean.parseBoolean(String.valueOf(HmacUtils.calculateHmac(password, ALGORITHM)
                    .equals(HmacUtils.calculateHmac(password, ALGORITHM))));
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
        }
        return false;
    }

    @Override
    public String toString() {
        return "User: " + '\n' +
                " name = " + name + '\n' +
                " password = " + password + '\n' +
                " lastname = " + lastName + '\n';
    }
}
