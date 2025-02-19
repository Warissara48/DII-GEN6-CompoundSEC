import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class AdminCard extends Card {
    private static List<UserCard> users = new ArrayList<>(); // List ของ User

    public AdminCard(String username, String password) {
        super(username, password);
    }

    @Override
    public void accessSystem() {
        JFrame adminFrame = new JFrame("Admin Panel");
        adminFrame.setSize(300, 300);
        JPanel panel = new JPanel();
        adminFrame.add(panel);
        panel.setLayout(new GridLayout(4, 1));

        JButton addCardButton = new JButton("Add Card");
        JButton withdrawCardButton = new JButton("Withdraw Card");
        JButton auditLogButton = new JButton("Audit Log");
        JButton backButton = new JButton("Back");

        panel.add(addCardButton);
        panel.add(withdrawCardButton);
        panel.add(auditLogButton);
        panel.add(backButton);

        addCardButton.addActionListener(e -> promptForCardDetails()); // เพิ่มบัตร
        withdrawCardButton.addActionListener(e -> promptForWithdrawCard()); // ลบบัตร
        auditLogButton.addActionListener(e -> showAuditLog());
        backButton.addActionListener(e -> adminFrame.dispose()); // ปิดหน้าต่างและย้อนกลับ


        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setVisible(true);
    }

    // ฟังก์ชันเพิ่มบัตร (Add Card)
    private void promptForCardDetails() {
        JTextField nameField = new JTextField(10);
        JTextField floorField = new JTextField(10);
        JTextField roomField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Floor:"));
        panel.add(floorField);
        panel.add(new JLabel("Room:"));
        panel.add(roomField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Enter details to Add Card", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String floor = floorField.getText();
            String room = roomField.getText();

            // เพิ่มบัตรให้ผู้ใช้ใหม่
            UserCard newUser = new UserCard(name, "defaultPassword"); // กำหนดรหัสผ่านเริ่มต้น
            users.add(newUser);
            newUser.logAccess("Added card with access to floor: " + floor + ", room: " + room);

            JOptionPane.showMessageDialog(null, "Card added for " + name + " with access to floor " + floor + " and room " + room);
        }
    }

    // ฟังก์ชันลบบัตร (Withdraw Card)
    private void promptForWithdrawCard() {
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No cards to withdraw.");
            return;
        }

        String[] userNames = users.stream().map(Card::getUsername).toArray(String[]::new);
        String selectedUser = (String) JOptionPane.showInputDialog(null, "Select user to withdraw card",
                "Withdraw Card", JOptionPane.PLAIN_MESSAGE,
                null, userNames, userNames[0]);

        if (selectedUser != null) {
            users.removeIf(user -> user.getUsername().equals(selectedUser)); // ลบผู้ใช้จากรายการ
            JOptionPane.showMessageDialog(null, "Card for " + selectedUser + " has been withdrawn.");
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
