package racing.arena.server.feature.handler;

import racing.arena.server.core.Providers;
import racing.arena.server.feature.client.Client;
import racing.arena.server.feature.room.Room;
import racing.arena.server.model.*;
import racing.arena.server.core.utils.Logger;

import java.io.IOException;

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
            handleClientJoinRoom(client, payloadWrapper);
            return;
        }

        if (type.equals(MessageType.CLIENT_ANSWER.name())) {
            handleClientAnswer(client, payloadWrapper);
            return;
        }

    }

    private void handleClientAnswer(Client client, PayloadWrapper payloadWrapper) {
        ClientAnswer clientAnswer = new ClientAnswer();
        clientAnswer.initFromObject(payloadWrapper.getPayload());

        Room currentRoom = Providers.defaultRoom;

        currentRoom.submitAnswer(client, clientAnswer.getRound(), clientAnswer.getAnswer());

    }

    private void handleClientJoinRoom(Client client, PayloadWrapper payloadWrapper) {
        ClientJoinRoom clientJoinRoomData = new ClientJoinRoom();
        // parse
        clientJoinRoomData.initFromObject(payloadWrapper.getPayload());
        Logger.d("[SERVER] " + clientJoinRoomData.getUsername() + " is here!");

        // set username for client
        client.setUsername(clientJoinRoomData.getUsername());

        // get room
        Room currentRoom = Providers.defaultRoom;

        // check existed
        if (currentRoom.isExisted(client)) {
            return;
        }

        // add client to appropriate room
        currentRoom.addClient(client);

        try {
            client.send(
                    new PayloadWrapper(
                            new ServerAllowJoinRoom(
                                    currentRoom.getId(),
                                    currentRoom.getListUsernames()
                            )
                    ).toString()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
