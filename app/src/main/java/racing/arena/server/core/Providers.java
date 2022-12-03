package racing.arena.server.core;

import racing.arena.server.feature.client.ClientManager;
import racing.arena.server.feature.handler.ClientHandler;
import racing.arena.server.feature.handler.ClientListener;
import racing.arena.server.feature.handler.CompleteAddNewClient;
import racing.arena.server.core.server.SocketServer;
import racing.arena.server.core.utils.Configs;
import racing.arena.server.feature.room.Room;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Providers {
    public static ExecutorService baseExecutorService = Executors.newFixedThreadPool(4);
    public static ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
    public static ExecutorService clientExecutorService = Executors.newFixedThreadPool(Configs.MAXIMUM_USERS);
    public static ClientManager clientManager = new ClientManager(clientExecutorService);
    public static Room defaultRoom = new Room(new ArrayList<>(), singleExecutorService);
    public static SocketServer socketServer = new SocketServer(baseExecutorService, clientManager);

    public static ClientHandler clientListener = new ClientListener();

    public static ClientHandler completeAddNewClient = new CompleteAddNewClient();

    private Providers() {
    }
}
