package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.view.View;


public class Controller implements Observer<State> {

    View view;
    Model model;
    Player currentPlayer;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

    }

    @Override
    public void update(State message) {
        if (message instanceof Select)
            makeSelection((Select)message);
        else if (message instanceof Move)
            makeMovement((Move)message);
        else if (message instanceof PositionWorkers)
            positionWorker((PositionWorkers)message);
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
}
