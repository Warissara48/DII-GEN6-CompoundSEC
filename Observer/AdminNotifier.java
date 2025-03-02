package Observer;
import javax.swing.*;
import java.awt.*;

public class AdminNotifier implements AccessObserver {
    @Override
    public void update(String message) {
        JFrame alertFrame = new JFrame("Access Alert");
        alertFrame.setSize(400, 150);
        alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Admin Alert: " + message, SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> alertFrame.dispose());
        panel.add(okButton, BorderLayout.SOUTH);

        alertFrame.add(panel);
        alertFrame.setLocationRelativeTo(null);
        alertFrame.setVisible(true);
    }
}
