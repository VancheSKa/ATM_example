package com.kaivse;

import java.util.Scanner;

public class ATM {
    static Scanner inputParams = new Scanner(System.in);

    public static void main(String[] args) {
        Bank bank = new Bank("ATM-example");

        User user = bank.addNewUser("Tom", "Tom", "1234");
        Account account = new Account("check", user, bank);
        user.addAccount(account);
        bank.addAccount(account);

        User atmUser;
        while (true) {
            atmUser = loginUserMenu(bank, inputParams);
            welcomeMenu(atmUser, inputParams);
        }
    }

    public static User loginUserMenu(Bank bank, Scanner inputParams) {
        String loginID;
        String password;
        User authorizedUser;
        do {
            System.out.println("Enter login: ");
            loginID = inputParams.next();
            System.out.println("Enter password: ");
            password = inputParams.next();

            authorizedUser = bank.checkUserLogin(loginID, password);
            if (authorizedUser == null) {
                System.out.println("u make a mistake in login/password " +
                        "Please try again...");
            }
        }
        while (authorizedUser == null);
        return authorizedUser;
    }

    public static void welcomeMenu(User bankUser, Scanner inputParams) {
        int yourChoice;
        do {
            System.out.println("Choice a menu item: ");
            System.out.println("  " + 1 + ".  Deposit a bank account: ");
            System.out.println("  " + 2 + ".  Withdraw from bank account: ");
            System.out.println("  " + 3 + ".  Transfer from bank account: ");
            System.out.println("  " + 4 + ".  Check your balance: ");
            System.out.println("  " + 5 + ".  Quit");

            yourChoice = inputParams.nextInt();
            if (yourChoice < 1 || yourChoice > 5) {
                System.out.println("U make a mistake");
            }
        }
        while (yourChoice < 1 || yourChoice > 5);
        switch (yourChoice) {
            case 1:
                System.out.println("Deposit a bank account");
                depositFunds(bankUser, inputParams);
                break;
            case 2:
                System.out.println("Withdraw from bank account");
                System.out.println(withdrawFunds());
                break;
            case 3:
                System.out.println("Transfer from bank account");
                transferFunds();
                break;
            case 4:
                System.out.println("Check your balance");
                showTransactionHistory();
                break;
            case 5:
                inputParams.next();
                break;
        }
        if (yourChoice != 5) {
            System.out.println("Choice a menu item: ");
            welcomeMenu(bankUser, inputParams);
        }
    }

    public static void depositFunds(User user, Scanner inputParams) {
        System.out.println("Deposit amount: ");
        double amount = Double.parseDouble(inputParams.next());
        System.out.println("Your comment for this deposit: ");
        String comment = inputParams.next();
        user.addTransactionToAcc(amount, comment);
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
        System.out.println("Check your balance: ");
        System.out.println(Account.getBalance());
    }
}
