package it.polimi.ingsw;

import it.polimi.ingsw.clientGraphic.BackEndGui;
import it.polimi.ingsw.clientGraphic.CLI;
import it.polimi.ingsw.view.Client;
import it.polimi.ingsw.view.Server;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Server server;
        Client client;
        CLI cli;
        BackEndGui gui;
        String ipAdd;




        if(args.length>1)
            ipAdd=args[1];
        else
            ipAdd="40.113.159.138";
        client=new Client(ipAdd,12345);

        if(args.length>0) {
            if (args[0].toLowerCase().equals("server")) {
                try {
                    server = new Server();
                    server.run();
                } catch (IOException e) {
                    System.err.println("Impossible to start the server!\n" + e.getMessage());
                }
            } else if (args[0].toLowerCase().equals("cli")) {
                cli = new CLI(client);
                client.addObserver(cli);
                Thread t0 = new Thread(client);
                Thread t1 = new Thread(cli);
                t0.start();
                t1.start();

                try {
                    t0.join();
                    t1.join();
                } catch (Exception ex) {
                    System.out.println("Thread interrupted");
                    return;
                }
            }
            else if(args[0].toLowerCase().equals("gui")){
                gui = new BackEndGui(client);
                client.addObserver(gui);

                Thread t0 = new Thread(client);

                t0.start();


                try{
                    t0.join();
                }
                catch (Exception ex){
                    System.out.println("Thread interrupted");
                    return;
                }
            }
        }
        else{
            gui = new BackEndGui(client);
            client.addObserver(gui);

            Thread t0 = new Thread(client);

            t0.start();


            try{
                t0.join();
            }
            catch (Exception ex){
                System.out.println("Thread interrupted");
                return;
            }
        }
    }
}
