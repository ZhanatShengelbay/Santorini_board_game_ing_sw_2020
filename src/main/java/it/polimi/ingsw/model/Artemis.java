package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;
/**
 *  The power of god Artemis is described in this class. Player holding this god may move 1 more additional time,
 *  but not back to its initial space
 *  @author CG51
 *  @version 0.1
 */
public class Artemis extends Player {
    /**
     * Class attributes
     */
    private Coordinate from;
    private boolean isActive;

    /**
     * Constructor of the class to initialize player's  values
     * @param workers list of workers
     * @param playerID name of the player
     */
    public Artemis(List<Worker> workers, String playerID) {
        super(workers, playerID);
    }

    /**
     * Method is overridden in order to keep the value of initial space
     * @param model
     * @param select
     */
    @Override
    public void makeSelection(Model model, Select select) {
        super.makeSelection(model, select);
        from = select.getChoice();
    }

    /**
     * Method defines the behavior of the Artemis' turn
     * @param model The model where set the new current State
     */
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

    /**
     * In the method the description of power is defined, i.e. how power behaves while it's off and ongit
     * @param model
     * @param choice
     */
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
            if (containsValidCoordinate(destination)) {
                moveWorker(model,destination);
                if (winCondition(model, from, destination)) model.setCurrentState(new Win());
                else {
                    nextPhase(model);
                }
            } else return;


        }

    }
}
