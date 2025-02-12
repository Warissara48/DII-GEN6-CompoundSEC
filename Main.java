import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    private JFrame frame;
    private JTextField cardIdField;
    private JTextField floorField;
    private JTextField roomField;

    // Create some sample access cards with unique cardIds
    private AccessCard[] accessCards = {
            new AccessCard("MG001", Arrays.asList("Low", "Medium", "High"), Arrays.asList("Room101", "Room102", "Room201", "Room202", "Room301", "Room302")),  // Manager card
            new AccessCard("EP001", Arrays.asList("Low", "Medium"), Arrays.asList("Room101", "Room102", "Room201", "Room202")),  // Employee card
            new AccessCard("VS001", Arrays.asList("Low"), Arrays.asList("Meeting Room" , "Room101"))  // Visitor card
    };

    public AccessCard[] getAccessCards() {
        return accessCards;
    }


    public Main() {
        JFrame frame = new JFrame("Login with");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JLabel titleLabel = new JLabel("Login with:", SwingConstants.CENTER);
        titleLabel.setBounds(100, 5, 100, 20);
        frame.add(titleLabel);

        // Create buttons for Manager, Employee, Visitors
        JButton managerButton = new JButton("Manager");
        JButton employeeButton = new JButton("Employee");
        JButton visitorsButton = new JButton("Visitors");

        // Set positions and sizes of buttons
        managerButton.setBounds(100, 30, 100, 30);
        employeeButton.setBounds(100, 70, 100, 30);
        visitorsButton.setBounds(100, 110, 100, 30);

        // Add ActionListener for each button
        managerButton.addActionListener(e -> openAccessWindow("Manager"));
        employeeButton.addActionListener(e -> openAccessWindow("Employee"));
        visitorsButton.addActionListener(e -> openAccessWindow("Visitors"));

        // Add buttons to the frame
        frame.setLayout(null);
        frame.add(managerButton);
        frame.add(employeeButton);
        frame.add(visitorsButton);

        // Display the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void openAccessWindow(String role) {
        // เปิดหน้าต่าง GUI ที่ตรงกับ role ที่เลือก
        switch (role) {
            case "Manager":
                new ManagerGUI();// เปิดหน้าต่าง Manager
                break;
            case "Employee":
                new EmployeeGUI(this);  // เปิดหน้าต่าง Employee
                break;
            case "Visitors":
                new VisitorsGUI();  // เปิดหน้าต่าง Visitor
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid Role");
        }
    }

    public void checkAccess(String cardId, String floor, String room) {
        AccessUtils.checkAccess(cardId, floor, room, accessCards);
    }


    public static void main(String[] args) {
        new Main();
    }

}
