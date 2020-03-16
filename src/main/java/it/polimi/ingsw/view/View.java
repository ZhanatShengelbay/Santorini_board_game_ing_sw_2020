package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.utility.Subject;

import java.io.PrintStream;
import java.util.Scanner;

public class View extends Subject<State> implements Observer<Model> {

    State currentState;
    Scanner inStream;
    PrintStream outStream;

    @Override
    public void update(Model message) {
        message.toString();
    }

    public View(Scanner answer) {
        this.inStream = new Scanner(System.in);
        this.outStream = new PrintStream(System.out);
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void handle(State state){
        state.handleInput(this);
        notify(state);
    }
}
