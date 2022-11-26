package racing.arena.server;

import racing.arena.server.utils.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class ClientManager {
    private final Map<String, Client> clients = new HashMap<>();

    private final ExecutorService executorService;

    ClientManager(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void add(Client client) {
        clients.put(client.getId(), client);
        this.executorService.submit(() -> listenClient(client));
    }

    private void listenClient(Client client) {
        try {
            String dataFromClient;
            while ((dataFromClient = client.retrieve()) != null) {
                Logger.d("[SERVER] From client: " + client.getId() + ": " + dataFromClient);
                // TODO: handle client actions here
            }

        } catch (Exception e) {
            Logger.d("Exception: " + e.getMessage());
        }
    }
}
