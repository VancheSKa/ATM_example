package com.kaivse;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Ivan Kalinin 16.10.2022
 */
public class Account {

    String loginID;
    User cardHolder;
    static ArrayList<Transactions> transactions;

    public Account(String loginID, User cardHolder) {
        this.loginID = loginID;
        this.cardHolder = cardHolder;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(double amount, String comment) {
        Transactions newTrans = new Transactions(amount, comment, this);
//        this.transactions.add(newTrans);
        transactions.add(newTrans);
    }

    public static double getBalance() {
        double sum = 0;
        for (Transactions s : transactions) {
            sum += s.getAmount();
        }
        return sum;
    }

    public static String getUniqueID() {
        final int maxLength = 4;
        String uuid = UUID.randomUUID().toString();
        String requestId = uuid.replaceAll("[^0-9]", "");
        String subString = requestId.substring(0, maxLength);

        return subString;
    }

    @Override
    public String toString() {
        return "Account{" +
                "loginID='" + loginID + '\'' +
                ", cardHolder=" + cardHolder +
                '}';
    }
}
