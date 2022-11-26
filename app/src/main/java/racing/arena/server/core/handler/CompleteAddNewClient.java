package racing.arena.server.core.handler;

import racing.arena.server.core.Client;
import racing.arena.server.model.PayloadWrapper;
import racing.arena.server.model.ServerNewUser;

import java.io.IOException;

public class CompleteAddNewClient implements ClientHandler {
    @Override
    public void handle(Client client) {
        ServerNewUser payload = new ServerNewUser(client.getId());
        PayloadWrapper payloadWrapper = new PayloadWrapper(payload);
        try {
            client.send(payloadWrapper.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
