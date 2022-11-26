package racing.arena.server.model;

import racing.arena.server.utils.JSONSafeObject;

public class ServerNewUser implements BaseMessage {
    static private String FIELD_ID = "id";
    private String id;

    public ServerNewUser(String id) {
        this.id = id;
    }

    public ServerNewUser() {
    }

    @Override
    public String getType() {
        return MessageType.SERVER_NEW_USER.name();
    }

    @Override
    public void initFromObject(JSONSafeObject object) {
        this.id = object.getString(FIELD_ID);
    }

    @Override
    public JSONSafeObject toJsonObject() {
        JSONSafeObject object = new JSONSafeObject();
        object.put(FIELD_ID, this.id);
        return object;
    }
}
