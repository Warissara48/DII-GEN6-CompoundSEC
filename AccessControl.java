import java.text.SimpleDateFormat;
import java.util.Date;

abstract class AccessControl {
    private AccessCard accessCard;

    public  abstract  boolean checkAccess(String floor,String room);

    public AccessCard getAccessCard(){
        return accessCard;
    }

    public void setAccessCard(AccessCard accessCard){
        this.accessCard = accessCard;
    }

    public void logAccessAttempt(String floor,String room,boolean accessGranted){
        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("Date and Time: " + dateTime);
        System.out.println("Card ID: " + accessCard.getCardId() + " Access Attempt to Floor: " + floor + ", Room: " + room);
        System.out.println("Access " + (accessGranted ? "Granted" : "Denied"));
    }
}
