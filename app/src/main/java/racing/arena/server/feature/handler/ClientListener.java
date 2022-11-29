package racing.arena.server.feature.handler;

import racing.arena.server.core.Providers;
import racing.arena.server.feature.client.Client;
import racing.arena.server.model.ClientJoinRoom;
import racing.arena.server.model.MessageType;
import racing.arena.server.model.PayloadWrapper;
import racing.arena.server.core.utils.Logger;

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
            ClientJoinRoom clientJoinRoomData = new ClientJoinRoom();
            // parse
            clientJoinRoomData.initFromObject(payloadWrapper.getPayload());

            // set username for client
            client.setUsername(clientJoinRoomData.getUsername());

            // add client to appropriate room
            Providers.defaultRoom.addClient(client);

            Logger.d("[SERVER] " + clientJoinRoomData.getUsername() + " is here!");
            return;
        }

        if (type.equals(MessageType.CLIENT_ANSWER.name())) {
            //
            return;
        }

    }
}
