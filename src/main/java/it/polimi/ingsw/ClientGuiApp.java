package it.polimi.ingsw;

import it.polimi.ingsw.client.ClientBackEnd;
import it.polimi.ingsw.view.CLI;
import it.polimi.ingsw.view.Client;

import java.util.Scanner;

public class ClientGuiApp
{
    public static void main(String[] args){


        Scanner stdin= new Scanner(System.in);
        System.out.println("Insert server ip\n");


        Client client = new Client("127.0.0.1", 12345);
        ClientBackEnd gui= new ClientBackEnd(client);

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
