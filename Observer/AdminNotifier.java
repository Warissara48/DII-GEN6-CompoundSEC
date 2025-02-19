package Observer;
import javax.swing.*;
import java.awt.*;

public class AdminNotifier implements AccessObserver {
    @Override
    public void update(String message) {
        // สร้างหน้าต่างใหม่เพื่อแสดงการแจ้งเตือน
        JFrame alertFrame = new JFrame("Access Alert");
        alertFrame.setSize(400, 150);
        alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Admin Alert: " + message, SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> alertFrame.dispose()); // ปิดหน้าต่างเมื่อกดปุ่ม OK
        panel.add(okButton, BorderLayout.SOUTH);

        alertFrame.add(panel);
        alertFrame.setLocationRelativeTo(null); // ให้หน้าต่างแสดงตรงกลางของจอ
        alertFrame.setVisible(true);
    }
}
