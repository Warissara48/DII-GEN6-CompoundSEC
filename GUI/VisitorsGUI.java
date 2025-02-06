package GUI;

import javax.swing.*;

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

    private void checkAccess() {
        // Implement the check access logic specific to Visitor here
    }
}
