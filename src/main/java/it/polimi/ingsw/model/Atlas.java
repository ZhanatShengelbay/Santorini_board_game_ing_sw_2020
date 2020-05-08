package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Atlas extends Player {

    /**
     * @param playerID
     */
    public Atlas(String playerID) {
        super(playerID);
    }


    @Override
    public void nextPhase(Model model) {
        State currentState = model.getCurrentState();
        State nextState = null;
        if (currentState instanceof Select)
            nextState = new Move();
        else if (currentState instanceof Move)
            nextState = new Power();
        else if (currentState instanceof Build)
            nextState = new End();
        model.setCurrentState(nextState);

    }

    @Override
    public boolean makePower(Model model, Coordinate destination) {
        if (isActive()) {
            model.setCurrentState(new Build());
            setValidCoordinate(new Checks(model, model.getCurrentWorker()).isNotWorker().isNotDome());
            if (containsInValidCoordinate(destination)) {
                Tile t=model.getGrid().getTile(destination);
                while(t.getHeight().ordinal()<TypeBlock.DOME.ordinal())
                    t.levelUp();
                nextPhase(model);
                return true;
            } else {
                model.setCurrentState(new Build());
                return false;
            }
        }
        else{
            model.setCurrentState(new Build());
            boolean result= makeBuild(model,destination);
            if(!result){
                model.setCurrentState(new Power());
            }
            return result;

        }

    }
}
