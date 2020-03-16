package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.view.Move;
import it.polimi.ingsw.view.State;
import it.polimi.ingsw.view.View;


public class Controller implements Observer<State>  {

    View view;
    Model model;
    State currentState;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

    }

    @Override
    public void update(State message) {
        currentState=message;
        if (currentState instanceof Move)
            makeMovement((Move)currentState);

    }

    public void makeMovement (Move move){
        /*2 approcci, si modifica il model con uno strategy pattern
        ma la sua implementazione puo venire o qua o nel model
         */


    }
}
