package it.polimi.ingsw;

import it.polimi.ingsw.view.CLI;
import it.polimi.ingsw.view.Client;

public class ClientApp
{
    public static void main(String[] args){
        Client client = new Client("127.0.0.1", 12345);
        CLI cli = new CLI();
        client.addObserver(cli);
        cli.addObserver(client);

        Thread t0 = new Thread(client);
        Thread t1 = new Thread(cli);

        t0.setDaemon(true);
        t1.setDaemon(true);

        t0.start();
        t1.start();

        while(true){
            if(!client.isActive()) t0.interrupt();
            if(!cli.isActive()) t1.interrupt();
            if(t0.isInterrupted() && t1.isInterrupted()) {
                System.out.println("CLIENT APP CLOSING");
                break;
            }
        }
    }
}
