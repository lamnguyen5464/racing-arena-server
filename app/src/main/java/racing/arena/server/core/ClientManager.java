package racing.arena.server.core;

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
        this.clients.put(client.getId(), client);
        this.executorService.submit(() -> Providers.completeAddNewClient.handle(client));
        this.executorService.submit(() -> Providers.clientListener.handle(client));
    }
}
