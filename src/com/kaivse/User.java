package com.kaivse;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Ivan Kalinin 16.10.2022
 */
public class User {
    String name;
    String lastName;
    String loginID;
    String password;
    ArrayList<Account> accounts;
    final String ALGORITHM = "HmacSHA256";

    public User(String name, String lastName, String loginID, String password) {
        this.name = name;
        this.lastName = lastName;

        try {
            this.password = HmacUtils.calculateHmac(password, ALGORITHM);
        } catch (Exception e) {
            System.err.println("error! : " + e.getMessage());
            System.exit(1);
        }

        this.loginID = getNewUniqueID();
        this.accounts = new ArrayList<>();
        System.out.printf("Новый пользователь %s %s" + '\n'
                        + "с ID %s был создан.\n",
                lastName, name, this.loginID);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public static String getNewUniqueID() {
        final int maxLength = 4;
        String uuid = UUID.randomUUID().toString();
        String requestId = uuid.replaceAll("[^0-9]", "");
        String subString = requestId.substring(0, maxLength);

        return subString;
    }

    @Override
    public String toString() {
        return "User: " + '\n' +
                " имя = " + name + '\n' +
                " пароль = " + password + '\n' +
                " фамилия= " + lastName + '\n';
    }
}
