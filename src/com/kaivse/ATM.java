package com.kaivse;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ATM {
    static Scanner inputParams = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        Bank bank = new Bank("ATM");
        User user = bank.addNewUser("I", "I", "1234", "1234");

        Account account = new Account("string", user);
        user.addAccount(account);

        bank.addAccount(account);

        User curUser;
        while (true) {
            loginMenu();
            welcomeMenu();
        }
    }

    public static void loginMenu() throws NoSuchAlgorithmException, InvalidKeyException {
        final String ALGORITHM = "HmacSHA256";
        System.out.printf("Добро пожаловать!\n");

        String loginID = Account.getUniqueID();
        System.out.println("Введите логин: " + loginID);
        System.out.println("Введите пароль: ");
        String password = inputParams.next();
        password = HmacUtils.calculateHmac(password, ALGORITHM);
        System.out.println("Пользователь с " + loginID + " добавлен");

        welcomeMenu();
    }

    public static void welcomeMenu() {
        Bank bank = new Bank("ATM");
        int yourChoice;
        do {
            System.out.println("Выберите пункт меню:");
            System.out.println("  " + 1 + ".  Пополнить счет");
            System.out.println("  " + 2 + ".  Снять со счёта");
            System.out.println("  " + 3 + ".  Перевести сумму со счёта");
            System.out.println("  " + 4 + ".  История операций");
            System.out.println("  " + 5 + ".  Выйти");

            yourChoice = inputParams.nextInt();
            if (yourChoice < 1 || yourChoice > 5) {
                System.out.println("U make a mistake");
            }
        }
        while (yourChoice < 1 || yourChoice > 5);
        switch (yourChoice) {
            case 1:
                depositFunds();
                break;
            case 2:
                System.out.println("Снятие со счета");
                System.out.println(withdrawFunds());
                break;
            case 3:
                System.out.println("Перевести со счета");
                transferFunds();
                break;
            case 4:
                showTransactionHistory();
                break;
            case 5:
                inputParams.next();
                break;
        }
        if (yourChoice > 5) {
            System.out.println("Выберите пункт");
        }
    }

    public static void depositFunds() {
        System.out.println("Введите имя");
        String name = inputParams.next();
        System.out.println("Укажите фамилию");
        String lastName = inputParams.next();
        System.out.println("loginID");
        String loginID = inputParams.next();
        System.out.println("Укажите пароль");
        String pass = inputParams.next();

        Account account = new Account("", new User(name, lastName, loginID, pass));
        System.out.println("Сумма пополнения: ");
        double amount = Double.parseDouble(inputParams.next());
        System.out.println("Комментарий: ");
        String comment = inputParams.next();
        account.addTransaction(amount, comment);
    }

    public static double withdrawFunds() {
        double sum = Account.getBalance();
        double amount = Double.parseDouble(inputParams.next());
        double left = sum - amount;
        return left;
    }

    public static void transferFunds() {
        System.out.println("in progress");
    }


    public static void showTransactionHistory() {
        System.out.println("История операций: ");
        System.out.println(Account.getBalance());
    }
}
