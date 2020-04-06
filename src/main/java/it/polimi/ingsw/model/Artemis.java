package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Artemis extends Player {

    private Coordinate from;
    private boolean isActive;


    public Artemis(List<Worker> workers, String playerID) {
        super(workers, playerID);
    }

    @Override
    public void makeSelection(Model model, Select select) {
        super.makeSelection(model, select);
        from = select.getChoice();
    }

    @Override
    public void nextPhase(Model model) {
        State currentState=model.getCurrentState();
        State nextState=null;
        if(currentState instanceof Select)
            nextState=new Move(null);

        else if(currentState instanceof Move)
            if (isActive) {
                nextState = new Build(null);
                isActive = false;
            } else nextState = new Choice();
        else if(currentState instanceof Build)
            nextState=new End();
        model.setCurrentState(nextState);

    }

    @Override
    public void makePower(Model model, Choice choice) {
        if(choice.getState() instanceof Build) {
            model.setCurrentState(choice.getState());
            makeBuild(model, (Build) choice.getState());
        }
        else{
            isActive=true;
            model.setCurrentState(choice.getState());
            Coordinate destination = choice.getState().getChoice();
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome().isRisible().remove(from));
            if (containsInValidCoordinate(destination)) {

                moveWorker(model,destination);
                if (winCondition(model, from, destination)) model.setCurrentState(new Win());
                else {
                    nextPhase(model);


                }
            } else return;


        }

    }
}
