package racing.arena.server;

import racing.arena.server.utils.Configs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Providers {
    public static ExecutorService baseExecutorService = Executors.newFixedThreadPool(4);
    public static ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
    public static ExecutorService clientExecutorService = Executors.newFixedThreadPool(Configs.MAXIMUM_USERS);
    public static ClientManager clientManager  = new ClientManager(clientExecutorService);
    public static SocketServer socketServer = new SocketServer(baseExecutorService, clientManager);

    private Providers() {
    }
}
