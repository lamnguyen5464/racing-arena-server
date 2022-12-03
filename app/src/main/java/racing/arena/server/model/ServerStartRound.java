package racing.arena.server.model;

import org.json.JSONArray;
import racing.arena.server.core.utils.JSONSafeObject;
import racing.arena.server.feature.room.ClientInRoom;

import java.util.List;

public class ServerStartRound implements BaseMessage {
    private String question;
    private int round;
    private List<ClientInRoom> listRankedUsers;

    public ServerStartRound(String question, int round, List<ClientInRoom> listRankedUsers) {
        this.question = question;
        this.round = round;
        this.listRankedUsers = listRankedUsers;
    }

    @Override
    public String getType() {
        return MessageType.SERVER_START_ROUND.name();
    }

    @Override
    public void initFromObject(JSONSafeObject object) {

    }

    @Override
    public JSONSafeObject toJsonObject() {
        JSONSafeObject object = new JSONSafeObject();
        object.put("round", this.round);
        object.put("question", question);

        JSONArray jsonListRankedUsers = new JSONArray();

        for (ClientInRoom user : this.listRankedUsers) {
            JSONSafeObject jsonUser = new JSONSafeObject();
            jsonUser.put("username", user.getClient().getUsername());
            jsonUser.put("id", user.getClient().getId());
            jsonUser.put("score", user.getScore());

            jsonListRankedUsers.put(jsonUser);
        }
        object.put("listRankedUser", jsonListRankedUsers);

        return object;
    }
}
