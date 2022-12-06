package racing.arena.server.feature.room;

public class RoomManager {
    private Room defaultRoom;

    public Room getDefaultRoom() {
        return defaultRoom;
    }

    public RoomManager(Room defaultRoom) {
        this.defaultRoom = defaultRoom;
    }

}
