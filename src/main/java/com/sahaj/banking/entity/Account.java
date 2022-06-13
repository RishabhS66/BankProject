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


    public boolean depositPossible(double amount) throws Exception {
        try {
            if (amount < minDeposit) {
                throw new IllegalArgumentException(String.format("Error! Amount to be deposited is less than the minimum value of %s %.2f.", currency, minDeposit));
            }
            if (amount > maxDeposit) {
                throw new IllegalArgumentException(String.format("Error! Amount to be deposited is more than the maximum value of %s %.2f.", currency, maxDeposit));
            }
            if (this.balance + amount > maxBalance) {
                throw new IllegalArgumentException(String.format("Error! Amount to be deposited will make the balance exceed the limit of %s %.2f.", currency, maxBalance));
            }
            if (this.noOfDeposits == 3) {
                if (this.date.equals(getDate())) {
                    throw new IllegalArgumentException("Not Allowed! You have already performed 3 deposit transactions today!");
                } else {
                    this.date = getDate();
                    this.noOfDeposits = 0;
                }
            }

            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public double deposit(double amount) {
        try {
            boolean check = depositPossible(amount);

            this.noOfDeposits++;
            this.balance += amount;
            return this.balance;
        }
        catch(Exception e) {
            return this.balance;
        }
    }

    public boolean withdrawPossible(double amount) throws Exception {
        try {
            if (amount < minWithdrawal) {
                throw new IllegalArgumentException(String.format("Error! Amount to be withdrawn is less than the minimum value of %s %.2f.", currency, minWithdrawal));
            }
            if (amount > maxWithdrawal) {
                throw new IllegalArgumentException(String.format("Error! Amount to be withdrawn is more than the maximum value of %s %.2f.", currency, maxWithdrawal));
            }
            if (this.balance - amount < minBalance) {
                throw new IllegalArgumentException(String.format("Error! Amount to be withdrawn will reduce the balance to beyond minimum value of %s %.2f.", currency, minBalance));
            }
            if (this.noOfWithdrawals == 3) {
                if (this.date.equals(getDate())) {
                    throw new IllegalArgumentException("Not Allowed! You have already performed 3 withdrawal transactions today!");
                } else {
                    this.date = getDate();
                    this.noOfWithdrawals = 0;
                }
            }

            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public double withdraw(double amount) {
        try {
            if (!withdrawPossible(amount)) return this.balance;

            this.noOfWithdrawals++;
            this.balance -= amount;
            return this.balance;
        }
        catch (Exception e) {
            return this.balance;
        }
    }

}
