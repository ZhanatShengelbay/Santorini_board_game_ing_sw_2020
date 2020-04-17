package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;
/**
 *  Class describes the power of Demeter. Player owning the Demeter can build 1 additional time, but not at the same place
 *  @author CG51
 *  @version 0.1
 */

public class Demeter extends Player {

    /**
     * Class attributes: iff isActive is true, power can be used
     */

    private boolean isActive;
    private Coordinate destination;


    /**
     * Constructor to keep the info of the player
     * @param workers list of workers belonging to the player
     * @param playerID name of the player
     */
    public Demeter(List<Worker> workers, String playerID) {
        super(workers, playerID);
    }

    /**
     * Method is overridden in order to keep the destination that is used to prevent selecting the same building coordinate
     * @param model
     * @param select selected coordinate
     */
    @Override
    public void makeSelection(Model model, Select select) {
        super.makeSelection(model, select);
        destination = select.getChoice();
    }

    /**
     * Method describes the behavior of demeter. The current state received from the model is assigned to current state and
     * next state is first defined as null, later depending on the current state respective new next state info is assigned
     * @param model sets the new current State
     */
    @Override
    public void nextPhase(Model model) {
        State currentState = model.getCurrentState();
        State nextState = null;
        if (currentState instanceof Select)
            nextState = new Move(null);
        else if (currentState instanceof Move)
            nextState = new Build(null);
        else if (currentState instanceof Build){
            if (isActive){
                nextState = new End();
                isActive = false;
            }else
                nextState = new Choice();
        }else
        nextState = new End();
        model.setCurrentState(nextState);

    }

    /**
     * Method is overridden to describe how the demeter's power acts.
     * if after the building first time player that owns demeter decides to use its power,
     * player chooses the coordinate to build (should NOT be the same coordinate),
     * if there is any valid coordinate to build, builds otherwise goes to the next phase, i.e to the end
     * @param model
     * @param choice
     */
    @Override
    public void makePower(Model model, Choice choice) {
        if (choice.getState() instanceof End) {
            return;
        } else {
            model.setCurrentState(new Build(null));
            Coordinate newBuild = choice.getState().getChoice();
            setValidCoordinate(new Checks(model, model.getCurrentWorker()).isNotWorker().isNotDome().remove(destination));
            if (containsValidCoordinate(newBuild)) {
                model.getGrid().getTile(newBuild).levelUp();
                nextPhase(model);
            } else nextPhase(model);
        }
    }
}
