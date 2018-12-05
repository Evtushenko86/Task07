package Task_7_1;

import Task_7_1.Variant1.Account1;
import Task_7_1.Variant2.Account2;
import Task_7_1.Variant1.MoneyTransferManager1;
import Task_7_1.Variant2.MoneyTransferManager2;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MoneyTransferManagerTest {

    private static final String fileName = "/Users/aevtushenko/Downloads/EpamTask/Task07/src/test/resources/Operations.txt";

    @Test
    public void should_execute_all_operations_variant_1() throws InterruptedException {
        List<Account1> account1s = new ArrayList<>();
        account1s.add(new Account1("vasya",1000));
        account1s.add(new Account1("petya",1000));
        account1s.add(new Account1("tanya",1000));
        account1s.add(new Account1("nina",1000));
        Account1 kuzya = new Account1("kuzya",1000);
        account1s.add(kuzya);

        MoneyTransferManager1 moneyTransferManager1 = new MoneyTransferManager1(account1s, new File(fileName));
        moneyTransferManager1.executeOperations();

        assertThat("The thread is working wrong", 3000, is(kuzya.getBalance()));
    }

    @Test
    public void should_execute_all_operations_variant_2() throws InterruptedException {
        List<Account2> accounts2 = new ArrayList<>();
        accounts2.add(new Account2("vasya",1000));
        accounts2.add(new Account2("petya",1000));
        accounts2.add(new Account2("tanya",1000));
        accounts2.add(new Account2("nina",1000));
        Account2 kuzya = new Account2("kuzya",1000);
        accounts2.add(kuzya);

        MoneyTransferManager2 moneyTransferManager2 = new MoneyTransferManager2(accounts2, new File(fileName));
        moneyTransferManager2.executeOperations();

        assertThat("ThreadPool is working wrongly", 3000, is(kuzya.getBalance()));
    }
}
