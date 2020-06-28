package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

/**
 * This class describes the behaviour of the player's turn, if s/he owns
 * the power of Prometheus - Titan Benefactor of Mankind.
 * This God gives the ability of building both BEFORE and AFTER moving
 * to the player IF the player's worker does NOT move UP, i.e., player can build first
 * and move on the same level or down, but not UP and again build
 */
public class Prometheus extends Player{


    /**
     * Constructor to keep the player's information from the super class
     * @param playerID
     */
    public Prometheus(String playerID, Model model) {
        super( playerID, model);
    }

    /**
     * Overridden in a way to redefine the usual movement of super class Player
     * @param destination The input choice
     * @return true or false depending on the result
     */
    @Override
    public boolean makeMovement(Coordinate destination) {

        Coordinate from = model.getCurrentWorker();
        if(isActive())
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome().isRisible(0));
        else
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome().isRisible());
        if (containsInValidCoordinate(destination)) {

            moveWorker(destination);
            if (winCondition(from, destination)) model.setCurrentState(new Win(this.getPlayerID()));
            else {
                nextPhase();

            }
            return true;
        } else return false;


    }

    /**
     * To set the model in accordance with Prometheus' power
     */
    @Override
    public void nextPhase() {
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

    /**
     * The actual power of Prometheus is described.
     * Takes as a parameter model, and coordinate when the player wants to use her/his power
     * In case the power is active, checks the validity of the passed place and if valid builds,
     * if not valid sets the current state to Build and returns false
     * If power if not active sets the current state to Move and checks the makeMovements result.
     * @param destination
     * @return
     */
    @Override
    public boolean makePower(Coordinate destination) {
        if(isActive()){
            model.setCurrentState(new Build());
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome());
            if (containsInValidCoordinate(destination)) {
                model.getGrid().getTile(destination).levelUp();
                nextPhase();
                return true;
            } else {
                model.setCurrentState(new Power());
                togglePower();
                return false;
            }
        }
        else{
            model.setCurrentState(new Move());
            boolean result= makeMovement(destination);
            if(!result){
                model.setCurrentState(new Power());
                togglePower();
            }
            return result;
        }

    }
}
