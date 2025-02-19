import Observer.AccessMonitor;
import Observer.AdminNotifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Card {  // Abstract Class
    private String username;  // Data Hiding
    private String password;
    private int failedAttempts = 0; // ตัวแปรนับจำนวนการใส่รหัสผิด


    public Card(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Card(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    protected boolean authenticate(String password) {
        if (this.password.equals(password)) {
            failedAttempts = 0; // รีเซ็ตเมื่อใส่รหัสถูก
            logAccess("Access granted for user: " + username); // บันทึกเมื่อเข้าถึงสำเร็จ
            return true;
        } else {
            failedAttempts++;
            logAccess("Failed login attempt for user: " + username);
            if (failedAttempts == 3) {
                // แจ้งเตือน Admin เมื่อใส่รหัสผิด 3 ครั้ง
                notifyAdmin("Incorrect password entered 3 times for user: " + username);
            }
            return false;
        }
    }

    private void notifyAdmin(String message) {
        AccessMonitor monitor = new AccessMonitor();
        AdminNotifier adminNotifier = new AdminNotifier();
        monitor.addObserver(adminNotifier);
        monitor.notifyObservers(message);
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
