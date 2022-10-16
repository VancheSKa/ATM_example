package com.kaivse;

import java.util.ArrayList;

/**
 * @author Ivan Kalinin 16.10.2022
 */
public class Account {
    String nameAccount;
    String loginID;
    User cardHolder;
    static ArrayList<Transactions> transactions;

    public Account(String nameAccount, User cardHolder, Bank bank) {
        this.nameAccount = nameAccount;
        this.loginID = bank.getAccountUniqueID();
        this.cardHolder = cardHolder;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(double amount, String comment) {
        Transactions newTrans = new Transactions(amount, comment, this);
        transactions.add(newTrans);
    }

    public static double getBalance() {
        double sum = 0;
        for (Transactions s : transactions) {
            sum += s.getAmount();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Account " +
                "loginID = " + loginID + '\'' +
                ", cardHolder = " + cardHolder;
    }
}
