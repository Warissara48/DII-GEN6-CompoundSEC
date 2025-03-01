import Strategy.AccessStrategy;
import Strategy.WeekdayAccessStrategy;
import javax.swing.*;
import java.awt.*;
import java.util.List;

class UserCard extends Card {
    private AccessStrategy accessStrategy;

    public UserCard(String username, String password) {
        super(username, password);
        this.accessStrategy = new WeekdayAccessStrategy();
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

        JButton lowFloorButton = new JButton("Low Floor");
        JButton mediumFloorButton = new JButton("Medium Floor");
        JButton highFloorButton = new JButton("High Floor");
        JButton backButton = new JButton("Back");

        panel.add(lowFloorButton);
        panel.add(mediumFloorButton);
        panel.add(highFloorButton);
        panel.add(backButton);

        // Action listeners for selecting the floor
        lowFloorButton.addActionListener(e -> showRoomSelection("Low Floor"));
        mediumFloorButton.addActionListener(e -> showRoomSelection("Medium Floor"));
        highFloorButton.addActionListener(e -> showRoomSelection("High Floor"));
        backButton.addActionListener(e -> userFrame.dispose()); // Close window and go back

        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setVisible(true);
    }

    private void showRoomSelection(String floor) {
        String[] rooms = new String[]{};

        // Define all rooms per floor
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
        // Check if the selected room is accessible
        if (isRoomAccessible(floor, room)) {
            JPasswordField passwordField = new JPasswordField(10);
            int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter password to access " + room, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                String enteredPassword = new String(passwordField.getPassword());
                if (authenticate(enteredPassword)) {
                    logAccess("Accessed " + floor + " "  + room);
                    JOptionPane.showMessageDialog(null, "Access granted to " + room);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid password. Access denied.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You do not have permission to access " + room + " on the " + floor + ".");
        }
    }

    private boolean isRoomAccessible(String floor, String room) {
        if (floor.equals("Low Floor") && (room.equals("Room 1") || room.equals("Room 2"))) {
            return true;
        } else if (floor.equals("Medium Floor") && room.equals("Room 1")) {
            return true;
        } else {
            return false; // All other combinations are not accessible
        }
    }
}
