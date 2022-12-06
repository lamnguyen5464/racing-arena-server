package racing.arena.server.model;

import racing.arena.server.core.utils.JSONSafeObject;

public class ClientJoinRoom implements BaseMessage{
    static private final String FIELD_USER_NAME = "username";

    public String getUsername() {
        return username;
    }

    private String username;
    public ClientJoinRoom(String username) {
        this.username = username;
    }

    public ClientJoinRoom() {}
    @Override
    public String getType() {
        return MessageType.CLIENT_JOIN_ROOM.name();
    }

    @Override
    public void initFromObject(JSONSafeObject object) {
        this.username = object.getString(FIELD_USER_NAME);
    }

    @Override
    public JSONSafeObject toJsonObject() {
        JSONSafeObject object = new JSONSafeObject();
        object.put(FIELD_USER_NAME, this.username);
        return object;
    }
}
