import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

abstract class Card {  //Abstract Class
    private String username;  //Data Hiding
    private String password;
    private List<String> actionLog;

    public Card(String username, String password) {
        this.username = username;
        this.password = password;
        this.actionLog = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    protected boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public abstract void accessSystem();

    protected void logAccess(String action) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);
        String logEntry = username + " performed action: " + action + " at " + timestamp;
        actionLog.add(logEntry);
        System.out.println(logEntry);
    }

    public String getLog()    {
        return String.join("\n", actionLog);
    }
}
