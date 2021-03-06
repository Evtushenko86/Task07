package Task_7_1.Variant2;

public class Account2 {
    private final String name;
    private volatile int balance;

    public Account2(String name, int initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public synchronized void withdraw(int amount){
        balance -= amount;
    }

    public synchronized void deposit(int amount){
        balance += amount;
    }

    public synchronized int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return name + ", balance = " + balance;
    }
}
