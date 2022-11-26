package racing.arena.server.model;

import org.json.JSONException;
import org.json.JSONObject;
import racing.arena.server.utils.JSONSafeObject;

public class PayloadWrapper {
    private static String FIELD_TYPE = "type";
    private static String FIELD_PAYLOAD = "payload";

    private JSONSafeObject data;

    public PayloadWrapper(BaseMessage message) {
        this.data = new JSONSafeObject();
        this.data.put(FIELD_TYPE, message.getType());
        this.data.put(FIELD_PAYLOAD, message.toJsonObject());
    }

    public PayloadWrapper(String rawData) {
        try {
            data = new JSONSafeObject(rawData);
        } catch (JSONException e) {
            data = JSONSafeObject.EMPTY;
        }
    }

    public String getType() {
        return this.data.getString(FIELD_TYPE);
    }

    public JSONSafeObject getPayload() {
        JSONObject obj = this.data.getJSONObject(FIELD_PAYLOAD);
        try {
            return new JSONSafeObject(obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return JSONSafeObject.EMPTY;
        }
    }

    @Override
    public String toString() {
        return this.data.toString() + "\n";
    }
}
