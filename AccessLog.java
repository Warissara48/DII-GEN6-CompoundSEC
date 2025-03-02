import java.util.ArrayList;
import java.util.List;

public class AccessLog { //strategy pattern
    private static volatile AccessLog instance;
    private List<String> logs = new ArrayList<>();

    private AccessLog() {
    }

    public static AccessLog getInstance() {
        if (instance == null) {
            synchronized (AccessLog.class) {
                if (instance == null) {
                    instance = new AccessLog();
                }
            }
        }
        return instance;
    }

    public void logEntry(String entry) {
        logs.add(entry);
        System.out.println(entry);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }
}
