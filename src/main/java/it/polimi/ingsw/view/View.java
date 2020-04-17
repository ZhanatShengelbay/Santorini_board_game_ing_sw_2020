package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.State;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.utility.Subject;

import java.io.PrintStream;
import java.util.Scanner;

public class View extends Subject<State> implements Observer<State> {

    State currentState; // use reference to model? clone it? can we pass references through socket?
    Scanner inStream;
    PrintStream outStream;
    Model model;

    public View(){
        inStream = new Scanner(System.in);
        outStream = new PrintStream(System.out);
    }

    @Override
    public void update(State message) {
        this.currentState = model.getCurrentState();
        outStream.println(currentState.toString());
       // currentState.getInput();
        notify(currentState);
    }
    



}
