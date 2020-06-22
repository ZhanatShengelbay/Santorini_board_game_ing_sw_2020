package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Power;
import it.polimi.ingsw.utility.Coordinate;

public class Triton extends Player{
    /**
     * @param playerID
     */
    public Triton(String playerID, Model model) {
        super(playerID, model);
    }


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
        if(!result) model.setCurrentState(new Power());
        return result;
    }
}
