package racing.arena.server.core.handler;

import racing.arena.server.core.Client;
import racing.arena.server.model.ClientJoinRoom;
import racing.arena.server.model.MessageType;
import racing.arena.server.model.PayloadWrapper;
import racing.arena.server.utils.Logger;

public class ClientListener implements ClientHandler {
    @Override
    public void handle(Client client) {
        try {
            String dataFromClient;
            while ((dataFromClient = client.retrieve()) != null) {
                Logger.d("[SERVER] From client: " + client.getId() + ": " + dataFromClient);
                doAction(client, dataFromClient);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAction(Client client, String rawData) {
        PayloadWrapper payloadWrapper = new PayloadWrapper(rawData);
        String type = payloadWrapper.getType();
        if (type.equals(MessageType.CLIENT_JOIN_ROOM.name())) {
            ClientJoinRoom clientJoinRoom = new ClientJoinRoom();
            clientJoinRoom.initFromObject(payloadWrapper.getPayload());
            Logger.d("[SERVER] " + clientJoinRoom.getUsername() + " is here!");
            return;
        }

    }
}
