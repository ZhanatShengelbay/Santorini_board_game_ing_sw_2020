package it.polimi.ingsw.view;

import it.polimi.ingsw.utility.Subject;

import java.io.IOException;
import java.io.ObjectOutputStream;
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
    int gameIndex;

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
        System.out.println("Deregistering it.polimi.ingsw.client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
    }

    // Manage in.nextLine errors
    @Override
    public void run() {
        try{
            String read;
            String[] splittedInput;
            in = new Scanner(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            send("Welcome! What's your name?");
            while(true){
                read = in.nextLine();
                if(read.length() < 20) break;
                else send("Name too long, max 20 characters");
            }
            this.name = read;
            send("Choose number of player");
            while(true){
                read = in.nextLine();
                splittedInput = read.split(" ");
                if(splittedInput.length == 1){
                    if(splittedInput[0].compareTo("2") == 0 || splittedInput[0].compareTo("3") == 0) break;
                    else send("Can select only 2 or 3 players");
                }
                else send("Only one argument required");
            }
            this.numOfPlayers = Integer.parseInt(read);
            server.lobby(this);
            while(isActive()){
                //send("Next Input");
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