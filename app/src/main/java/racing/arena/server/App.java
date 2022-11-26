package racing.arena.server;

import racing.arena.server.core.Providers;
import racing.arena.server.model.ClientJoinRoom;
import racing.arena.server.model.PayloadWrapper;
import racing.arena.server.model.TestModel;
import racing.arena.server.utils.Configs;
import racing.arena.server.utils.Logger;

import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.net.Socket;


public class App {

    public static void main(String[] args) {
        Logger.d("start server...");
        Providers.socketServer.start();

        //test
        Providers.baseExecutorService.submit(() -> startClient(1));
        Providers.baseExecutorService.submit(() -> startClient(2));

    }

    private static void startClient(int num) {
        try {

            System.out.println("Start client " + num + "...");
            String sentence_to_server = "From client " + num;

            Socket clientSocket = new Socket("localhost", Configs.PORT);

            DataOutputStream outToServer =
                    new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer =
                    new BufferedReader(new
                            InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(sentence_to_server + '\n');

            String sentence_from_server = inFromServer.readLine();

            while (sentence_from_server != null) {

                System.out.println("[CLIENT] FROM SERVER: " + sentence_from_server);


                ClientJoinRoom payload = new ClientJoinRoom(sentence_to_server);
                outToServer.writeBytes(new PayloadWrapper(payload).toString());
                sentence_from_server = inFromServer.readLine();
            }

//                    clientSocket.close();
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        }


    }
}
