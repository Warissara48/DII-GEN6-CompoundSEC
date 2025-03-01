import Observer.AccessMonitor;
import Observer.AdminNotifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Card {  // Abstract Class
    private String username;  // Data Hiding
    private String password;
    private int failedAttempts = 0; // ตัวแปรนับจำนวนการใส่รหัสผิด

    // ใช้ instance เดียวกันตลอด
    private static AccessMonitor accessMonitor = new AccessMonitor();

    static {
        accessMonitor.addObserver(new AdminNotifier()); // ให้ AdminNotifier ติดตามเหตุการณ์
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
            failedAttempts = 0; // รีเซ็ตเมื่อใส่รหัสถูก
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
        accessMonitor.notifyObservers(message);  // ใช้ Instance เดียวกัน
    }

    public abstract void accessSystem();

    protected void logAccess(String action) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);
        // กำหนดขนาดคอลัมน์
        int colWidth1 = 20, colWidth2 = 40, colWidth3 = 19;
        // เติมช่องว่างให้ข้อมูลแต่ละช่อง
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
