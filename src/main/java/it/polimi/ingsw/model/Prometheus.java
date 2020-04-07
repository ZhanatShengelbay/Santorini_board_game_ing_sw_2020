package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Prometheus extends Player{

    boolean isActive;

    /**
     * @param workers
     * @param playerID
     */
    public Prometheus(List<Worker> workers, String playerID) {
        super(workers, playerID);
    }

    @Override
    public void makeMovement(Model model, Move move) {
        Coordinate destination = move.getChoice();
        Coordinate from = model.getCurrentWorker();
        if(isActive)
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome().isRisible(0));
        else
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome().isRisible());
        if (containsInValidCoordinate(destination)) {

            moveWorker(model,destination);
            if (winCondition(model, from, destination)) model.setCurrentState(new Win());
            else {
                nextPhase(model);


            }
        } else return;


    }

    @Override
    public void nextPhase(Model model) {
        State currentState=model.getCurrentState();
        State nextState=null;
        if(currentState instanceof Select)
            nextState=new Choice();
        else if(currentState instanceof Build)
            if (isActive) {
                nextState = new Move(null);
                isActive = false;
            } else nextState = new End();
        else if(currentState instanceof Move)
            nextState=new Build(null);
        model.setCurrentState(nextState);


    }

    @Override
    public void makePower(Model model, Choice choice) {
        if(choice.getState() instanceof Move) {
            model.setCurrentState(choice.getState());
            makeMovement(model,(Move)choice.getState());
        }
        else{
            isActive=true;
            makeBuild(model,(Build)choice.getState());


        }

    }
}
