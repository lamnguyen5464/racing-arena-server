package racing.arena.server.feature.room;

import racing.arena.server.feature.client.Client;

public class ClientInRoom {
    public Client getClient() {
        return client;
    }

    public int getScore() {
        return score;
    }

    private final Client client;
    private int score;

    public ClientInRoom(Client client, int score) {
        this.client = client;
        this.score = score;
    }

    public int modifyScore(int delta) {
        score += delta;
        if (score < 0) score = 0;
        return score;
    }

}
