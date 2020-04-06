package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Athena extends Player implements GroundEffect {

    private Coordinate from;


    public Athena(List<Worker> workers, String playerID) {
        super(workers, playerID);
    }

    @Override
    public void makeSelection(Model model, Select select) {
        super.makeSelection(model, select);
        from = model.getCurrentWorker();

    }

    @Override
    public void makeMovement(Model model, Move move) {
        super.makeMovement(model, move);
        if(model.getGrid().HeightDifference(from,move.getChoice())==1)
                addEffect(model);


    }

    @Override
    public void nextPhase(Model model) {
        State currentState=model.getCurrentState();
        State nextState=null;
        if(currentState instanceof Select)
            nextState=new Move(null);
        else if(currentState instanceof Move)
            nextState=new Build(null);
        else if(currentState instanceof Build)
            nextState=new End();
        model.setCurrentState(nextState);

    }


    @Override
    public void makePower(Model model, Choice choice) {
        throw new IllegalStateException();

    }

    @Override
    public boolean respectEffect(Model model,Coordinate from, Coordinate destination) {
        if(model.getCurrentState() instanceof Move)
            return model.getGrid().HeightDifference(from, destination) > 0;
        else return false;

    }

    @Override
    public void addEffect(Model model) {
        model.getGroundEffects().add(this);

    }
}
