package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Cardinal;
import static it.polimi.ingsw.model.TypeBlock.*;


public class Build implements State {
    
    private Cardinal choice;
    private TypeBlock block;
    private State currentState;
    private Worker worker;
    private Tile tile;


    public Build(Cardinal choice, TypeBlock block, Worker worker, State currentState) {
        this.choice = choice;
        this.block = block;
        this.worker = worker;
        this.currentState = currentState;
        this.tile = tile; /*??worker.getPosition() \\ ??Grid.nextTile(Grid.getWorkerTile(), choice);?? to calculate the chosen place's coordinate/tile
        (getWorkerTile() is the actual place of worker, e.g. after the movement, it's needed to define the grid of the game (room) .. later*/
    }

    public Cardinal getChoice() {
        return choice;
    }

    public boolean isBuildable(){
       if(!currentState.equals(DOME) && !currentState.equals(worker)){
          return true;
       } else return false;

       /* if(!c.isDome(tile) && !c.isWorker(tile)){
            return true;
        } else return false;*/


    public void build() throws IllegalStateException {
        if(isBuildable() == true){
            if (FLOOR.equals(currentState)) {
                block = FIRST;

            } else if (FIRST.equals(currentState)) {
                block = SECOND;

            } else if (SECOND.equals(currentState)) {
                block = THIRD;

            } else if (THIRD.equals(currentState)) {
                block = DOME;
            } else {
                throw new IllegalStateException("Unexpected value: " + currentState);
            }
        }
    }
}
