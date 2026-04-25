package org.example;

class BankAccount{

    // Shared resource (bank balance)
    private int balance = 1000;

    // Synchronized method for deposit operation
    public synchronized void deposit(int amount){

        balance += amount;
        System.out.println("Deposited: " + amount
                + ", Balance: " + balance);
    }

    // Synchronized method for withdrawal operation
    public synchronized void withdraw(int amount){

        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount
                    + ", Balance: " + balance);
        }
        else {
            System.out.println(
                    "Insufficient balance to withdraw: "
                            + amount);
        }
    }

    public int getBalance() { return balance; }
}