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
        for(Connection connection : playingConnections.get(c.gameIndex - 1)){
            connection.send(c.getID() + " disconnected");
        }
    }

    public synchronized void createGame(List<Connection> connectionList){
        Connection c1 = connectionList.get(0);
        Connection c2 = connectionList.get(1);
        List<String> players = new ArrayList<>();
        List<RemoteView>views=new ArrayList<>();
        players.add(c1.getID());
        players.add(c2.getID());
        Model model = new Model();
        RemoteView player1view = new RemoteView(c1, model);
        RemoteView player2view = new RemoteView(c2, model);

        model.addObserver(player1view);
        model.addObserver(player2view);
        views.add(player1view);
        views.add(player2view);

        if(connectionList.size()==3) {
            Connection c3 = connectionList.get(2);
            RemoteView player3view = new RemoteView(c3, model);
            model.addObserver(player3view);

            players.add(c3.getID());
            views.add(player3view);
        }
        SetUpController controller = new SetUpController(model, players,views);


    }

    public synchronized void lobby(Connection c) throws Error{
        waitingConnections.put(c, c.getNumOfPlayers());
        List<Connection> tmp2 = new ArrayList<>();
        List<Connection> tmp3 = new ArrayList<>();
        boolean isIn = false;
        for (Map.Entry<Connection, Integer> entry : waitingConnections.entrySet()) {
            if(entry.getValue() == 2){
                if(tmp2.isEmpty()){
                    tmp2.add(entry.getKey());
                } else{
                    for(Connection connection : tmp2){
                        if(connection.getID().compareTo(entry.getKey().getID())==0) {
                            isIn = true;
                            break;
                        }
                    }
                    if(!isIn) {
                        tmp2.add(entry.getKey());
                        if(tmp2.size()==2){
                            playingConnections.add(tmp2);
                            createGame(tmp2);
                            for(Connection connection : tmp2){
                                connection.gameIndex = playingConnections.size();
                                waitingConnections.remove(connection);
                            }
                            break;
                        }
                    }
                }
            }
            else if(entry.getValue() == 3){
                if(tmp3.isEmpty()){
                    tmp3.add(entry.getKey());
                } else{
                    for(Connection connection : tmp3){
                        if(connection.getID().compareTo(entry.getKey().getID())==0) {
                            isIn = true;
                            break;
                        }
                    }
                    if(!isIn) {
                        tmp3.add(entry.getKey());
                        if(tmp3.size()==2){
                            playingConnections.add(tmp3);
                            createGame(tmp3);
                            for(Connection connection : tmp3){
                                connection.gameIndex = playingConnections.size();
                                waitingConnections.remove(connection);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
/*
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
                        connection.gameIndex = playingConnections.size();
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
                        connection.gameIndex = playingConnections.size();
                        waitingConnections.remove(connection);
                    }
                }
            }
        }
    }
*/

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
