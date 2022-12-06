package racing.arena.server.core.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;


public class JSONSafeObject extends JSONObject {

    public static JSONSafeObject EMPTY = new JSONSafeObject();

    public JSONSafeObject(String rawData) throws JSONException {
        super(rawData);
    }

    public JSONSafeObject() {
        super();
    }

    @Override
    public int getInt(String s) {
        try {
            return super.getInt(s);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean getBoolean(String s) {
        try {
            return super.getBoolean(s);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public JSONObject put(String s, Collection collection) {
        try {
            return super.put(s, collection);
        } catch (JSONException e) {
            return this;
        }
    }

    @Override
    public JSONObject put(String s, Object o) {
        try {
            return super.put(s, o);
        } catch (JSONException e) {
            return this;
        }
    }

    @Override
    public JSONObject put(String s, int o) {
        try {
            return super.put(s, o);
        } catch (JSONException e) {
            return this;
        }
    }

    @Override
    public String getString(String s) {
        try {
            return super.getString(s);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public JSONObject getJSONObject(String s) {
        try {
            return super.getJSONObject(s);
        } catch (JSONException e) {
            return JSONSafeObject.EMPTY;
        }
    }
}
