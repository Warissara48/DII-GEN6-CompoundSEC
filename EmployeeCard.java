import Strategy.AccessStrategy;
import Strategy.EverydayAccessStrategy;
import javax.swing.*;
import java.awt.*;

class EmployeeCard extends Card {  //Inheritance
    private AccessStrategy accessStrategy;

    public EmployeeCard(String username, String password) {
        super(username, password);
        this.accessStrategy = new EverydayAccessStrategy();
    }

    public void setAccessStrategy(AccessStrategy strategy) {
        this.accessStrategy = strategy;
    }

    public boolean requestAccess() {
        return accessStrategy != null && accessStrategy.canAccess();
    }

    @Override
    public void accessSystem() {
        JFrame employeeFrame = new JFrame("Employee Panel");
        employeeFrame.setSize(300, 300);
        JPanel panel = new JPanel();
        employeeFrame.add(panel);
        panel.setLayout(new GridLayout(4, 1));

        JButton lowFloorButton = new JButton("Low Floor");
        JButton mediumFloorButton = new JButton("Medium Floor");
        JButton highFloorButton = new JButton("High Floor");
        JButton backButton = new JButton("Back");

        panel.add(lowFloorButton);
        panel.add(mediumFloorButton);
        panel.add(highFloorButton);
        panel.add(backButton);

        lowFloorButton.addActionListener(e -> showRoomSelection("Low Floor"));
        mediumFloorButton.addActionListener(e -> showRoomSelection("Medium Floor"));
        highFloorButton.addActionListener(e -> showRoomSelection("High Floor"));
        backButton.addActionListener(e -> employeeFrame.dispose());

        employeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeFrame.setVisible(true);
    }

    private void showRoomSelection(String floor) {
        String[] rooms = new String[]{};

        if (floor.equals("Low Floor")) {
            rooms = new String[]{"Room 1", "Room 2"};
        } else if (floor.equals("Medium Floor")) {
            rooms = new String[]{"Room 1", "Room 2", "Meeting Room"};
        } else if (floor.equals("High Floor")) {
            rooms = new String[]{"Room 1", "Room 2"};
        }

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
                    logAccess("Accessed granted to " + floor + " " + room);
                    JOptionPane.showMessageDialog(null, "Access granted to "  + room);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid password. Access denied.");
                }
            }
        } else {
            logAccess("Accessed denied to " + floor + " " + room);
            JOptionPane.showMessageDialog(null, "You do not have permission to access " + room + " on the " + floor + ".");
        }
    }

    private boolean isRoomAccessible(String floor, String room) {
        if (floor.equals("High Floor") && room.equals("Room 2")) {
            return false;
        } else {
            return true;
        }
    }
}
