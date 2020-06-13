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
     */
    public Zeus(String playerID){
        super(playerID);
    }

    /**
     * Along with normal movement, in case the movement actually carried out, it assigns to class variable the coordinate where the worker moved to
     * @param model The model where the movement happened
     * @param destination The input choice
     * @return true or false
     */
    @Override
    public boolean makeMovement(Model model, Coordinate destination) {
        boolean prevAnswer = super.makeMovement(model, destination);
        if (prevAnswer) selfPlace = destination;
        return prevAnswer;
    }

    /**
     * Overridden to exploit the power of Zeus, now method allows to build under itself, together with normal build case
     * @param model which the building process is carrying out
     * @param destination input choice where to build
     * @return true or false
     */
    @Override
    public boolean makeBuild(Model model, Coordinate destination) {
        if (destination != selfPlace) {
           super.makeBuild(model, destination);
        } else {
            setValidCoordinate(new Checks(model, model.getCurrentWorker()).add(this.selfPlace));
            if (model.getGrid().getTile(selfPlace).getHeight().ordinal() < TypeBlock.THIRD.ordinal()) {
                model.getGrid().getTile(selfPlace).levelUp();
                nextPhase(model);
                return true;
            } else return false;
        }
        return false;
    }


    @Override
    public boolean makePower(Model model, Coordinate destination) {
        throw new IllegalArgumentException();
    }
}
