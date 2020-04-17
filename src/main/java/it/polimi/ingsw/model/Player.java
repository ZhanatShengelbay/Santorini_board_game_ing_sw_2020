package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;


import java.util.List;
/**
 * The player is used by the controller to change the model between a Strategy pattern's approach:
 * is responsible of all changes in the grid and
 * all the actions that could be done by its workers are described in this class.
 * The class is abstract because it needs an implementation depending on which God the user decides to use during the game.
 */

public abstract class Player {



    private List<Worker> workers;
    private String playerID;
    private List<Coordinate> validCoordinate;  //is always calculated in the previous action

    /**
     *
     * @param workers
     * @param playerID
     */
    public Player(List<Worker> workers, String playerID) {
        this.workers = workers;
        this.playerID = playerID;
        for (Worker worker : workers)
            worker.setPlayer(this);
    }

    /**
     * Getter method to access the playerID
     * @return the string which holds playerID
     */
    public String getPlayerID() {
        return playerID;
    }

    /**
     * Getter method to access the worker
     * @param num index of worker to be accessed
     * @return specified worker
     */
    public Worker getWorker(int num) {
        return workers.get(num);
    }

    /**
     * Sets the value of coordinate from an object Checks if the passed parameter is in the set of valid coordinates
     * @param checks to be verified
     */
    public final void setValidCoordinate(Checks checks) {
        this.validCoordinate= checks.getResult();
        if(this.validCoordinate.isEmpty())defeatHandler();
    }

    protected void defeatHandler(){


    }

    /**
     * Method locates the worker in the working area (game board) if there is no worker at chosen point
     * @param model
     * @param posWorker
     */
    public void positionWorker(Model model, PositionWorkers posWorker) {
        Tile destination = model.getGrid().getTile(posWorker.getChoice());
        if (destination.getWorker() == null) {
            destination.setWorker(workers.get(posWorker.getNum()));
        }
    }

    /**
     * 
     * @param model
     * @param select
     */
    public void makeSelection(Model model, Select select) {
        Coordinate selection = select.getChoice();
        Worker workerTmp = model.getGrid().getTile(selection).getWorker();

        if (workerTmp!=null &&  workerTmp.getPlayer().equals(this)) {
            model.setCurrentWorker(selection);
            nextPhase(model);
            setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome().isRisible());

        } else return;


    }

    /**
     * Make the condition to check if the movement is available and call the move worker function
     * @param model The model where the movement happened
     * @param move The Move state that contains input choice
     */
    public void makeMovement(Model model, Move move) {
        Coordinate destination = move.getChoice();
        Coordinate from = model.getCurrentWorker();
        setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome().isRisible());
        if (validCoordinate.contains(destination)) {

            moveWorker(model,destination);
            if (winCondition(model, from, destination)) model.setCurrentState(new Win());
            else {
                nextPhase(model);


            }
        } else return;


    }

    protected void moveWorker(Model model,Coordinate destination){

        Worker wrkTmp = model.getGrid().getTile(model.getCurrentWorker()).getWorker();
        model.getGrid().getTile(destination).setWorker(wrkTmp);
        model.getGrid().getTile(model.getCurrentWorker()).noneWorker();
        model.setCurrentWorker(destination);

    }

    public void makeBuild(Model model, Build build) {
        Coordinate destination = build.getChoice();
        setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome());
        if (validCoordinate.contains(destination)) {
            model.getGrid().getTile(destination).levelUp();
            nextPhase(model);
        } else return;
    }

    public boolean winCondition(Model model, Coordinate from, Coordinate destination) {
        Tile tileFrom = model.getGrid().getTile(from);
        Tile tileDestination = model.getGrid().getTile(destination);

        return tileFrom.getHeight().equals(TypeBlock.SECOND) && tileDestination.getHeight().equals(TypeBlock.THIRD);
    }

    /**
     * This function needs an implementation of the FSM structure which describe game's round for each kind of god.
     * For each state, the function had to decide the next state, depends also if the power is active or not.
     * @param model The model where set the new current State
     */
    public abstract void nextPhase(Model model);

    public abstract void makePower(Model model, Choice choice);

    public final boolean containsValidCoordinate(Coordinate coordinate){
        return validCoordinate.contains(coordinate);
    }

    /**
     * Overriden equals method to compare the player object
     * @param obj to be compared with player
     * @return current player if passed parameter is player instance
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) return false;
        Player that = (Player) obj;
        return this.getPlayerID().equals(that.getPlayerID());

    }
}
