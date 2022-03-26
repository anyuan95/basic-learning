package org.example.basic.oj.leetcode.Q2043;

class Bank {

    private long[] accounts;
    private long count;

    public Bank(long[] balance) {
        this.accounts = balance;
        this.count = balance.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 < 1 || account1 > count || account2 < 1 || account2 > count || accounts[account1 - 1] < money) {
            return false;
        }
        accounts[account1 - 1] -= money;
        accounts[account2 - 1] += money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if (account < 1 || account > count) {
            return false;
        }
        accounts[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account < 1 || account > count || accounts[account - 1] < money) {
            return false;
        }
        accounts[account - 1] -= money;
        return true;
    }
}