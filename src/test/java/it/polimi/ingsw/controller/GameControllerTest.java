package it.polimi.ingsw.controller;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.PositionWorkers;
import it.polimi.ingsw.model.State.Power;
import it.polimi.ingsw.model.playerChoice.GameChoice;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;
import it.polimi.ingsw.utility.Coordinate;
import it.polimi.ingsw.view.Connection;
import it.polimi.ingsw.view.RemoteView;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameControllerTest{

    final int numOfPlayers = 2;
    Model model;
    GameController controller;
    List<Player> players = new ArrayList<>();
    PlayerChoice[] choice;
    RemoteView[] rmView;
    Connection[] connection;
    Socket[] socket;

    @Before
    public void SetUp(){
        model = new Model();
        Atlas player1 = new Atlas("player1");
        Pan player2 = new Pan("player2");
        model.getPlayers().add(player1);
        model.getPlayers().add(player2);
        //model.players = players;
        model.setCurrentPlayer(model.getPlayer(0));
        controller = new GameController(model);
        rmView = new RemoteView[numOfPlayers];
        connection = new Connection[numOfPlayers];
        socket = new Socket[numOfPlayers];
        choice = new PlayerChoice[10];
        for(int i=0; i < numOfPlayers; i++) {
            socket[i] = new Socket();
            connection[i] = new Connection(socket[i], null);
            rmView[i] = new RemoteView(connection[i], model);
        }
    }

    @Test
    public void updateTestCorrectPlayer(){
        model.setCurrentState(new PositionWorkers());
        choice[0] = new GameChoice(2, 2, "player1", rmView[0]);
        controller.update(choice[0]);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                assertSame(model.getGrid().getTile(i, j).getHeight(), TypeBlock.FLOOR);
                if(i==2 && j==2){
                    assertTrue(model.getGrid().getTile(i, j).isWorker());
                }
                else{
                    assertFalse(model.getGrid().getTile(i, j).isWorker());
                }
            }
    }

    @Test
    public void updateTestWrongPlayer(){
        choice[0] = new GameChoice(2, 2, "player2", rmView[0]);
        try{
            controller.update(choice[0]);
        } catch (Error e){

        } catch (Exception ex){

        }
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                assertSame(model.getGrid().getTile(i, j).getHeight(), TypeBlock.FLOOR);
                assertFalse(model.getGrid().getTile(i, j).isWorker());
            }
    }

    @Test
    public void handleTestNotGameChoice(){
        String[] inputs = {"Test", "Test"};
        choice[0] = new SetUpChoice(inputs, "player1", rmView[0]);
        controller.handler(choice[0]);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                assertSame(model.getGrid().getTile(i, j).getHeight(), TypeBlock.FLOOR);
                assertFalse(model.getGrid().getTile(i, j).isWorker());
            }
    }

    @Test
    public void handleTestOK(){
        model.setCurrentState(new PositionWorkers());
        choice[0] = new GameChoice(2, 2, "player1", rmView[0]);
        controller.handler(choice[0]);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                assertSame(model.getGrid().getTile(i, j).getHeight(), TypeBlock.FLOOR);
                if(i==2 && j==2){
                    assertTrue(model.getGrid().getTile(i, j).isWorker());
                }
                else{
                    assertFalse(model.getGrid().getTile(i, j).isWorker());
                }
            }
    }

    @Test
    public void handleTestPower(){
        model.setCurrentState(new Power());
        model.getGrid().getTile(1, 2).setWorker(new Worker(model.getPlayer(0), 0));
        model.setCurrentWorker(new Coordinate(1, 2));
        choice[0] = new GameChoice(2, 2, "player1", rmView[0]);
        ((GameChoice)choice[0]).activePower();
        controller.handler(choice[0]);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                if(i==1 && j==2){
                    assertTrue(model.getGrid().getTile(i, j).isWorker());
                    assertSame(model.getGrid().getTile(i, j).getHeight(), TypeBlock.FLOOR);
                }
                else if(i==2 && j==2){
                    assertFalse(model.getGrid().getTile(i, j).isWorker());
                    assertSame(model.getGrid().getTile(i, j).getHeight(), TypeBlock.DOME);
                } else{
                    assertFalse(model.getGrid().getTile(i, j).isWorker());
                    assertSame(model.getGrid().getTile(i, j).getHeight(), TypeBlock.FLOOR);
                }
            }
    }

    @Test
    public void handleTestWrongAction(){
        model.setCurrentState(new Move());
        model.getGrid().getTile(1, 2).setWorker(new Worker(model.getPlayer(0), 0));
        model.setCurrentWorker(new Coordinate(1, 2));
        choice[0] = new GameChoice(3, 3, "player1", rmView[0]);
        try{
            controller.handler(choice[0]);
        } catch (Error e){

        } catch (Exception ex){

        }
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                assertSame(model.getGrid().getTile(i, j).getHeight(), TypeBlock.FLOOR);
                if(i==1 && j==2)
                    assertTrue(model.getGrid().getTile(i, j).isWorker());
                else
                    assertFalse(model.getGrid().getTile(i, j).isWorker());
            }
    }
}




