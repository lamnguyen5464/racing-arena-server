package racing.arena.server.feature.room;

import racing.arena.server.core.utils.Configs;
import racing.arena.server.feature.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room {
    private final String id;
    private final List<ClientInRoom> clients;

    private class ClientInRoom {
        public Client getClient() {
            return client;
        }

        public int getScore() {
            return score;
        }

        private final Client client;
        private final int score;

        public ClientInRoom(Client client, int score) {
            this.client = client;
            this.score = score;
        }
    }

    public Room(List<Client> clients) {
        this.id = "room-id-" + UUID.randomUUID();
        List<ClientInRoom> clientsInRoom = new ArrayList<>();
        for (Client client : clients) {
            clientsInRoom.add(new ClientInRoom(client, Configs.DEFAULT_INIT_SCORE));
        }
        this.clients = clientsInRoom;
    }

    public String getId() {
        return id;
    }

    public void addClient(Client client) {
        clients.add(new ClientInRoom(client, Configs.DEFAULT_INIT_SCORE));
    }

}
