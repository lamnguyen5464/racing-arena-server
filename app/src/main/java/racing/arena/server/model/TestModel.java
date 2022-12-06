package racing.arena.server.model;

import org.json.JSONObject;

public class TestModel extends JSONObject {
    private final String a;
    private final int b;
    public TestModel(String a, int b) {
        this.a = a;
        this.b = b;

    }
}
