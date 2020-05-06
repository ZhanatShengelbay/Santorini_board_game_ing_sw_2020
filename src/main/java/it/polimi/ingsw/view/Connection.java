package it.polimi.ingsw.view;

import it.polimi.ingsw.utility.Subject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection extends Subject<String> implements Runnable {

    private Socket socket;
    private Scanner in;
    private ObjectOutputStream out;
    private Server server;
    private String name;
    private int numOfPlayers;
    private boolean active = true;

    public Connection(Socket socket, Server server){
            this.socket = socket;
            this.server = server;
    }

    public String getID(){
        return name;
    }

    public int getNumOfPlayers(){
        return numOfPlayers;
    }

    private synchronized boolean isActive(){
        return active;
    }

    public void send(Object message){
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void asyncSend(final Object message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                send(message);
            }
        }).start();
    }

    public synchronized void closeConnection(){
        send("Connection closed from the server side");
        try{
            socket.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        active = false;
    }

    private void close(){
        closeConnection();
        System.out.println("Deregistering client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
    }

    // Manage in.nextLine errors
    @Override
    public void run() {
        try{
            String read;
            in = new Scanner(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            send("Welcome! What's your name?");
            this.name = in.nextLine();
            send("Choose number of player");
            this.numOfPlayers = Integer.parseInt(in.nextLine());
            server.lobby(this);
            while(isActive()){
                send("Next Input");
                read = in.nextLine();
                notify(read);
            }
        } catch(IOException e){
            System.err.println(e.getMessage());
        } finally {
            close();
        }
    }
}