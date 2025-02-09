import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccessUtils {
    public static void checkAccess(String cardId, String floor, String room, AccessCard[] accessCards) {
        AccessCard selectedCard = null;
        for (AccessCard card : accessCards) {
            if (card.getCardId().equals(cardId)) {
                selectedCard = card;
                break;
            }
        }
        if (selectedCard != null) {
            AccessControl accessControl = new FloorAccess();
            accessControl.setAccessCard(selectedCard);
            boolean accessGranted = accessControl.checkAccess(floor, room);

            String result = "Access " + (accessGranted ? "Granted" : "Denied");
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            JOptionPane.showMessageDialog(null, "Date and Time: " + dateTime + "\n" + result);
        } else {
            JOptionPane.showMessageDialog(null, "Card ID not found!");
        }
    }
}
