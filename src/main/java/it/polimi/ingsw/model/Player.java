package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;


import java.util.ArrayList;
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
    boolean power=false;

    /**
     *
     * @param playerID
     */
    public Player (String playerID) {

        this.playerID = playerID;
        this.workers= new ArrayList<>();

    }

    public Worker addWorker(){
        if(workers.size()<2){
        Worker worker=new Worker(this,workers.size());
        this.workers.add(worker);
        return worker;
        }
        else return null;
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
     * @param destination
     * @return
     */
    public boolean positionWorker(Model model, Coordinate destination) {


        if (!model.getGrid().getTile(destination).isWorker()) {
            model.getGrid().getTile(destination).setWorker(addWorker());
            return true;
        }
        else return false;
    }

    /**
     *
     * @param selection
     * @param model
     * @return
     */
    public boolean makeSelection(Model model, Coordinate selection) {
        Worker workerTmp = model.getGrid().getTile(selection).getWorker();

        if (workerTmp!=null &&  workerTmp.getPlayer().equals(this)) {
            model.setCurrentWorker(selection);
            nextPhase(model);
            return true;

        } else return false;


    }

    /**
     * Make the condition to check if the movement is available and call the move worker function
     * @param model The model where the movement happened
     * @param destination The input choice
     * @return
     */
    public boolean makeMovement(Model model, Coordinate destination) {
        Coordinate from = model.getCurrentWorker();
        setValidCoordinate(new Checks(model,from).isNotWorker().isNotDome().isRisible());
        if (validCoordinate.contains(destination)) {

            moveWorker(model,destination);
            if (winCondition(model, from, destination)) model.setCurrentState(new Win());
            else {
                nextPhase(model);

            }
            return true;
        } else return false;


    }

    protected void moveWorker(Model model,Coordinate destination){

        Worker wrkTmp = model.getGrid().getTile(model.getCurrentWorker()).getWorker();
        model.getGrid().getTile(destination).setWorker(wrkTmp);
        model.getGrid().getTile(model.getCurrentWorker()).noneWorker();
        model.setCurrentWorker(destination);

    }

    public boolean makeBuild(Model model, Coordinate destination) {
        setValidCoordinate(new Checks(model,model.getCurrentWorker()).isNotWorker().isNotDome());
        if (validCoordinate.contains(destination)) {
            model.getGrid().getTile(destination).levelUp();
            nextPhase(model);
            return true;
        } else return false;
    }

    public boolean winCondition(Model model, Coordinate from, Coordinate destination) {
        Tile tileFrom = model.getGrid().getTile(from);
        Tile tileDestination = model.getGrid().getTile(destination);

        return tileFrom.getHeight().equals(TypeBlock.SECOND) && tileDestination.getHeight().equals(TypeBlock.THIRD);
    }

    public boolean isActive(){
        return power;
    }

    public void togglePower(){
        if(power)
            power=false;
        else power=true;
    }


    /**
     * This function needs an implementation of the FSM structure which describe game's round for each kind of god.
     * For each state, the function had to decide the next state, depends also if the power is active or not.
     * @param model The model where set the new current State
     */
    public void nextPhase(Model model){
        State currentState = model.getCurrentState();
        State nextState = null;
        if (currentState instanceof Select)
            nextState = new Move();

        else if (currentState instanceof Move)
            nextState = new Build();

        else if (currentState instanceof Build)
            nextState = new End();
        model.setCurrentState(nextState);
    }

    public abstract boolean makePower(Model model, Coordinate destination);

    public final boolean containsInValidCoordinate(Coordinate coordinate){
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
