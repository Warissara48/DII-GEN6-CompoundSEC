import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class VisitorsGUI {
    private JFrame frame;
    private JTextField cardIdField;
    private JTextField floorField;
    private JTextField roomField;

    public VisitorsGUI() {
        frame = new JFrame("Visitor Access Control System");

        JLabel roleLabel = new JLabel("Role: Visitor");
        roleLabel.setBounds(20, 20, 200, 20);

        // Card ID Field
        JLabel cardIdLabel = new JLabel("Card ID:");
        cardIdLabel.setBounds(10, 50, 80, 25);
        frame.add(cardIdLabel);

        cardIdField = new JTextField();
        cardIdField.setBounds(100, 50, 160, 25);
        frame.add(cardIdField);

        // Floor Field
        JLabel floorLabel = new JLabel("Floor:");
        floorLabel.setBounds(10, 90, 80, 25);
        frame.add(floorLabel);

        floorField = new JTextField();
        floorField.setBounds(100, 90, 160, 25);
        frame.add(floorField);

        // Room Field
        JLabel roomLabel = new JLabel("Room:");
        roomLabel.setBounds(10, 130, 80, 25);
        frame.add(roomLabel);

        roomField = new JTextField();
        roomField.setBounds(100, 130, 160, 25);
        frame.add(roomField);

        // Check Access Button
        JButton checkButton = new JButton("Check Access");
        checkButton.setBounds(100, 170, 160, 25);
        checkButton.addActionListener(e -> checkAccess());
        frame.add(checkButton);

        // Frame Setup
        frame.setSize(300, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private AccessCard[] accessCards = {
            new AccessCard("MG001", Arrays.asList("Low", "Medium", "High"), Arrays.asList("Room101", "Room102", "Room201", "Room202", "Room301", "Room302")),  // Manager card
            new AccessCard("EP001", Arrays.asList("Low", "Medium"), Arrays.asList("Room101", "Room102", "Room201", "Room202")),  // Employee card
            new AccessCard("VS001", Arrays.asList("Low"), Arrays.asList("Meeting Room" , "Room101"))  // Visitor card
    };

    private void checkAccess() {
        String cardId = cardIdField.getText();
        String floor = floorField.getText();
        String room = roomField.getText();

        // Find the card with the provided cardId
        AccessCard selectedCard = null;
        for (AccessCard card : accessCards) {
            if (card.getCardId().equals(cardId)) {
                selectedCard = card;
                break;
            }
        }

        if (selectedCard != null) {
            AccessControl accessControl = new FloorAccess();  // Example: Check access to floor
            accessControl.setAccessCard(selectedCard);
            boolean accessGranted = accessControl.checkAccess(floor, room);

            String result = "Access " + (accessGranted ? "Granted" : "Denied");
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            JOptionPane.showMessageDialog(frame, "Date and Time: " + dateTime + "\n" + result);
        } else {
            JOptionPane.showMessageDialog(frame, "Card ID not found.!");
        }
    }
}
