package racing.arena.server;

import racing.arena.server.core.Providers;
import racing.arena.server.model.ClientJoinRoom;
import racing.arena.server.model.PayloadWrapper;
import racing.arena.server.core.utils.Logger;

import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.net.Socket;


public class App {

    public static void main(String[] args) {
        Logger.d("start server...");
        Providers.socketServer.start();

        //test
        // Providers.baseExecutorService.submit(() -> startClient(1));
        // Providers.baseExecutorService.submit(() -> startClient(2));
        // Providers.baseExecutorService.submit(() -> startClient(3));

    }

    private static void startClient(int num) {
        try {

            System.out.println("Start client " + num + "...");
            String sentence_to_server = "Hi, I am client: " + num;

            Socket clientSocket = new Socket("localhost", 5555);

            DataOutputStream outToServer =
                    new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer =
                    new BufferedReader(new
                            InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(sentence_to_server + '\n');

            String sentence_from_server = inFromServer.readLine();


            ClientJoinRoom payload = new ClientJoinRoom("client-" + num);
            outToServer.writeBytes(new PayloadWrapper(payload).toString());

            while (sentence_from_server != null) {

                System.out.println("[CLIENT " + num + "] FROM SERVER: " + sentence_from_server);
                sentence_from_server = inFromServer.readLine();
            }

//                    clientSocket.close();
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        }


    }
}
