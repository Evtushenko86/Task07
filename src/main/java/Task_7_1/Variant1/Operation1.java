package Task_7_1.Variant1;

public class Operation1 extends Thread {
    private Account1 account1From;
    private Account1 account1To;
    private int amount;

    public Operation1(Account1 account1From, Account1 account1To, int amount) {
        this.account1From = account1From;
        this.account1To = account1To;
        this.amount = amount;
    }

    @Override
    public void run() {
        transfer(account1From, account1To, amount);
    }

    private void transfer(Account1 account1From, Account1 account1To, int amount) {
        synchronized (account1From) {
            synchronized (account1To) {
                account1To.deposit(amount);
            }
            account1From.withdraw(amount);
        }
        System.out.println("The operation done by " + Thread.currentThread().getName());
    }
}