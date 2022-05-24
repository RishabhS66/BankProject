package com.sahaj.banking.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
    private String accountHolder;
    private long accountNo;
    private double balance;
    private static double maxBalance = 100000.00;
    private static double minBalance = 0.00;
    private static double maxDeposit = 50000.00;
    private static double minDeposit = 500.00;
    private static double maxWithdrawal = 25000.00;
    private static double minWithdrawal = 1000.00;
    private int noOfDeposits = 0;
    private int noOfWithdrawals = 0;
    private String currency = "Rs.";
    private String date;

    public Account(String name, long accountNo) {
        this.accountHolder = name;
        this.accountNo = accountNo;
        this.balance = 0.0;
        this.date = getDate();
    }

    public long getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    private String getDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }


    public boolean depositPossible(double amount) {
        if(amount < minDeposit) {
            System.out.println(String.format("Error! Amount to be deposited is less than the minimum value of %s %.2f.", currency, minDeposit));
            return false;
        }
        if(amount > maxDeposit) {
            System.out.println(String.format("Error! Amount to be deposited is more than the maximum value of %s %.2f.", currency, maxDeposit));
            return false;
        }
        if(this.balance + amount > maxBalance) {
            System.out.println(String.format("Error! Amount to be deposited will make the balance exceed the limit of %s %.2f.", currency, maxBalance));
            return false;
        }
        if(this.noOfDeposits == 3) {
            if(this.date.equals(getDate())) {
                System.out.println("Not Allowed! You have already performed 3 deposit transactions today!");
                return false;
            }
            else {
                this.date = getDate();
                this.noOfDeposits = 0;
            }
        }

        return true;
    }

    public double deposit(double amount) {
        if(!depositPossible(amount)) return this.balance;

        this.noOfDeposits++;
        this.balance += amount;
        return this.balance;
    }

    public boolean withdrawPossible(double amount) {
        if(amount < minWithdrawal) {
            System.out.println(String.format("Error! Amount to be withdrawn is less than the minimum value of %s %.2f.", currency, minWithdrawal));
            return false;
        }
        if(amount > maxWithdrawal) {
            System.out.println(String.format("Error! Amount to be withdrawn is more than the maximum value of %s %.2f.", currency, maxWithdrawal));
            return false;
        }
        if(this.balance - amount < minBalance) {
            System.out.println(String.format("Error! Amount to be withdrawn will reduce the balance to beyond minimum value of %s %.2f.", currency, minBalance));
            return false;
        }
        if(this.noOfWithdrawals == 3) {
            if(this.date.equals(getDate())) {
                System.out.println("Not Allowed! You have already performed 3 withdrawal transactions today!");
                return false;
            }
            else {
                this.date = getDate();
                this.noOfWithdrawals = 0;
            }
        }

        return true;
    }

    public double withdraw(double amount) {
        if(!withdrawPossible(amount)) return this.balance;

        this.noOfWithdrawals++;
        this.balance -= amount;
        return this.balance;
    }

}
