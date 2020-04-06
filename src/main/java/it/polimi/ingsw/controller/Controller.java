package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.view.View;

import java.lang.invoke.WrongMethodTypeException;


public class Controller implements Observer<State> {

    View view;
    Model model;
    Player currentPlayer;

    public Controller(Model model) {
        this.model = model;
    }

    @Override // Add view to parameters
    public void update(State message) {
        handler(message);
    }

    private void handler(State message){
        try{
            if (message instanceof Select)
                makeSelection((Select)message);
            else if (message instanceof Move)
                makeMovement((Move)message);
            else if (message instanceof PositionWorkers)
                positionWorker((PositionWorkers)message);
            else if (message instanceof Build)
                makeBuild((Build)message);
            else if (message instanceof Choice)
                makeChoice((Choice) message);
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
