package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.view.RemoteView;

import java.lang.invoke.WrongMethodTypeException;
import java.util.List;


public class GameController implements Observer<PlayerChoice>, Controller {

    Model model;
    Player currentPlayer;
    List<RemoteView> views;

    public GameController(Model model, List<RemoteView> views) {
        this.model = model;
        this.currentPlayer = model.getPlayer(0);
        this.views = views;
    }

    @Override // Add view to parameters
    public void update(PlayerChoice message) {
        handle(message);
    }

    private void handle(PlayerChoice message){
        try{
            if(model.getCurrentState() instanceof Select){

            }
            else if (model.getCurrentState() instanceof PositionWorkers) {

            }
            else if (model.getCurrentState() instanceof Move) {

            }
            else if (model.getCurrentState() instanceof Build){

            }
        }catch (WrongMethodTypeException e){
            model.setCurrentState(model.getCurrentState());
        }

    }

    public void positionWorker(PositionWorkers posWorker){
        currentPlayer.positionWorker(model, posWorker);
    }

    public void makeSelection(Select select){
        currentPlayer.makeSelection(model, select);
        model.notify(model.clone());
    }

    public void makeMovement(Move move){
        currentPlayer.makeMovement(model, move);
        model.notify(model.clone());
    }
    public void makeBuild(Build build){
        currentPlayer.makeBuild(model, build);
        model.notify(model.clone());

    }

    public void makeChoice(Choice choice){
        try {
            currentPlayer.makePower(model, choice);
        }catch (IllegalArgumentException e){

        }
        model.notify(model.clone());

    }



}
