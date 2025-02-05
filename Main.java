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
            new AccessCard("MG001", Arrays.asList("Low Floor","Medium Floor","High Floor"),Arrays.asList("Room101","Room102","Room201","Room202","Room301","Room302")),
            new AccessCard("EP001",Arrays.asList("Low Floor","Meduim Floor"),Arrays.asList("Room101","Room102","Room201","Room202")),
            new AccessCard("VS001",Arrays.asList("Low Floor"),Arrays.asList("Room101","Room102","Meeting Room")),
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

    public void openAccessWindow(String role){
        frame = new JFrame("Access Control System");

        JLabel rolelabel = new JLabel("Role:" + role);
        rolelabel.setBounds(20,20,200,20);

        JLabel cardIdlabel = new JLabel("Card ID:");
        cardIdlabel.setBounds(10,10,80,25);
        frame.add(cardIdlabel);

        cardIdField = new JTextField();
        cardIdField.setBounds(100,10,160,25);
        frame.add(cardIdField);

        JLabel floorLsbel = new JLabel("Floor:");
        floorLsbel.setBounds(10,50,80,25);
        frame.add(floorLsbel);

        floorField = new JTextField();
        floorField.setBounds(100,50,160,25);
        frame.add(floorField);

        JLabel roomLabel = new JLabel("Room:");
        roomLabel.setBounds(10,90,80,25);
        frame.add(roomLabel);

        roomField = new JTextField();
        roomField.setBounds(100,90,160,25);
        frame.add(roomField);

        JButton checkButton = new JButton("Check Access");
        checkButton.setBounds(100,130,160,25);
        checkButton.addActionListener(e -> checkAccess());
        frame.add(checkButton);

        frame.setSize(300, 200);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
