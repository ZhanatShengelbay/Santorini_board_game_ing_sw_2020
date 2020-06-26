package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Power;
import it.polimi.ingsw.utility.Coordinate;

/**
 * Class describes the Power of the Triton, God of the Waves. In the turn of the player who owns the Triton's power
 * in Move state: each time Triton's worker moves into a perimeter space, it may immediately move again, i.e it may do multiple movement
 */
public class Triton extends Player{
    /**
     * Class constructor to keep the player's info
     * @param playerID Player's name
     * @param model
     */
    public Triton(String playerID, Model model) {
        super(playerID, model);
    }

    /**
     * Overridden to make Triton's worker to be able to move several times
     * @param destination The input choice
     * @return result
     */
    @Override
    public boolean makeMovement(Coordinate destination) {
        boolean result= super.makeMovement(destination);
        if(result){
            if(model.getGrid().perimeterTile(destination))
                model.setCurrentState(new Power());
                if(isActive())togglePower();
        }
        return result;
    }

    /**
     * Method checks the power, if it's active then makes the extra movement possible to Triton's worker
     * @param destination of type Coordinate
     * @return true or false
     */
    @Override
    public boolean makePower(Coordinate destination) {
        boolean result;
        if(isActive()) {
            model.setCurrentState(new Move());
             result=makeMovement(destination);

        }
        else{
            model.setCurrentState(new Build());
            result=makeBuild(destination);

        }
        if(!result){
            model.setCurrentState(new Power());
        }
        return result;
    }
}
