import Observer.AccessMonitor;
import Observer.AdminNotifier;
import Strategy.EverydayAccessStrategy;
import Strategy.WeekdayAccessStrategy;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessControlSystem { //Class Visibility ที่กำหนดเป็น public ให้สามารถเข้าถึงได้จากทุกที่
    private static Card[] users = {  //upcasting
            new UserCard("Bom", "672110244"),
            new AdminCard("Lis", "ad123"),
            new EmployeeCard("Vee", "em123")
    };

    private static AccessMonitor accessMonitor = new AccessMonitor();

    static {
        accessMonitor.addObserver(new AdminNotifier());

        for (Card user : users) {
            if (user instanceof UserCard) {
                ((UserCard) user).setAccessStrategy(new WeekdayAccessStrategy());
            } else if (user instanceof EmployeeCard) {
                ((EmployeeCard) user).setAccessStrategy(new EverydayAccessStrategy());
            }
        }
    }

    public static void login() {
        JFrame frame = new JFrame("Access Control System");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                for (Card user : users) {
                    if (user.getUsername().equals(username) && user.authenticate(password)) {
                        accessMonitor.notifyObservers(username + " logged in successfully.");
                        if (user instanceof UserCard) {
                            if (!((UserCard) user).requestAccess()) {
                                JOptionPane.showMessageDialog(null, "Access Denied (Weekdays Only)!");
                                return;
                            }
                        } else if (user instanceof EmployeeCard) {
                            if (!((EmployeeCard) user).requestAccess()) {
                                JOptionPane.showMessageDialog(null, "Access Denied!");
                                return;
                            }
                        }
                        user.accessSystem(); // Dynamic Late Binding
                        user.logAccess("Logged in"); // Dynamic Late Binding ( JVM จะเลือกเมธอด logAccess() ตามประเภทของ user ตอน Runtime )
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Invalid credentials!");
                accessMonitor.notifyObservers("Failed login attempt for username: " + username);
            }
        });
    }
}