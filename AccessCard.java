import java.util.List;

public class AccessCard {
    private String cardId;
    private List<String> accessFloors;
    private List<String> accessRooms;

    public  AccessCard(String cardId,List<String> accessFloors,List<String> accessRooms){
        this.cardId = cardId;
        this.accessFloors = accessFloors;
        this.accessRooms = accessRooms;
    }

    public String getCardId() {
        return cardId;
    }

    public List<String> getAccessibleFloors() {
        return accessFloors;
    }

    public List<String> getAccessibleRooms() {
        return accessRooms;
    }
}
