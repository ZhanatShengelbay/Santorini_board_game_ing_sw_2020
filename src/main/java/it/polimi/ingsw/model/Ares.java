package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

/**
 * This class describes the behaviour of the Ares, God of war
 * It has the power or removing the block. At the end of the Ares' turn, one of the places neighboring the unmoved worker of Ares might be selected.
 * If the chosen place is free of worker and last block is not a dome then it's last block is removed
 *
 * @author CG51
 *
 * @version 1.1
 */
public class Ares extends Player {
    /**
     * Class attribute
     */
    private int numWrk;

    /**
     * Class constructor to set the player's ID
     * @param playerID
     * @param model
     */
    public Ares(String playerID, Model model) {
        super(playerID, model);
    }

    /**
     * Choosing the coordinate with respect to Ares' power and the index of unmoved worker is assigned to numWork var
     * @param selection of type Coordinate
     * @return true or false depending on the result
     */

    @Override
    public boolean makeSelection(Coordinate selection) {
        boolean result= super.makeSelection(selection);
        if(result) {
            numWrk = model.getGrid().getTile(model.getCurrentWorker()).getWorker().getNum();
            numWrk = 1 - numWrk;
        }
        return result;
    }

    /**
     * Behaviour of FSM is defined in accordance with the Ares' power
     */
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

    /**
     * Defines the Power of Ares
     * @param destination of type Coordinate
     * @return true or false depending on the result
     */
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

    /**
     * Method removes the last block of the Coordinate
     * @param destination
     * @return true or false
     */
    public boolean reduce(Coordinate destination){
        if(!new Checks(model,null,destination).isNotWorker().isNotDome().isNotBuild(0).simpleGetResult()) {
            model.getGrid().getTile(destination).reduceBlock();
            return true;
        }
        else return false;
    }
}
