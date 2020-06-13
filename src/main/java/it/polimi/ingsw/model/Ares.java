package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Ares extends Player {

    private int numWrk;

    /**
     * @param playerID
     */
    public Ares(String playerID) {
        super(playerID);
    }

    @Override
    public boolean makeSelection(Model model, Coordinate selection) {
        boolean result= super.makeSelection(model, selection);
        if(result) {
            numWrk = model.getGrid().getTile(model.getCurrentWorker()).getWorker().getNum();
            numWrk = 1 - numWrk;
        }
        return result;
    }

    @Override
    public void nextPhase(Model model) {
        State currentState = model.getCurrentState();
        State nextState = null;
        if (currentState instanceof Select)
            nextState = new Move();

        else if (currentState instanceof Move)
            nextState = new Build();

        else if (currentState instanceof Build)
            nextState = new Power();
        model.setCurrentState(nextState);

    }

    @Override
    public boolean makePower(Model model, Coordinate destination) {
        if(isActive()){
            boolean result=false;
            List<Coordinate>tileAroundDestination=model.getGrid().validTileAround(destination);
            for(Coordinate c : tileAroundDestination){
                Worker tmp= model.getGrid().getTile(c).getWorker();
                if(tmp.equals(this.getWorker(numWrk))) {
                    result = this.reduce(model, destination);
                    break;
                }
            }
            if(result)
                model.setCurrentState(new End());
            return result;
        }
        else{
            model.setCurrentState(new End());
            return true;
        }

    }


    public boolean reduce(Model model, Coordinate destination){
        if(!new Checks(model,null,destination).isNotWorker().isNotDome().isNotBuild(0).simpleGetResult()) {
            model.getGrid().getTile(destination).reduceBlock();
            return true;
        }
        else return false;
    }
}
