package it.polimi.ingsw.model;
import it.polimi.ingsw.controller.SetUpController;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;
import it.polimi.ingsw.view.Connection;
import it.polimi.ingsw.view.RemoteView;
import it.polimi.ingsw.view.Server;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class SetUpControllerTest {

    final int numOfPlayers = 2;

    SetUpController controller;
    Model model;
    PlayerChoice[] choice;
    RemoteView[] rmView;
    Connection[] connection;
    Socket[] socket;
    Server server;

    @Before
    public void setUp(){
        List<String> players = new ArrayList<>();
        rmView = new RemoteView[numOfPlayers];
        connection = new Connection[numOfPlayers];
        socket = new Socket[numOfPlayers];
        choice = new PlayerChoice[10];
        players.add("player1");
        players.add("player2");
        model = new Model();
        controller = new SetUpController(model, players);
        try {
            server = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0; i < 2; i++){
            socket[i] = new Socket();
            connection[i] = new Connection(socket[i], server);
            rmView[i] = new RemoteView(connection[i], model);
        }


    }

    @Test
    public void addPlayerTest(){

    }

    @Test
    public void handleTest(){

    }
}
