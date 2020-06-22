package it.polimi.ingsw;

import it.polimi.ingsw.clientGraphic.BackEndGui;
import it.polimi.ingsw.view.Client;

import java.util.Scanner;

public class ClientGuiApp
{
    public static void main(String[] args){


        Scanner stdin= new Scanner(System.in);
        System.out.println("Insert server ip\n");


        //Client client = new Client("40.113.159.138", 12345);
        Client client = new Client("127.0.0.1", 12345);
        BackEndGui gui= new BackEndGui(client);

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
