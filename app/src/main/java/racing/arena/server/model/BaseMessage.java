package racing.arena.server.model;

import racing.arena.server.utils.JSONSafeObject;

public interface BaseMessage {
    String getType();
    void initFromObject(JSONSafeObject object);
    JSONSafeObject toJsonObject();
}
