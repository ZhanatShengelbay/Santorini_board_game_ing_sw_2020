package it.polimi.ingsw;

import it.polimi.ingsw.clientGraphic.BackEndGui;
import it.polimi.ingsw.clientGraphic.CLI;
import it.polimi.ingsw.view.Client;

import java.util.Scanner;

/**
 * Runs the CLI mode
 * @author CG51
 * @version 1.1
 */
public class ClientCliApp
{


    public static void main(String[] args){

        Client client;
        CLI cli;


        String ipAdd = "127.0.0.1";
        client = new Client(ipAdd, 12345);
        cli = new CLI(client);
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
