package Task_7_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class PropertiesFileReader {
    private ReentrantLock fileLock = new ReentrantLock();
    private Map<String, String> properties = new ConcurrentHashMap<>();

    public void getProperties(String fileName) throws IOException {
        String delemiters = "=: ";
        String tmp;
        StringTokenizer tokenizer;
        fileLock.lock();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((tmp = bufferedReader.readLine()) != null) {
                tokenizer = new StringTokenizer(tmp, delemiters);
                properties.put(tokenizer.nextToken(), tokenizer.nextToken());
                }
        }
        finally {
            fileLock.unlock();
        }
    }

    public String getValueByKey(String key) {
        String value = properties.get(key);
        return value;
    }
}
