import javax.swing.*;
import java.awt.*;

class EmployeeCard extends Card{
    public EmployeeCard(String username, String password) {
        super(username, password);
    }

    @Override
    public void accessSystem() {
        JFrame employeeFrame = new JFrame("Employee Panel");
        employeeFrame.setSize(300, 300);
        JPanel panel = new JPanel();
        employeeFrame.add(panel);
        panel.setLayout(new GridLayout(4, 1));

        JButton lowFloorButton = new JButton("Medium Floor");
        JButton meetingRoomButton = new JButton("Room2");
        JButton auditLogButton = new JButton("Audit Log");
        JButton backButton = new JButton("Back");


        panel.add(lowFloorButton);
        panel.add(meetingRoomButton);
        panel.add(auditLogButton);
        panel.add(backButton);

        // Add ActionListener for each button
        lowFloorButton.addActionListener(e -> promptForPassword("Medium Floor"));
        meetingRoomButton.addActionListener(e -> promptForPassword("Room2"));
        auditLogButton.addActionListener(e -> showAuditLog());
        backButton.addActionListener(e -> employeeFrame.dispose()); // ปิดหน้าต่างและย้อนกลับ

        employeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeFrame.setVisible(true);
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
        String logContent = getLog(); // Get the audit log content
        JTextArea logArea = new JTextArea(logContent);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        JFrame logFrame = new JFrame("Audit Log");
        logFrame.setSize(400, 300);
        logFrame.add(scrollPane);
        logFrame.setVisible(true);
    }
}

