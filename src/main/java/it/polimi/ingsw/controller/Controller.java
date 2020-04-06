package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.view.View;

import java.util.ArrayList;
import java.util.List;


public class Controller implements Observer<State> {

    View view;
    Model model;
    Player currentPlayer;
    List<Player> playerList = new ArrayList<>();

    public Controller(Model model, List<Player> playerList) {
        this.model = model;
        this.playerList = playerList;
        initGame();
    }

    @Override // Add view to parameters
    public void update(State message) {
        if (message instanceof Select)
            makeSelection((Select)message);
        else if (message instanceof Move)
            makeMovement((Move)message);
        else if (message instanceof PositionWorkers)
            positionWorker((PositionWorkers)message);
        else if (message instanceof EndTurn){
            changePlayer();
        }
    }

    public void changePlayer(){
        int index = 0;
        for (int i=0; i<playerList.size(); i++){
            if (currentPlayer.equals(playerList.get(i)))
                if (i + 1 == playerList.size())
                    index = 0;
                else index = i+1;
        }
        currentPlayer = playerList.get(index);
        currentPlayer.begin();
    }

    public void orderPlayers(){
        Player tmpPlayer;
        int tmpAge = 999;
        for (int i=0; i < playerList.size(); i++){
            tmpAge = 999;
            for (int j=i; j<playerList.size(); j++){
                if (playerList.get(j).getAge() < tmpAge){
                    tmpAge = playerList.get(j).getAge();
                    tmpPlayer = playerList.get(j);
                    playerList.set(j, playerList.get(i));
                    playerList.set(i, tmpPlayer);
                }
            }
        }
        tmpPlayer = null;
    }

    public void initGame(){
        orderPlayers();
        currentPlayer = playerList.get(0);
        currentPlayer.begin() //to implement or use position worker
    }

    public void positionWorker(PositionWorkers posWorker){
        currentPlayer.positionWorker(model, posWorker);
    }

    public void makeSelection(Select select){
        currentPlayer.makeSelection(model, select);
    }

    public void makeMovement(Move move){
        /*2 approcci, si modifica il model con uno strategy pattern
        ma la sua implementazione puo venire o qua o nel model
         */
        currentPlayer.makeMovement(model, move);
    }
    public void build(Build build){

    }


}
