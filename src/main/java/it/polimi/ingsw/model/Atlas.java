package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Atlas extends Player {

    boolean isActive;
    /**
     * @param workers
     * @param playerID
     */
    public Atlas(List<Worker> workers, String playerID) {
        super(workers, playerID);
    }

    @Override
    public void makeBuild(Model model, Build build) {
        Coordinate destination = build.getChoice();
        setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome());
        if (containsInValidCoordinate(destination)) {
            if(isActive){
                int height=model.getGrid().getTile(destination).getHeight().ordinal();
                while(height<TypeBlock.DOME.ordinal())
                    model.getGrid().getTile(destination).levelUp();
                isActive=false;
            }
            else
            model.getGrid().getTile(destination).levelUp();
            nextPhase(model);
        } else return;
    }

    @Override
    public void nextPhase(Model model) {
        State currentState=model.getCurrentState();
        State nextState=null;
        if(currentState instanceof Select)
            nextState=new Move(null);
        else if(currentState instanceof Move)
            nextState=new Build(null);
        else if(currentState instanceof Build)
            nextState=new End();
        model.setCurrentState(nextState);

    }

    @Override
    public void makePower(Model model, Choice choice) {


    }
}
