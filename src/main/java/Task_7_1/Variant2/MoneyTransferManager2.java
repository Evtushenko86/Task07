package Task_7_1.Variant2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class MoneyTransferManager2 {
    private final File file;
    private final ArrayList<String> operations = new ArrayList<>();
    private List<Account2> accounts2;
    private final ReentrantLock locker = new ReentrantLock();


    public MoneyTransferManager2(List<Account2> accounts2, File file) {
        this.file = file;
        this.accounts2 = accounts2;
    }

    private void readFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String input;
            while ((input = reader.readLine()) != null) {
                operations.add(input);
            }
        } catch (IOException e) {
        }
    }

    public List<Account2> executeOperations() {
        readFromFile(file);

        ExecutorService threadPool = Executors.newCachedThreadPool();

        for (String s : operations) {
            String[] transferData = s.split(" ");
            Account2 account2From = null;
            Account2 account2To = null;
            int amount = Integer.parseInt(transferData[2]);

            for (Account2 account2 : accounts2) {
                if (account2.getName().equals(transferData[0])) {
                    account2From = account2;
                }
                if (account2.getName().equals(transferData[1])) {
                    account2To = account2;
                }
            }
            threadPool.submit(new Operation2(account2From, account2To, amount, locker));
        }

        threadPool.shutdown();

        return this.accounts2;
    }
}
