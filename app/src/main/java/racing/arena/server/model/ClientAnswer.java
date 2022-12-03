package racing.arena.server.model;

import racing.arena.server.core.utils.JSONSafeObject;

public class ClientAnswer implements BaseMessage{
    private String answer;
    private int round;

    @Override
    public String getType() {
        return MessageType.CLIENT_ANSWER.name();
    }

    @Override
    public void initFromObject(JSONSafeObject object) {
        this.round = object.getInt("round");
        this.answer = object.getString("answer");
    }

    @Override
    public JSONSafeObject toJsonObject() {
        return null;
    }

    public String getAnswer() {
        return answer;
    }

    public int getRound() {
        return round;
    }

}
