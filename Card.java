import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Card {  //Abstract Class
    private String username;  //Data Hiding
    private String password;

    public Card(String username, String password) {
        this.username = username;
        this.password = password;
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

        AccessLog.getInstance().logEntry(logEntry);
    }

    protected String getAuditLog() {
        return String.join("\n", AccessLog.getInstance().getLogs());
    }
}
