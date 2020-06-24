package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Ares extends Player {

    private int numWrk;

    /**
     * @param playerID
     */
    public Ares(String playerID, Model model) {
        super(playerID, model);
    }

    @Override
    public boolean makeSelection(Coordinate selection) {
        boolean result= super.makeSelection(selection);
        if(result) {
            numWrk = model.getGrid().getTile(model.getCurrentWorker()).getWorker().getNum();
            numWrk = 1 - numWrk;
        }
        return result;
    }

    @Override
    public void nextPhase() {
        State currentState = model.getCurrentState();
        State nextState = null;
        if (currentState instanceof Select)
            nextState = new Move();

        else if (currentState instanceof Move)
            nextState = new Build();

        else if (currentState instanceof Build)
            nextState = new PowerEnd();
        model.setCurrentState(nextState);

    }

    @Override
    public boolean makePower(Coordinate destination) {
        if(isActive()){
            model.setCurrentState(new Move());
            boolean result=false;
            List<Coordinate>tileAroundDestination=model.getGrid().validTileAround(destination);
            for(Coordinate c : tileAroundDestination){
                Worker tmp= model.getGrid().getTile(c).getWorker();
                if(tmp.equals(this.getWorker(numWrk))) {
                    result = this.reduce(destination);
                    break;
                }
            }
            if(result) {
                model.setCurrentState(new End());
                numWrk=0;
            }
            else
            {
                model.setCurrentState(new PowerEnd());
                togglePower();
            }
            return result;
        }
        else{
            model.setCurrentState(new End());
            return true;
        }

    }


    public boolean reduce(Coordinate destination){
        if(!new Checks(model,null,destination).isNotWorker().isNotDome().isNotBuild(0).simpleGetResult()) {
            model.getGrid().getTile(destination).reduceBlock();
            return true;
        }
        else return false;
    }
}
