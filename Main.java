import GUI.EmployeeGUI;
import GUI.ManagerGUI;
import GUI.VisitorsGUI;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;

public class Main{
    private JFrame frame;
    private JTextField cardIdField;
    private JTextField floorField;
    private JTextField roomField;

    private AccessCard[] accessCards = {
            new AccessCard("MG001", Arrays.asList("Low","Medium","High"),Arrays.asList("101","102","201","202","301","302")),
            new AccessCard("EP001",Arrays.asList("Low","Medium"),Arrays.asList("101","102","201","202")),
            new AccessCard("VS001",Arrays.asList("Low"),Arrays.asList("101","102","Meeting Room")),
    };

    private  void checkAccess(){
        String cardId = cardIdField.getText();
        String floor = floorField.getText();
        String room = roomField.getText();

        AccessCard selectedCard = null;
        for (AccessCard card : accessCards){
            if (card.getCardId().equals(cardId)){
                selectedCard = card;
                break;
            }
        }
        if (selectedCard != null){
            AccessControl accessControl = new FloorAccess();
            accessControl.setAccessCard(selectedCard);
            boolean accessGranted = accessControl.checkAccess(floor, room);

            String result = "Access " + (accessGranted ? "Granted" : "Denied");
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            JOptionPane.showMessageDialog(frame, "Date and Time: " + dateTime + "\n" + result);
        } else {
            JOptionPane.showMessageDialog(frame, "Card ID not found!");
        }
    }

    public Main(){
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JLabel titleLabel = new JLabel("Login with:",SwingConstants.CENTER);
        titleLabel.setBounds(100,5,100,20);
        frame.add(titleLabel);

        JButton managerButton = new JButton("Manager");
        JButton employeeButton = new JButton("Employee");
        JButton visitorButton = new JButton("Visitor");

        managerButton.setBounds(100,30,100,30);
        employeeButton.setBounds(100,70,100,30);
        visitorButton.setBounds(100,110,100,30);

        managerButton.addActionListener(e -> openAccessWindow("Manager"));
        employeeButton.addActionListener(e -> openAccessWindow("Employee"));
        visitorButton.addActionListener(e -> openAccessWindow("Visitor"));

        frame.setLayout(null);
        frame.add(managerButton);
        frame.add(employeeButton);
        frame.add(visitorButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void openAccessWindow(String role) {
        // เปิดหน้าต่าง GUI ที่ตรงกับ role ที่เลือก
        switch (role) {
            case "Manager":
                new ManagerGUI();  // เปิดหน้าต่าง Manager
                break;
            case "Employee":
                new EmployeeGUI();  // เปิดหน้าต่าง Employee
                break;
            case "Visitor":
                new VisitorsGUI();  // เปิดหน้าต่าง Visitor
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid Role");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
