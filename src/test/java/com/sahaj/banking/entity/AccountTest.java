package com.sahaj.banking.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account testAcc = new Account("Test", 10000);

    @Test
    void getAccountNo() {
        assertEquals(10000, testAcc.getAccountNo());
    }

    @Test
    void getBalance() {
        assertEquals(0, testAcc.getBalance());
        testAcc.deposit(2000.25);
        assertEquals(2000.25, testAcc.getBalance());
    }

    @Test
    void depositPossible() {
        assertEquals(false, testAcc.depositPossible(55000));
        assertEquals(false, testAcc.depositPossible(499.99));
        assertEquals(true, testAcc.depositPossible(2001.00));
        testAcc.deposit(49000);
        testAcc.deposit(49000);
        assertEquals(false, testAcc.depositPossible(2001.00));
        testAcc.deposit(1000);
        assertEquals(false, testAcc.depositPossible(1000.00));
    }

    @Test
    void deposit() {
        testAcc.deposit(2000.25);
        assertEquals(51000.25, testAcc.deposit(49000.00));
        assertEquals(51000.25, testAcc.deposit(55000));
        assertEquals(51000.25, testAcc.deposit(499.00));
        assertEquals(51000.25, testAcc.deposit(50000.00));
        assertEquals(60000.25, testAcc.deposit(9000.00));
        assertEquals(60000.25, testAcc.deposit(1000.00));
    }

    @Test
    void withdrawPossible() {
        testAcc.deposit(20000);
        assertEquals(false, testAcc.withdrawPossible(55000));
        assertEquals(false, testAcc.withdrawPossible(999.00));
        assertEquals(true, testAcc.withdrawPossible(2001.00));
        assertEquals(false, testAcc.withdrawPossible(20001.00));
        testAcc.withdraw(2000);
        testAcc.withdraw(2000);
        testAcc.withdraw(2000);
        assertEquals(false, testAcc.withdrawPossible(2000.00));
    }

    @Test
    void withdraw() {
        testAcc.deposit(20000.00);
        assertEquals(18000.00, testAcc.withdraw(2000.00));
        assertEquals(18000.00, testAcc.withdraw(26000));
        assertEquals(18000.00, testAcc.withdraw(499.00));
        assertEquals(18000.00, testAcc.withdraw(20000));
        assertEquals(9000, testAcc.withdraw(9000.00));
        assertEquals(8000, testAcc.withdraw(1000.00));
        assertEquals(8000, testAcc.withdraw(1000.00));
    }
}