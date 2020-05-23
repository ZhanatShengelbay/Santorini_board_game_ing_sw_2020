package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.SetUpController;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.GameStart;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT= 12345;
    private ServerSocket serverSocket;

    private ExecutorService executor = Executors.newFixedThreadPool(10);

    private List<Connection> connections = new ArrayList<>();
    private HashMap<Connection, Integer> waitingConnections = new HashMap<>();
    private List<List<Connection>> playingConnections = new ArrayList<>();

    //Register connection
    private synchronized void registerConnection(Connection c){
        connections.add(c);
    }

    //Deregister connection
    public synchronized void deregisterConnection(Connection c){
        connections.remove(c);
    }

    public synchronized void createGame(List<Connection> connectionList){
        Connection c1 = connectionList.get(0);
        Connection c2 = connectionList.get(1);
        List<String> players = new ArrayList<>();
        players.add(c1.getID());
        players.add(c2.getID());
        Model model = new Model();
        RemoteView player1view = new RemoteView(c1, model);
        RemoteView player2view = new RemoteView(c2, model);
        SetUpController controller = new SetUpController(model, players);
        model.addObserver(player1view);
        model.addObserver(player2view);
        player1view.addObserver(controller);
        player2view.addObserver(controller);

        if(connectionList.size()==3) {
            Connection c3 = connectionList.get(2);
            RemoteView player3view = new RemoteView(c3, model);
            model.addObserver(player3view);
            player3view.addObserver(controller);
            controller.addPlayer(c3.getID());
        }
    }

    public synchronized void lobby(Connection c) throws Error{
        int j = 0, k = 0;
        waitingConnections.put(c, c.getNumOfPlayers());
        for (Map.Entry<Connection, Integer> entry : waitingConnections.entrySet()) {
            if(entry.getValue() == 2){
                j++;
                if(j == 2){
                    List<Connection> tmp = new ArrayList<>();
                    for (Map.Entry<Connection, Integer> hash : waitingConnections.entrySet()){
                        if(hash.getValue() == 2){
                            tmp.add(hash.getKey());
                        }
                    }
                    playingConnections.add(tmp);
                    createGame(tmp);
                    for(Connection connection : tmp){
                        waitingConnections.remove(connection);
                    }
                }
            }
            if(entry.getValue() == 3){
                k++;
                if(k == 3){
                    List<Connection> tmp = new ArrayList<>();
                    for (Map.Entry<Connection, Integer> hash : waitingConnections.entrySet()){
                        if(hash.getValue() == 3){
                            tmp.add(hash.getKey());
                        }
                    }
                    playingConnections.add(tmp);
                    createGame(tmp);
                    for(Connection connection : tmp){
                        waitingConnections.remove(connection);
                    }
                }
            }
        }
    }

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public void run(){
        System.out.println("Server listening on port: " + PORT);
        while(true){
            try {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket, this);
                registerConnection(connection);
                executor.submit(connection);
            } catch (IOException e){
                System.err.println("Connection error!");
            }
        }
    }

}
