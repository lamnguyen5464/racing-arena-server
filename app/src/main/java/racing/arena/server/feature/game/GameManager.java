package racing.arena.server.feature.game;

import racing.arena.server.core.utils.Configs;
import racing.arena.server.core.utils.Logger;
import racing.arena.server.feature.client.Client;
import racing.arena.server.feature.room.ClientInRoom;
import racing.arena.server.model.PayloadWrapper;
import racing.arena.server.model.ServerStartRound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class GameManager {
    private final ExecutorService executor;
    private final List<ClientInRoom> clientsInRoom;
    private int currentRound = 0;
    private String currentExpectedAnswer = "";
    private String currentQuestion = "";

    public GameManager(List<Client> clients, ExecutorService executor) {
        this.executor = executor;
        List<ClientInRoom> clientsInRoom = new ArrayList<>(clients.size());
        for (Client client : clients) {
            clientsInRoom.add(new ClientInRoom(client, Configs.DEFAULT_INIT_SCORE));
        }
        this.clientsInRoom = clientsInRoom;
    }

    public List<ClientInRoom> getClientsInRoom() {
        return clientsInRoom;
    }

    public synchronized void addClient(Client client) {
        if (clientsInRoom.size() == Configs.MAXIMUM_USERS) {
            // TODO: reject user
            return;
        }
        clientsInRoom.add(new ClientInRoom(client, Configs.DEFAULT_INIT_SCORE));
        Logger.d("[GAME] add client | size: " + clientsInRoom.size());
        if (clientsInRoom.size() == Configs.MAXIMUM_USERS) {
            executor.submit(this::startGame);
        }
    }

    private void startGame() {
        Logger.d("[GAME] --- START ----");
        startRound(1);
    }

    private void startRound(int round) {
        try {
            Logger.d("[GAME] start round: " + round);
            if (round > Configs.MAXIMUM_ROUNDS) {
                return;
            }
            this.currentRound = round;

            notifyStartRound(round, generateQuestion());

            Thread.sleep(Configs.ROUND_TIME_IN_SECOND * 1000L);
            startRound(round + 1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateQuestion() {
        QuestionUtils.Question question = QuestionUtils.createQuestion();
        this.currentExpectedAnswer = question.answer();
        this.currentQuestion = question.question();
        return this.currentQuestion;
    }

    private void notifyStartRound(int round, String question) {
        ServerStartRound serverStartRound = new ServerStartRound(question, this.currentExpectedAnswer, round, clientsInRoom);
        String payload = new PayloadWrapper(serverStartRound).toString();
        for (ClientInRoom clientInRoom : clientsInRoom) {
            try {
                clientInRoom.getClient().send(payload);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public synchronized void submitAnswer(Client client, int round, String answer) {
        if (round != currentRound) {
            return;
        }

        if (!answer.equals(this.currentExpectedAnswer)) {
            return;
        }

        for (ClientInRoom clientInRoom : clientsInRoom) {
            if (client.getId().equals(clientInRoom.getClient().getId())) {
                clientInRoom.modifyScore(1);
                break;
            }
        }
        // re notify
        this.notifyStartRound(currentRound, currentQuestion);
    }
}
