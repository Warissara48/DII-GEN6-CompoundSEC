import Strategy.AccessStrategy;
import Strategy.VIPAccessStrategy;
import javax.swing.*;
import java.awt.*;

class UserCard extends Card {
    private AccessStrategy accessStrategy;


    public UserCard(String username, String password) {
        super(username, password);
        this.accessStrategy = new VIPAccessStrategy();
    }

    public void setAccessStrategy(AccessStrategy strategy) {
        this.accessStrategy = strategy;
    }

    public boolean requestAccess() {
        return accessStrategy != null && accessStrategy.canAccess();
    }

    @Override
    public void accessSystem() {
        JFrame userFrame = new JFrame("User Panel");
        userFrame.setSize(300, 300);
        JPanel panel = new JPanel();
        userFrame.add(panel);
        panel.setLayout(new GridLayout(4, 1));

        JButton lowFloorButton = new JButton("Low Floor (Room 1)");
        JButton meetingRoomButton = new JButton("Meeting Room");
        JButton auditLogButton = new JButton("Audit Log");
        JButton backButton = new JButton("Back");

        panel.add(lowFloorButton);
        panel.add(meetingRoomButton);
        panel.add(auditLogButton);
        panel.add(backButton);

        lowFloorButton.addActionListener(e -> promptForPassword("Low Floor Room 1"));
        meetingRoomButton.addActionListener(e -> promptForPassword("Meeting Room"));
        auditLogButton.addActionListener(e -> showAuditLog());
        backButton.addActionListener(e -> userFrame.dispose()); // ปิดหน้าต่างและย้อนกลับ

        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setVisible(true);
    }

    private void promptForPassword(String action) {
        JPasswordField passwordField = new JPasswordField(10);
        int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter password to access " + action, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String enteredPassword = new String(passwordField.getPassword());
            if (authenticate(enteredPassword)) {
                logAccess("Accessed " + action);
                JOptionPane.showMessageDialog(null, "Access granted to " + action);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid password. Access denied.");
            }
        }
    }

    private void showAuditLog() {
        JTextArea logArea = new JTextArea(getAuditLog());
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        JFrame logFrame = new JFrame("Audit Log");
        logFrame.setSize(400, 300);
        logFrame.add(scrollPane);
        logFrame.setVisible(true);
    }

}


