package Task_7_1.Variant1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoneyTransferManager1 {
    private final File file;
    private final ArrayList<String> operations = new ArrayList<>();
    private List<Account1> accounts1;
    private final List<Thread> threads = new ArrayList<>();

    public MoneyTransferManager1(List<Account1> accounts1, File file) {
        this.file = file;
        this.accounts1 = accounts1;
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

    public List<Account1> executeOperations() throws InterruptedException {
        readFromFile(file);

        for (String s : operations) {
            String[] transferData = s.split(" ");
            Account1 account1From = null;
            Account1 account1To = null;
            int amount = Integer.parseInt(transferData[2]);

            for (Account1 account1 : accounts1) {
                if (account1.getName().equals(transferData[0])) {
                    account1From = account1;
                }
                if (account1.getName().equals(transferData[1])) {
                    account1To = account1;
                }
            }

            Operation1 operation1 = new Operation1(account1From, account1To, amount);
            threads.add(operation1);
            operation1.start();
        }

        for (Thread thread: threads) {
            thread.join();
        }

        return this.accounts1;
    }
}
