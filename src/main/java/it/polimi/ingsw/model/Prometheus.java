package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Prometheus extends Player{


    /**
     * @param playerID
     */
    public Prometheus(String playerID) {
        super( playerID);
    }

    @Override
    public boolean makeMovement(Model model, Coordinate destination) {

        Coordinate from = model.getCurrentWorker();
        if(isActive())
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome().isRisible(0));
        else
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome().isRisible());
        if (containsInValidCoordinate(destination)) {

            moveWorker(model,destination);
            if (winCondition(model, from, destination)) model.setCurrentState(new Win());
            else {
                nextPhase(model);

            }
            return true;
        } else return false;


    }

    @Override
    public void nextPhase(Model model) {
        State currentState=model.getCurrentState();
        State nextState=null;
        if(currentState instanceof Select)
            nextState=new Power();
        else if(currentState instanceof Build)
            if (isActive()) {
                nextState = new Move();
            } else nextState = new End();
        else if(currentState instanceof Move){
            nextState=new Build();
            if(isActive())togglePower();
        }
        model.setCurrentState(nextState);


    }

    @Override
    public boolean makePower(Model model, Coordinate destination) {
        if(isActive()){
            model.setCurrentState(new Build());
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome());
            if (containsInValidCoordinate(destination)) {
                model.getGrid().getTile(destination).levelUp();
                nextPhase(model);
                return true;
            } else {
                model.setCurrentState(new Build());
                return false;
            }
        }
        else{
            model.setCurrentState(new Move());
            boolean result= makeMovement(model,destination);
            if(!result){
                model.setCurrentState(new Power());
            }
            return result;
        }

    }
}
