package it.polimi.ingsw.view;

import it.polimi.ingsw.utility.Subject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Subject<Object> implements Runnable {

    private String ip;
    private int port;
    Socket socket;
    ObjectInputStream socketIn;
    PrintWriter socketOut;
    Thread t0;

    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public synchronized void closeSocket(){
        try {
            socketIn.close();
            socketOut.close();
            socket.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void send(String message){
        if(message.toUpperCase().compareTo("QUIT") == 0){
            t0.stop();
        }
        try{
            socketOut.println(message);
            socketOut.flush();
        }
        catch(Exception e){
            t0.stop();
        }
    }

    public Thread asyncReadFromSocket(final ObjectInputStream socketIn){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Object inputObject = socketIn.readObject();
                        if (inputObject != null){
                            Client.this.notify(inputObject);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
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
            t0 = asyncReadFromSocket(socketIn);
            t0.join();
            closeSocket();
        } catch (Exception ex){
            System.out.println("Connection closed from the client side");
        }
    }
}