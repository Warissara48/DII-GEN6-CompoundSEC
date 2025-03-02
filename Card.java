import Observer.AccessMonitor;
import Observer.AdminNotifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Card {  // Abstract Class
    private String username;  // Data Hiding
    private String password;
    private int failedAttempts = 0;

    private static AccessMonitor accessMonitor = new AccessMonitor();

    static {
        accessMonitor.addObserver(new AdminNotifier());
    }

    public Card(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    protected boolean authenticate(String password) {
        if (this.password.equals(password)) {
            failedAttempts = 0;
            return true;
        } else {
            failedAttempts++;
            logAccess("Failed login attempt " + failedAttempts + " for user: " + username);

            if (failedAttempts == 3) {
                notifyAdmin("Incorrect password entered 3 times for user: " + username);
            }
            return false;
        }
    }

    private void notifyAdmin(String message) {
        accessMonitor.notifyObservers(message);
    }

    public abstract void accessSystem();

    protected void logAccess(String action) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        int colWidth1 = 20, colWidth2 = 50, colWidth3 = 19;
        String col1 = String.format("%-" + colWidth1 + "s", username);
        String col2 = String.format("%-" + colWidth2 + "s", action);
        String col3 = String.format("%-" + colWidth3 + "s", timestamp);
        String logEntry = "| " + col1 + " | " + col2 + " | " + col3 + " |";

        AccessLog.getInstance().logEntry(logEntry);
    }


    protected String getAuditLog() {
        return String.join("\n", AccessLog.getInstance().getLogs());
    }
}
