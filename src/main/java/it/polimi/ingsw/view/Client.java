package it.polimi.ingsw.view;

import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.utility.Subject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Subject<Object> implements Observer<String>, Runnable {

    private String ip;
    private int port;
    private boolean active = true;
    Socket socket;
    ObjectInputStream socketIn;
    PrintWriter socketOut;


    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public synchronized boolean isActive(){
        return active;
    }

    public synchronized void setActive(boolean active){
        this.active = active;
    }

    public synchronized void closeSocket(){
        try {
            setActive(false);
            socketIn.close();
            socketOut.close();
            socket.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Thread asyncReadFromSocket(final ObjectInputStream socketIn){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        Object inputObject = socketIn.readObject();
                        if (inputObject != null){
                            Client.this.notify(inputObject);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    closeSocket();
                }
            }
        });
        t.start();
        return t;
    }

    @Override
    public void run() {
        try{
            socket = new Socket(ip, port);
            System.out.println("Connection established");
            socketIn = new ObjectInputStream(socket.getInputStream());
            socketOut = new PrintWriter(socket.getOutputStream());
            Thread t0 = asyncReadFromSocket(socketIn);
            t0.join();
        } catch (Exception ex){
            System.out.println("Connection closed from the client side");
        } finally {
            closeSocket();
        }
    }

    @Override
    public void update(String message) {
        if(message.compareTo("quit") == 0){
            System.out.println("CLIENT CLOSING");
            closeSocket();
        }
        try{
            socketOut.println(message);
            socketOut.flush();
        }
        catch(Exception e){
            closeSocket();
        }
    }
}