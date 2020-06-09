package it.polimi.ingsw.controller;
import it.polimi.ingsw.ServerApp;
import it.polimi.ingsw.controller.SetUpController;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;
import it.polimi.ingsw.view.Client;
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
    List<String> players;

    @Before
    public void setUp(){
        players = new ArrayList<>();
        rmView = new RemoteView[numOfPlayers];
        connection = new Connection[numOfPlayers];
        socket = new Socket[numOfPlayers];
        choice = new PlayerChoice[10];
        players.add("player1");
        players.add("player2");
        model = new Model();
        controller = new SetUpController(model, players);
        for(int i=0; i < numOfPlayers; i++) {
            socket[i] = new Socket();
            connection[i] = new Connection(socket[i], null);
            rmView[i] = new RemoteView(connection[i], model);
        }



    }

    @Test
    public void UpdateTestCorrectPlayer(){
        assertTrue(controller.init);
        assertEquals(0, controller.current_player);
        String[] inputs = {"Apollo", "Artemis", "Atlas"};
        choice[0] = new SetUpChoice(inputs, players.get(0), rmView[0]);
        controller.update(choice[0]);
        assertFalse(controller.init);
        assertEquals(0, controller.gods.get(0).compareTo("Apollo"));
        assertEquals(0, controller.gods.get(1).compareTo("Artemis"));
        assertEquals(0, controller.gods.get(2).compareTo("Atlas"));
        assertEquals(1, controller.current_player);
    }

    @Test
    public void UpdateTestWrongPlayer(){
        assertTrue(controller.init);
        assertEquals(0, controller.current_player);
        String[] inputs = {"Apollo", "Artemis", "Atlas"};
        choice[0] = new SetUpChoice(inputs, players.get(1), rmView[0]);
        try{
            controller.update(choice[0]);
        } catch (Error e){

        } catch (Exception ex) {

        }
        assertTrue(controller.init);
        assertEquals(0, controller.gods.size());
        assertEquals(0, controller.current_player);
    }

    @Test
    public void addPlayerTest(){
        assertEquals(controller.numOfPlayerToCreate, 2);
        assertEquals(controller.players.size(), 2);
        assertEquals(controller.numOfPlayerToCreate, controller.players.size());
        assertEquals(0, controller.players.get(0).compareTo("player1"));
        assertEquals(0, controller.players.get(1).compareTo("player2"));
        assertEquals(2, controller.numOfPlayerToCreate);
        controller.addPlayer("player3");
        assertEquals(controller.numOfPlayerToCreate, 3);
        assertEquals(controller.players.size(), 3);
        assertEquals(controller.numOfPlayerToCreate, controller.players.size());
        assertEquals(0, controller.players.get(0).compareTo("player1"));
        assertEquals(0, controller.players.get(1).compareTo("player2"));
        assertEquals(0, controller.players.get(2).compareTo("player3"));
        assertEquals(3, controller.numOfPlayerToCreate);
    }

    @Test
    public void handleTestCase1(){
        assertEquals(2, controller.numOfPlayerToCreate);
        assertTrue(controller.init);
        assertEquals(0, controller.current_player);
        String[] inputs = {"Apollo", "Artemis", "Atlas"};
        choice[0] = new SetUpChoice(inputs, players.get(0), rmView[0]);
        controller.handle(choice[0]);
        assertFalse(controller.init);
        assertEquals(0, controller.gods.get(0).compareTo("Apollo"));
        assertEquals(0, controller.gods.get(1).compareTo("Artemis"));
        assertEquals(0, controller.gods.get(2).compareTo("Atlas"));
        assertEquals(1, controller.current_player);
        assertEquals(2, controller.numOfPlayerToCreate);
    }

    @Test
    public void handleTestCase2(){
        assertTrue(controller.init);
        assertEquals(2, controller.numOfPlayerToCreate);
        assertEquals(0, controller.current_player);
        String[] inputs = {"Apollo", "Artemis"};
        choice[0] = new SetUpChoice(inputs, players.get(0), rmView[0]);
        try{
            controller.handle(choice[0]);
        } catch (Error e){

        } catch (Exception ex){

        }
        assertTrue(controller.init);
        assertEquals(0, controller.gods.size());
        assertEquals(0, controller.current_player);
        assertEquals(2, controller.numOfPlayerToCreate);
    }

    @Test
    public void handleTestCase3GodOK(){
        handleTestCase1();
        assertEquals(0, controller.model.getNumOfPlayers());
        assertEquals(null, controller.model.getCurrentPlayer());
        assertEquals(2, controller.numOfPlayerToCreate);
        String[] inputs = {"Apollo"};
        choice[0] = new SetUpChoice(inputs, players.get(0), rmView[0]);
        try{
            controller.handle(choice[0]);
        } catch (Error e){

        } catch (Exception ex){

        }
        assertEquals(1, controller.model.getNumOfPlayers());
        assertEquals(0, controller.model.getPlayer(0).getClass().getSimpleName().compareTo("Apollo"));
        assertEquals(0, controller.gods.get(0).compareTo("Apollo"));
        assertEquals(0, controller.gods.get(1).compareTo("Artemis"));
        assertEquals(0, controller.gods.get(2).compareTo("Atlas"));
        assertEquals(0, controller.current_player);
        assertEquals(1, controller.numOfPlayerToCreate);
    }

    @Test
    public void handleTestCase3GodKO(){
        handleTestCase1();
        assertEquals(2, controller.numOfPlayerToCreate);
        String[] inputs = {"Pan"};
        choice[0] = new SetUpChoice(inputs, players.get(0), rmView[1]);
        try{
            controller.handle(choice[0]);
        } catch (Error e){

        } catch (Exception ex){

        }
        assertFalse(controller.init);
        assertEquals(0, controller.gods.get(0).compareTo("Apollo"));
        assertEquals(0, controller.gods.get(1).compareTo("Artemis"));
        assertEquals(0, controller.gods.get(2).compareTo("Atlas"));
        assertEquals(1, controller.current_player);
        assertEquals(2, controller.numOfPlayerToCreate);
    }

    @Test
    public void handleTestCase4NewGod(){
        handleTestCase3GodOK();
        String[] inputs = {"Artemis"};
        choice[0] = new SetUpChoice(inputs, players.get(0), rmView[0]);
        try{
            controller.handle(choice[0]);
        } catch (Error e){

        } catch (Exception ex){

        }
        assertFalse(controller.init);
        assertEquals(0, controller.gods.get(0).compareTo("Apollo"));
        assertEquals(0, controller.gods.get(1).compareTo("Artemis"));
        assertEquals(0, controller.gods.get(2).compareTo("Atlas"));
        assertEquals(1, controller.current_player);
        assertEquals(2, controller.model.getNumOfPlayers());
        assertEquals(0, controller.model.getPlayer(0).getClass().getSimpleName().compareTo("Apollo"));
        assertEquals(0, controller.model.getPlayer(1).getClass().getSimpleName().compareTo("Artemis"));
        assertEquals(0, controller.numOfPlayerToCreate);
    }

    @Test
    public void handleTestCase4SameGod(){
        handleTestCase3GodOK();
        String[] inputs = {"Apollo"};
        choice[0] = new SetUpChoice(inputs, players.get(0), rmView[0]);
        try{
            controller.handle(choice[0]);
        } catch (Error e){

        } catch (Exception ex){

        }
        assertFalse(controller.init);
        assertEquals(0, controller.gods.get(0).compareTo("Apollo"));
        assertEquals(0, controller.gods.get(1).compareTo("Artemis"));
        assertEquals(0, controller.gods.get(2).compareTo("Atlas"));
        assertEquals(0, controller.current_player);
        assertEquals(1, controller.model.getNumOfPlayers());
        assertEquals(0, controller.model.getPlayer(0).getClass().getSimpleName().compareTo("Apollo"));
        assertEquals(1, controller.numOfPlayerToCreate);
    }
}
