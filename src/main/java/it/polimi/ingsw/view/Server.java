package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.SetUpController;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Grid;
import it.polimi.ingsw.model.State.Init;
import it.polimi.ingsw.model.State.SelectGods;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT= 12345;
    private ServerSocket serverSocket;

    private int numOfPlayers;

    private ExecutorService executor = Executors.newFixedThreadPool(3);

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
        if(connectionList.size()==2){
            Connection c1 = connectionList.get(0);
            Connection c2 = connectionList.get(1);
            List<String> players = new ArrayList<>();
            players.add(c1.getID());
            players.add(c2.getID());
            RemoteView player1view = new RemoteView(c1);
            RemoteView player2view = new RemoteView(c2);
            List<RemoteView> views = new ArrayList<>();
            views.add(player1view);
            views.add(player2view);
            Grid grid = new Grid();
            Model model = new Model(grid);
            SetUpController controller = new SetUpController(model, players, views);
            model.addObserver(player1view);
            model.addObserver(player2view);
            player1view.addObserver(controller);
            player2view.addObserver(controller);
            model.setCurrentState(new Init());
        }
        else if(connectionList.size()==3){
            Connection c1 = connectionList.get(0);
            Connection c2 = connectionList.get(1);
            Connection c3 = connectionList.get(2);
            List<String> players = new ArrayList<>();
            players.add(c1.getID());
            players.add(c2.getID());
            players.add(c3.getID());
            RemoteView player1view = new RemoteView(c1);
            RemoteView player2view = new RemoteView(c2);
            RemoteView player3view = new RemoteView(c3);
            List<RemoteView> views = new ArrayList<>();
            views.add(player1view);
            views.add(player2view);
            views.add(player3view);
            Grid grid = new Grid();
            Model model = new Model(grid);
            SetUpController controller = new SetUpController(model, players, views);
            model.addObserver(player1view);
            model.addObserver(player2view);
            model.addObserver(player3view);
            player1view.addObserver(controller);
            player2view.addObserver(controller);
            player3view.addObserver(controller);
            model.setCurrentState(new Init());
        }
    }

    public synchronized void lobby(Connection c){
        waitingConnections.put(c, c.getNumOfPlayers());
        int i = 0, k = 0;
        List<Connection> tmpConnections2 = new ArrayList<>();
        List<Connection> tmpConnections3 = new ArrayList<>();
        for (Map.Entry<Connection, Integer> entry : waitingConnections.entrySet()) {
            if(entry.getValue() == 2){
                i++;
                tmpConnections2.add(entry.getKey());
                if (i==2){
                    playingConnections.add(tmpConnections2);
                    for(int j=0; j < tmpConnections2.size(); j++){
                        waitingConnections.remove(tmpConnections2.get(j));
                        createGame(tmpConnections2);
                    }
                }
            }
            if(entry.getValue() == 3){
                k++;
                tmpConnections3.add(entry.getKey());
                if (k==3){
                    playingConnections.add(tmpConnections3);
                    for(int j=0; j < tmpConnections3.size(); j++){
                        waitingConnections.remove(tmpConnections2.get(j));
                        createGame(tmpConnections3);
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
