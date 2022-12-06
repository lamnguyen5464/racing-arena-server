package racing.arena.server.core.server;

import racing.arena.server.feature.client.Client;
import racing.arena.server.feature.client.ClientManager;
import racing.arena.server.core.utils.Configs;
import racing.arena.server.core.utils.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class SocketServer {
    private final ExecutorService executorService;
    private final ClientManager clientManager;
    private ServerSocket socket;

    public SocketServer(ExecutorService executorService, ClientManager clientManager) {
        this.executorService = executorService;
        this.clientManager = clientManager;
    }

    public void start() {
        try {
            socket = new ServerSocket(Configs.PORT);
            executorService.submit(this::acceptNewClient);
            Logger.d("Init socket server successfully");

        } catch (Exception exception) {
            Logger.d("Exception: " + exception.getMessage());
        }
    }

    private void acceptNewClient() {
        try {
            while (true) {
                Logger.d("[SERVER] Waiting for new client...");
                Socket clientSocket = socket.accept();
                Logger.d("[SERVER] New client is coming to town!");

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())
                );

                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

                Client client = new Client(bufferedReader, outputStream);
                this.clientManager.add(client);
            }

        } catch (Exception e) {
            Logger.d("Exception: " + e.getMessage());
        }
    }


}
