package racing.arena.server.feature.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class Client {
    private final BufferedReader bufferedReader;
    private final DataOutputStream dataOutputStream;
    private final String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username = "";

    public Client(BufferedReader bufferedReader, DataOutputStream outputStream) {
        this.bufferedReader = bufferedReader;
        this.dataOutputStream = outputStream;
        this.id = "client-id-" + UUID.randomUUID();
//        Logger.d("[Client] create client with id: " + this.id);
    }

    public String getId() {
        return id;
    }

    public void send(String message) throws IOException {
        this.dataOutputStream.writeBytes(message);
    }

    public String retrieve() throws IOException {
        return this.bufferedReader.readLine();
    }

}
