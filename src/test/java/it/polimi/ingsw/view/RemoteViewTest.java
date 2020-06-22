package it.polimi.ingsw.view;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.SetUpController;
import it.polimi.ingsw.model.Atlas;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Pan;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.model.State.GameStart;
import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Power;
import it.polimi.ingsw.model.playerChoice.GameChoice;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RemoteViewTest {

    final int numOfPlayers = 2;

    SetUpController setUpController;
    GameController controller;
    Model model;
    PlayerChoice[] choice;
    RemoteView[] rmView;
    Connection[] connection;
    Socket[] socket;
    List<String> playersID;
    List<Player> players;

    @Before
    public void SetUp(){
        players = new ArrayList<>();
        rmView = new RemoteView[numOfPlayers];
        connection = new Connection[numOfPlayers];
        socket = new Socket[numOfPlayers];
        choice = new PlayerChoice[10];
        model = new Model();

        Atlas player1 = new Atlas("player1", model);
        Pan player2 = new Pan("player2", model);
        model.getPlayers().add(player1);
        model.getPlayers().add(player2);
        //model.players = players;
        model.setCurrentPlayer(model.getPlayer(0));

        controller = new GameController(model);

        for(int i=0; i < numOfPlayers; i++) {
            socket[i] = new Socket();
            connection[i] = new Connection(socket[i], null);
            rmView[i] = new RemoteView(connection[i], model);
            rmView[i].addObserver(controller);
        }
    }

    @Test
    public void handleTestGameStart(){
        model.setCurrentState(new GameStart());
        String[] inputs = {"aPollO", "artemis"};
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertTrue(controller.lastChoice instanceof SetUpChoice);
        assertEquals(0, ((SetUpChoice)controller.lastChoice).getInputs()[0].compareTo("Apollo"));
        assertEquals(0, ((SetUpChoice)controller.lastChoice).getInputs()[1].compareTo("Artemis"));
    }

    @Test
    public void handleTestPowerOK(){
        model.setCurrentState(new Power());
        String[] inputs = {"@", "1", "4"};
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertTrue(controller.lastChoice instanceof GameChoice);
        assertTrue(((GameChoice)controller.lastChoice).powerIsActive());
        assertEquals(0, ((GameChoice)controller.lastChoice).getChoice().getX());
        assertEquals(3, ((GameChoice)controller.lastChoice).getChoice().getY());
    }

    @Test
    public void handleTestPowerKO(){
        model.setCurrentState(new Power());
        String[] inputs = {"1", "4"};
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertTrue(controller.lastChoice instanceof GameChoice);
        assertFalse(((GameChoice)controller.lastChoice).powerIsActive());
        assertEquals(0, ((GameChoice)controller.lastChoice).getChoice().getX());
        assertEquals(3, ((GameChoice)controller.lastChoice).getChoice().getY());
    }

    @Test
    public void handleTestElse(){
        model.setCurrentState(new Move());
        String[] inputs = {"1", "4"};
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertTrue(controller.lastChoice instanceof GameChoice);
        assertFalse(((GameChoice)controller.lastChoice).powerIsActive());
        assertEquals(0, ((GameChoice)controller.lastChoice).getChoice().getX());
        assertEquals(3, ((GameChoice)controller.lastChoice).getChoice().getY());
    }

    @Test
    public void handleTestElsePowerSymbol(){
        model.setCurrentState(new Move());
        String[] inputs = {"@", "1", "4"};
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertNull(controller.lastChoice);
    }

    @Test
    public void handleTestCoordinateNotValid(){
        model.setCurrentState(new Build());
        String[] inputs = {"6", "4"};
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertNull(controller.lastChoice);
        inputs[0] = "3";
        inputs[1] = "-1";
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertNull(controller.lastChoice);
    }

    @Test
    public void handleTestNotAGod(){
        model.setCurrentState(new GameStart());
        String[] inputs = {"atlas", "test", "paN"};
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertNull(controller.lastChoice);
    }

    @Test
    public void handleTestWrongPowerSymbol(){
        model.setCurrentState(new Power());
        String[] inputs = {"!", "1", "2"};
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertNull(controller.lastChoice);
    }

    @Test
    public void handleTestOKPowerSymbolWrongCoordinates(){
        model.setCurrentState(new Power());
        String[] inputs = {"@", "3", "6"};
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertNull(controller.lastChoice);
    }

    @Test
    public void handleTestWrongNumberOfArguments(){
        model.setCurrentState(new Move());
        String[] inputs = {"1", "3", "2", "4"};
        try{
            rmView[0].handleMove(inputs);
        } catch (Exception ex){

        }
        assertNull(controller.lastChoice);
    }

   // @Test
    /*public void updateTest(){
        model.setCurrentState(new Move());
        try{
            rmView[0].update(model.updateState());
        } catch (Exception ex){

        }
        assertTrue(rmView[0].currentState instanceof Move);
        model.setCurrentState(new Build());
        try{
            rmView[0].update(model.updateState());
        } catch (Exception ex){

        }
        assertTrue(rmView[0].currentState instanceof Build);
        model.setCurrentState(new Power());
        try{
            rmView[0].update(model.updateState());
        } catch (Exception ex){

        }
        assertTrue(rmView[0].currentState instanceof Power);
    }*/

}
