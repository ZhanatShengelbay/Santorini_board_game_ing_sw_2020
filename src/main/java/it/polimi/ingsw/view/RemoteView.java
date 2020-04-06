package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.utility.Subject;

import java.io.PrintStream;
import java.util.Scanner;

public class RemoteView extends Subject<State> implements Observer<Model>{

    private Connection connection;
    private Player player;
    State currentState;
    Model model;

    public RemoteView(Connection connection, Player player) {
        this.connection = connection;
        this.player = player;
    }

    protected void showModel(Model model) {
        //connection.send(model.getOutcome(getPlayer()).printOutcome() + "\tMake your move");
    }

    // INSTEAD OF PASSING A MODEL REFERENCE WITH EVERY NOTIFY, PUT A REFERENCE TO MODEL IN EVERY REMOTEVIEW
    @Override
    public void update(Model message) {
        this.currentState = model.getCurrentState();
        //outStream.println(currentState.toString());
        currentState.getChoice();
        notify(currentState);
    }
}