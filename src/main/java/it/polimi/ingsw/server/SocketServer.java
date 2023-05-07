package it.polimi.ingsw.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represents the socket server.
 */
public class SocketServer implements Runnable {

    /**
     * The port of the server.
     */
    private final Integer port;
    /**
     * The server.
     */
    private final Server server;
    /**
     * The executor service.
     */
    private final ExecutorService executorService;
    /**
     * The boolean that indicates if the server is active.
     */
    private boolean active;

    /**
     * This is the constructor of the class.
     *
     * @param port the port of the server
     * @param server the server
     */
    public SocketServer(Integer port, Server server) {
        this.port = port;
        this.server = server;
        setActive(true);
        this.executorService = Executors.newCachedThreadPool();
    }

    /**
     * This method sets the boolean that indicates if the server is active.
     *
     * @param active the boolean that indicates if the server is active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * This method returns the boolean that indicates if the server is active.
     *
     * @return the boolean that indicates if the server is active
     */
    private boolean isActive() {
        return active;
    }

    /**
     * This method accepts the connection of the client.
     *
     * @param serverSocket the server socket
     */
    public void acceptConnection(ServerSocket serverSocket) {
        while (isActive()) {
            try {
                SocketClientConnection socketClient =
                        new SocketClientConnection(serverSocket.accept(), server);
                System.out.println("A new client is trying to connect...");
                executorService.submit(socketClient);
            } catch (IOException e) {
                System.err.println("Error! " + e.getMessage());
            }
        }
    }

    /**
     * This method creates a new server socket.
     *
     * @return the server socket
     * @throws IOException if an I/O error occurs when opening the socket
     */
    private ServerSocket createServerSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server socket created");
        return serverSocket;
    }

    /**
     * This method runs the server.
     */
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = createServerSocket();
            acceptConnection(serverSocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

