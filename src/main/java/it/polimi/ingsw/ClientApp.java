package it.polimi.ingsw;

import it.polimi.ingsw.view.CLI;
import it.polimi.ingsw.view.Client;

public class ClientApp
{
    public static void main(String[] args){
        Client client = new Client("127.0.0.1", 12345);
        CLI cli = new CLI(client);

        client.addObserver(cli);

        Thread t0 = new Thread(client);
        Thread t1 = new Thread(cli);

        t0.start();
        t1.start();

        try{
            t0.join();
            t1.join();
        }
        catch (Exception ex){
            System.out.println("Thread interrupted");
            return;
        }

    }
}
