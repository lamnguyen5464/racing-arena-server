package racing.arena.server.core;

import racing.arena.server.core.handler.ClientHandler;
import racing.arena.server.core.handler.ClientListener;
import racing.arena.server.core.handler.CompleteAddNewClient;
import racing.arena.server.utils.Configs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Providers {
    public static ExecutorService baseExecutorService = Executors.newFixedThreadPool(4);
    public static ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
    public static ExecutorService clientExecutorService = Executors.newFixedThreadPool(Configs.MAXIMUM_USERS);
    public static ClientManager clientManager = new ClientManager(clientExecutorService);
    public static SocketServer socketServer = new SocketServer(baseExecutorService, clientManager);

    public static ClientHandler clientListener = new ClientListener();

    public static ClientHandler completeAddNewClient = new CompleteAddNewClient();

    private Providers() {
    }
}
