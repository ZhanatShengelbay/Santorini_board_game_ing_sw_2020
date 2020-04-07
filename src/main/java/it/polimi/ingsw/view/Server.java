package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Grid;
import it.polimi.ingsw.model.Worker;

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
//        Connection opponent = playingConnections.get(c);
//        if(opponent != null){
//            opponent.closeConnection();
//            playingConnections.remove(c);
//            playingConnections.remove(opponent);
//            //Iterator<String> iterator = waitingConnection.keySet().iterator();
//            //while(iterator.hasNext()){
//            //    if(waitingConnection.get(iterator.next())==c){
//            //        iterator.remove();
//            //    }
//            //}
//        }
    }

    public synchronized void createGame(List<Connection> connectionList){
        if(connectionList.size()==2){
            Connection c1 = connectionList.get(0);
            Connection c2 = connectionList.get(1);
            Worker worker1 = new Worker();
            Worker worker2 = new Worker();
            List<Worker> workers = new ArrayList<>();
            workers.add(worker1);
            workers.add(worker2);
            Player player1 = new Player(workers, c1.getID(), c1.getAge());
            worker1.setPlayer(player1);
            worker2.setPlayer(player1);
            worker1 = new Worker();
            worker2 = new Worker();
            Player player2 = new Player(workers, c2.getID(), c2.getAge());
            worker1.setPlayer(player2);
            worker2.setPlayer(player2);
            RemoteView player1view = new RemoteView(c1, player1);
            RemoteView player2view = new RemoteView(c2, player2);
            List<Player> playerList = new ArrayList<>();
            playerList.add(player1);
            playerList.add(player2);
            Grid grid = new Grid();
            Model model = new Model(grid);
            Controller controller = new Controller(model, playerList);
            model.addObserver(player1view);
            model.addObserver(player2view);
            player1view.addObserver(controller);
            player2view.addObserver(controller);
        }
        else if(connectionList.size()==3){
            Connection c1 = connectionList.get(0);
            Connection c2 = connectionList.get(1);
            Connection c3 = connectionList.get(2);
            Worker worker1 = new Worker();
            Worker worker2 = new Worker();
            List<Worker> workers = new ArrayList<>();
            workers.add(worker1);
            workers.add(worker2);
            Player player1 = new Player(workers, c1.getID(), c1.getAge());
            worker1.setPlayer(player1);
            worker2.setPlayer(player1);
            worker1 = new Worker();
            worker2 = new Worker();
            Player player2 = new Player(workers, c2.getID(), c2.getAge());
            worker1.setPlayer(player2);
            worker2.setPlayer(player2);
            worker1 = new Worker();
            worker2 = new Worker();
            Player player3 = new Player(workers, c3.getID(), c3.getAge());
            worker1.setPlayer(player3);
            worker2.setPlayer(player3);
            RemoteView player1view = new RemoteView(c1, player1);
            RemoteView player2view = new RemoteView(c2, player2);
            RemoteView player3view = new RemoteView(c3, player3);
            List<Player> playerList = new ArrayList<>();
            playerList.add(player1);
            playerList.add(player2);
            playerList.add(player3);
            Grid grid = new Grid();
            Model model = new Model(grid);
            Controller controller = new Controller(model, playerList);
            model.addObserver(player1view);
            model.addObserver(player2view);
            model.addObserver(player3view);
            player1view.addObserver(controller);
            player2view.addObserver(controller);
            player3view.addObserver(controller);
        }
    }

    public synchronized void lobby(Connection c, int age){
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
//        if(waitingConnections.size() == 1){
//            waitingConnections.get(0).send("Choose number of players");
//            numOfPlayers = Integer.parseInt(waitingConnections.get(0).receive());
//        }
//        else if(waitingConnections.size() == 2 && numOfPlayers == 2){
//            Connection c1 = waitingConnections.get(0);
//            Connection c2 = waitingConnections.get(1);
//            Worker worker1 = new Worker();
//            Worker worker2 = new Worker();
//            List<Worker> workers = new ArrayList<>();
//            workers.add(worker1);
//            workers.add(worker2);
//            Player player1 = new Player(workers, c1.getID());
//            worker1.setPlayer(player1);
//            worker2.setPlayer(player1);
//            worker1 = new Worker();
//            worker2 = new Worker();
//            Player player2 = new Player(workers, c2.getID());
//            worker1.setPlayer(player2);
//            worker2.setPlayer(player2);
//            RemoteView player1view = new RemoteView(c1, player1);
//            RemoteView player2view = new RemoteView(c2, player2);
//            Grid grid = new Grid();
//            Model model = new Model(grid);
//            Controller controller = new Controller(model);
//            model.addObserver(player1view);
//            model.addObserver(player2view);
//            player1view.addObserver(controller);
//            player2view.addObserver(controller);
//            playingConnections.add(c1);
//            playingConnections.add(c2);
//            waitingConnections.clear();
//        }
//        else if(waitingConnections.size() == 3 && numOfPlayers == 3){
//            Connection c1 = waitingConnections.get(0);
//            Connection c2 = waitingConnections.get(1);
//            Connection c3 = waitingConnections.get(2);
//            Worker worker1 = new Worker();
//            Worker worker2 = new Worker();
//            List<Worker> workers = new ArrayList<>();
//            workers.add(worker1);
//            workers.add(worker2);
//            Player player1 = new Player(workers, c1.getID());
//            worker1.setPlayer(player1);
//            worker2.setPlayer(player1);
//            worker1 = new Worker();
//            worker2 = new Worker();
//            Player player2 = new Player(workers, c2.getID());
//            worker1.setPlayer(player2);
//            worker2.setPlayer(player2);
//            worker1 = new Worker();
//            worker2 = new Worker();
//            Player player3 = new Player(workers, c3.getID());
//            worker1.setPlayer(player3);
//            worker2.setPlayer(player3);
//            RemoteView player1view = new RemoteView(c1, player1);
//            RemoteView player2view = new RemoteView(c2, player2);
//            RemoteView player3view = new RemoteView(c3, player3);
//            Grid grid = new Grid();
//            Model model = new Model(grid);
//            Controller controller = new Controller(model);
//            model.addObserver(player1view);
//            model.addObserver(player2view);
//            model.addObserver(player3view);
//            player1view.addObserver(controller);
//            player2view.addObserver(controller);
//            player3view.addObserver(controller);
//            playingConnections.add(c1);
//            playingConnections.add(c2);
//            playingConnections.add(c3);
//            waitingConnections.clear();
//        }
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
