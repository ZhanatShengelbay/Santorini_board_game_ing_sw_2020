package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.model.Worker;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Apollo extends Player {

    public Apollo(List<Worker> workers, String playerID) {
        super(workers, playerID);
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
    public void makeMovement(Model model, Move move) {
        Coordinate destination = move.getChoice();
        Coordinate from = model.getCurrentWorker();
        setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotDome().isRisible());
        if (containsInValidCoordinate(destination)) {
            Worker wrkDestination = model.getGrid().getTile(destination).getWorker();

            if(wrkDestination==null)
                moveWorker(model,destination);
            else if(!wrkDestination.getPlayer().equals(this)) {

                Worker wrkFrom = model.getGrid().getTile(model.getCurrentWorker()).getWorker();
                model.getGrid().getTile(destination).setWorker(wrkFrom);
                model.getGrid().getTile(model.getCurrentWorker()).setWorker(wrkDestination);
                model.setCurrentWorker(destination);
                if (winCondition(model, from, destination)) model.setCurrentState(new Win());
                else
                    nextPhase(model);
            }else return;
        } else return;
    }



    @Override
    public void makePower(Model model, Choice choice) {
        throw new IllegalStateException();

    }
}
