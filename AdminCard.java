import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class AdminCard extends Card {
    private static List<UserCard> users = new ArrayList<>(); // List of User Cards

    public AdminCard(String username, String password) {
        super(username, password);
    }

    @Override
    public void accessSystem() {
        JFrame adminFrame = new JFrame("Admin Panel");
        adminFrame.setSize(300, 400);
        JPanel panel = new JPanel();
        adminFrame.add(panel);
        panel.setLayout(new GridLayout(6, 1));

        JButton lowFloorButton = new JButton("Low Floor");
        JButton mediumFloorButton = new JButton("Medium Floor");
        JButton highFloorButton = new JButton("High Floor");
        JButton addCardButton = new JButton("Add Card");
        JButton withdrawCardButton = new JButton("Withdraw Card");
        JButton auditLogButton = new JButton("Audit Log");
        JButton backButton = new JButton("Back");

        panel.add(lowFloorButton);
        panel.add(mediumFloorButton);
        panel.add(highFloorButton);
        panel.add(addCardButton);
        panel.add(withdrawCardButton);
        panel.add(auditLogButton);
        panel.add(backButton);

        lowFloorButton.addActionListener(e -> showRoomSelection("Low Floor"));
        mediumFloorButton.addActionListener(e -> showRoomSelection("Medium Floor"));
        highFloorButton.addActionListener(e -> showRoomSelection("High Floor"));
        addCardButton.addActionListener(e -> promptForCardDetails()); // Add Card functionality
        withdrawCardButton.addActionListener(e -> promptForWithdrawCard()); // Withdraw Card functionality
        auditLogButton.addActionListener(e -> showAuditLog());
        backButton.addActionListener(e -> adminFrame.dispose()); // Close the window and go back

        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setVisible(true);
    }

    private void showRoomSelection(String floor) {
        String[] rooms = new String[]{};

        // Define all rooms per floor (Admin has access to all)
        if (floor.equals("Low Floor")) {
            rooms = new String[]{"Room 1", "Room 2"};
        } else if (floor.equals("Medium Floor")) {
            rooms = new String[]{"Room 1", "Room 2", "Meeting Room"};
        } else if (floor.equals("High Floor")) {
            rooms = new String[]{"Room 1", "Room 2"};
        }

        // Show the room selection dialog with all rooms listed
        String room = (String) JOptionPane.showInputDialog(null,
                "Select a room in " + floor,
                "Room Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                rooms,
                rooms[0]);

        if (room != null) {
            promptForPassword(floor, room);
        }
    }

    private void promptForPassword(String floor, String room) {
        // Since Admin has access to all rooms, no need to check accessibility
        JPasswordField passwordField = new JPasswordField(10);
        int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter password to access " + room, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String enteredPassword = new String(passwordField.getPassword());
            if (authenticate(enteredPassword)) {
                logAccess("Accessed " + room);
                JOptionPane.showMessageDialog(null, "Access granted to " + room);
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

    // Function to Add Card
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

            // Add card to a new user
            UserCard newUser = new UserCard(name, "defaultPassword"); // Default password for new user
            users.add(newUser);
            newUser.logAccess("Added card with access to floor: " + floor + ", room: " + room);

            JOptionPane.showMessageDialog(null, "Card added for " + name + " with access to floor " + floor + " and room " + room);
        }
    }

    // Function to Withdraw Card
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
            users.removeIf(user -> user.getUsername().equals(selectedUser)); // Remove user from list
            JOptionPane.showMessageDialog(null, "Card for " + selectedUser + " has been withdrawn.");
        }
    }
}
