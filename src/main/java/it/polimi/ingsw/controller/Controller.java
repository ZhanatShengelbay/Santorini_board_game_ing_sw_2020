package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.model.Move;
import it.polimi.ingsw.model.State;
import it.polimi.ingsw.view.View;


public class Controller implements Observer<State>  {

    View view;
    Model model;
    State currentState;
    Player currentPlayer;
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

    }

    @Override
    public void update(State message) {
        currentState=message;
       handle();

    }
    public void handle() {

        if (currentState instanceof Move)
            makeMovement((Move)currentState);

    }


    public void makeMovement (Move move){
        /*2 approcci, si modifica il model con uno strategy pattern
        ma la sua implementazione puo venire o qua o nel model
         */
        currentState = currentPlayer.makeMovement(model,move);
        handle();

    }
}
