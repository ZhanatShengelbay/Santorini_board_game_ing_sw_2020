package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

/**
 *  Class describes the power of Demeter. Player owning the Demeter can build 1 additional time, but not at the same place
 *  @author CG51
 *  @version 0.1
 */

public class Demeter extends Player {

    /**
     * Class attributes: iff isActive is true, power can be used
     */

    private Coordinate firstBuild;
    boolean doubleBuild;


    /**
     * Constructor to keep the info of the player
     *
     * @param playerID name of the player
     */
    public Demeter( String playerID, Model model) {
        super( playerID, model);
    }

    /**
     * Overridden to keep the last built coordinate, it is used in the makePower method
     * @param destination
     * @return true or false depending on the condition's result
     */
    @Override
    public boolean makeBuild(Coordinate destination) {
        Checks tmp =new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome();
        if(doubleBuild)
            tmp.remove(firstBuild);
        setValidCoordinate(tmp);
        if (containsInValidCoordinate(destination)) {
            model.getGrid().getTile(destination).levelUp();
            nextPhase();
            doubleBuild=false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method describes the behavior of demeter. The current state received from the model is assigned to current state and
     * next state is first defined as null, later depending on the current state respective new next state info is assigned
     *
     */
    @Override
    public void nextPhase() {
        State currentState = model.getCurrentState();
        State nextState = null;
        if (currentState instanceof Select)
            nextState = new Move();
        else if (currentState instanceof Move)
            nextState = new Power();
        else if (currentState instanceof Build) {
                nextState = new End();
                doubleBuild=false;
                firstBuild=null;
        } else
            nextState = new End();
        model.setCurrentState(nextState);

    }

    /**
     * Method is overridden to describe how the demeter's power acts.
     * if after the building first time player that owns demeter decides to use its power,
     * player chooses the coordinate to build (should NOT be the same coordinate),
     * if there is any valid coordinate to build, builds otherwise goes to the next phase, i.e to the end
     *
     * @param destination
     */
    /*@Override
    public boolean makePower(Model model, Coordinate destination) {

        if (isActive()) {
            model.setCurrentState(new Build());

            setValidCoordinate(new Checks(model, model.getCurrentWorker()).isNotWorker().isNotDome().remove(this.firstBuild));
            if (containsInValidCoordinate(destination)) {
                model.getGrid().getTile(destination).levelUp();
                nextPhase(model);
                return true;
            } else {
                model.setCurrentState(new Power());
                return false;
            }
        } else {
            model.setCurrentState(new End());
            return true;

        }


    }
      */
    @Override
    public boolean makePower(Coordinate destination){
        if(isActive()){
            model.setCurrentState(new Build());
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome());
            if (containsInValidCoordinate(destination)) {
                doubleBuild=true;
                model.getGrid().getTile(destination).levelUp();
                firstBuild=destination;
                return true;
            } else {
                model.setCurrentState(new Power());
                return false;
            }
        }
        else{
            model.setCurrentState(new Build());
            boolean result=makeBuild(destination);
            if(result){
                model.setCurrentState(new End());
            }
            return result;

        }
    }
}
