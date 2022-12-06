package racing.arena.server.feature.room;

import racing.arena.server.core.utils.Configs;
import racing.arena.server.feature.client.Client;
import racing.arena.server.feature.game.GameManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Room {
    private final String id;
    private final GameManager gameManager;

    public Room(List<Client> clients, ExecutorService executor) {
        this.id = "room-id-" + UUID.randomUUID();
        this.gameManager = new GameManager(clients, executor);
    }

    public String getId() {
        return id;
    }

    public List<String> getListUsernames() {
        List<ClientInRoom> clientsInRoom = this.gameManager.getClientsInRoom();
        List<String> usernames = new ArrayList<>(clientsInRoom.size());
        for (ClientInRoom client : clientsInRoom) {
            usernames.add(client.getClient().getUsername());
        }
        return usernames;
    }

    public List<ClientInRoom> getListClientsInRoom() {
        return this.gameManager.getClientsInRoom();
    }

    public boolean isExisted(Client client) {
        for (ClientInRoom clientInRoom : this.gameManager.getClientsInRoom()) {
            if (clientInRoom.getClient().equals(client)) { // object reference equal
                return true;
            }
        }
        return false;
    }

    public void addClient(Client client) {
        this.gameManager.addClient(client);
    }

    public void submitAnswer(Client client, int round, String answer) {
        this.gameManager.submitAnswer(client, round, answer);
    }

}
