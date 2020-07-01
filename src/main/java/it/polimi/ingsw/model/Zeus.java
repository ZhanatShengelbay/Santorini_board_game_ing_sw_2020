package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.End;
import it.polimi.ingsw.model.State.Power;
import it.polimi.ingsw.utility.Coordinate;

/**
 * Class' methods define the turn behaviour of Player owning the power of Zeus, the God of sky.
 * Its power makes the Player holding Zeus, be able to build a block under itself.
 * But NOT a DOME, since none worker can stand on the dome.
 * Class has the following methods:
 * @author CG51
 * @version 0.1
 */
public class Zeus extends Player {
    /**
     * Variable to keep the coordinate where the player moved to
     */
    private Coordinate selfPlace;

    /**
     * Constructor to keep the value of PlayerID holding Zeus, which is inherited from the super class
     * @param playerID
     * @param model
     */
    public Zeus(String playerID, Model model){
        super(playerID,model);
    }

    /**
     * Along with normal movement, in case the movement actually carried out, it assigns to class variable the coordinate where the worker moved to
     * @param destination The input choice
     * @return true or false
     */
    @Override
    public boolean makeMovement( Coordinate destination) {
        boolean prevAnswer = super.makeMovement(destination);
        if (prevAnswer) selfPlace = destination;
        return prevAnswer;
    }

    /**
     * Overridden to exploit the power of Zeus, now method allows to build under itself, together with normal build case
     * @param destination input choice where to build
     * @return true or false
     */
    @Override
    public boolean makeBuild( Coordinate destination) {
        if (!(destination.equals(selfPlace))) {
           return super.makeBuild(destination);
        } else {
            setValidCoordinate(new Checks(model, model.getCurrentWorker()).add(this.selfPlace));
            if(containsInValidCoordinate(destination)&&model.getGrid().getTile(destination).getHeight().ordinal() < TypeBlock.THIRD.ordinal()){
                {
                    model.getGrid().getTile(selfPlace).levelUp();
                    nextPhase();
                    return true;
                }
            } else return false;
        }
    }


    @Override
    public boolean makePower( Coordinate destination) {
        throw new IllegalArgumentException();
    }
}
