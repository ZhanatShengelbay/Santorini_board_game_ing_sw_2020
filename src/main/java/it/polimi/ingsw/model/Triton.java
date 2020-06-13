package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Power;
import it.polimi.ingsw.utility.Coordinate;

public class Triton extends Player{
    /**
     * @param playerID
     */
    public Triton(String playerID) {
        super(playerID);
    }


    @Override
    public boolean makeMovement(Model model, Coordinate destination) {
        boolean result= super.makeMovement(model, destination);
        if(result){
            if(model.getGrid().perimeterTile(destination))
                model.setCurrentState(new Power());
                if(isActive())togglePower();
        }
        return result;
    }

    @Override
    public boolean makePower(Model model, Coordinate destination) {
        boolean result;
        if(isActive()) {
            model.setCurrentState(new Move());
             result=makeMovement(model, destination);

        }
        else{
            model.setCurrentState(new Build());
            result=makeBuild(model, destination);

        }
        if(!result) model.setCurrentState(new Power());
        return result;
    }
}
