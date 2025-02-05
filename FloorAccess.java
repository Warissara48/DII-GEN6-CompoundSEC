class FloorAccess extends AccessControl{
    @Override
    public  boolean checkAccess(String floor,String room) {
        if (getAccessCard().getAccessibleFloors().contains(floor)) {
            logAccessAttempt(floor, room, true);
            return true;
        }
        logAccessAttempt(floor, room, false);
        return false;
    }
}

class RoomAccess extends AccessControl{
    @Override
    public boolean checkAccess(String floor,String room) {
        if (getAccessCard().getAccessibleRooms().contains(room)) {
            logAccessAttempt(floor, room, true);
            return true;
        }
        logAccessAttempt(floor, room, false);
        return false;
    }
}
