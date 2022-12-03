package racing.arena.server.model;

import racing.arena.server.core.utils.JSONSafeObject;

import java.util.List;

public class ServerAllowJoinRoom implements BaseMessage {
    static private final String FIELD_LIST_USERS = "listUsers";
    static private final String FIELD_ROOM = "room";

    public ServerAllowJoinRoom(String room, List<String> listUsers) {
        this.room = room;
        this.listUsers = listUsers;
    }

    private final String room;
    private final List<String> listUsers;

    @Override
    public String getType() {
        return MessageType.SERVER_ALLOW_JOIN_ROOM.name();
    }

    @Override
    public void initFromObject(JSONSafeObject object) {
        // TODO: needn't now
    }

    @Override
    public JSONSafeObject toJsonObject() {
        JSONSafeObject object = new JSONSafeObject();
        object.put(FIELD_ROOM, this.room);
        object.put(FIELD_LIST_USERS, this.listUsers);
        return object;
    }
}
