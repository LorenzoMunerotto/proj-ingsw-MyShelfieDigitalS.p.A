package it.polimi.ingsw.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer implements Runnable {


    private final Integer port;
    private final Server server;
    private final ExecutorService executorService;
    private boolean active;

    public SocketServer(Integer port, Server server) {
        this.port = port;
        this.server = server;
        this.active=true;
        this.executorService = Executors.newCachedThreadPool();
    }

    public void acceptConnection(ServerSocket serverSocket){
        while(active){
            try{
                SocketClientConnection socketClient =
                        new SocketClientConnection(serverSocket.accept(), server);
                System.out.println("new client try to connect");
                executorService.submit(socketClient);
            }
            catch(IOException e){
                System.err.println("Error! " + e.getMessage());
            }
        }
    }

    @Override
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("make server socket");
            acceptConnection(serverSocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

