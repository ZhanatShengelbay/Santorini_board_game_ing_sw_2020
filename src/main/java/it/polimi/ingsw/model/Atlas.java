package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Atlas extends Player {

    /**
     * @param playerID
     */
    public Atlas( String playerID) {
        super( playerID);
    }

    @Override
    public boolean makeBuild(Model model, Coordinate destination) {

        setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome());
        if (containsInValidCoordinate(destination)) {
            if(isActive()){
                int height=model.getGrid().getTile(destination).getHeight().ordinal();
                while(height<TypeBlock.DOME.ordinal())
                    model.getGrid().getTile(destination).levelUp();
                togglePower();
            }
            else
            model.getGrid().getTile(destination).levelUp();
            nextPhase(model);
            return true;
        } else return false;
    }

    @Override
    public void nextPhase(Model model) {
        State currentState=model.getCurrentState();
        State nextState=null;
        if(currentState instanceof Select)
            nextState=new Move();
        else if(currentState instanceof Move)
            nextState=new Power();
        else if(currentState instanceof Build)
            nextState=new End();
        model.setCurrentState(nextState);

    }

    @Override
    public boolean makePower(Model model, Coordinate destination)  {
        model.setCurrentState(new Build());
        boolean result = super.makeBuild(model, destination);
        if(!result)model.setCurrentState(new Power());
        return result;

    }
}
