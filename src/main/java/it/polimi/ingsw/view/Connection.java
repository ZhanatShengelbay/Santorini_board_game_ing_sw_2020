//package it.polimi.ingsw.view;
//
//import com.sun.security.ntlm.Server;
//import it.polimi.ingsw.utility.Subject;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class Connection extends Subject<String> implements Runnable {
//
//    private Socket socket;
//    private Scanner in;
//    private PrintWriter out;
//    private Server server;
//    private String name;
//    private int age;
//    private int numOfPlayers;
//    private boolean active = true;
//
//    public Connection(Socket socket, Server server){
//            this.socket = socket;
//            this.server = server;
//    }
//
//    public String getID(){
//        return name;
//    }
//
//    public int getAge(){
//        return age;
//    }
//
//    public int getNumOfPlayers(){
//        return numOfPlayers;
//    }
//
//    private synchronized boolean isActive(){
//        return active;
//    }
//
//    public String receive(){
//        return in.nextLine();
//    }
//
//    public void send(String message){
//        out.println(message);
//        out.flush();
//    }
//
//    public void asyncSend(final String message){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                send(message);
//            }
//        }).start();
//    }
//
//    public synchronized void closeConnection(){
//        send("Connection closed from the server side");
//        try{
//            socket.close();
//        }catch (IOException e){
//            System.err.println(e.getMessage());
//        }
//        active = false;
//    }
//
//    private void close(){
//        closeConnection();
//        System.out.println("Deregistering client...");
//        server.deregisterConnection(this);
//        System.out.println("Done!");
//    }
//
//    @Override
//    public void run() {
//        try{
//            in = new Scanner(socket.getInputStream());
//            out = new PrintWriter(socket.getOutputStream());
//            send("Welcome! What's your name?");
//            this.name = in.nextLine();
//            send("What's your age?");
//            this.age = Integer.parseInt(in.nextLine());
//            send("Choose number of player");
//            this.numOfPlayers = Integer.parseInt(in.nextLine());
//            server.lobby(this, age);
//            while(isActive()){
//                String read = in.nextLine();
//                notify(read);
//            }
//        } catch(IOException e){
//            System.err.println(e.getMessage());
//        } finally {
//            close();
//        }
//    }
//}