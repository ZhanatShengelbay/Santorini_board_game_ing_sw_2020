package it.polimi.ingsw;

import it.polimi.ingsw.view.CLI;
import it.polimi.ingsw.view.Client;

import java.util.Scanner;

public class ClientCliApp
{
    public static void main(String[] args){
        Scanner stdin= new Scanner(System.in);
        System.out.println("Insert server ip\n");


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
