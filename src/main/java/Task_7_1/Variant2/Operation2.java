package Task_7_1.Variant2;

import java.util.concurrent.locks.ReentrantLock;

public class Operation2 extends Thread {
    private Account2 account2From;
    private Account2 account2To;
    private int amount;
    private final ReentrantLock locker;


    public Operation2(Account2 account2From, Account2 account2To, int amount, ReentrantLock lock) {
        this.account2From = account2From;
        this.account2To = account2To;
        this.amount = amount;
        this.locker = lock;
    }

    @Override
    public void run() {
        transfer(account2From, account2To, amount);
    }

    private void transfer(Account2 account2From, Account2 account2To, int amount) {
        locker.lock();
        account2To.deposit(amount);
        account2From.withdraw(amount);
        System.out.println("The operation done by " + Thread.currentThread().getName());
        locker.unlock();
    }
}