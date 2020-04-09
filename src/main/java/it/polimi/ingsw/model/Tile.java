package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

/**
 * This class describes the tile fragment of action
 * @author CG51
 * @version 0.1
 */
public class Tile {
    /**
     *Instance variables block of type TypeBlock and worker of type Worker
     */
    private TypeBlock block;
    private Worker worker;

    /**
     * Constructor declaration of the class, current block is set to the block and current worker is set to null
     * @param block to be set
     * @param coordinate where the block should be set
     */
    public Tile(TypeBlock block,Coordinate coordinate) {
        this.block = block;
        this.worker=null;
    }

    /**
     * getter method to access the height of Typeblock
     * @return block
     */
    public TypeBlock getHeight() {
        return block;
    }

    /**
     * getter method to access the worker
     * @return worker
     */
    public Worker getWorker() {
        return worker;
    }

    /**
     * method to set null value to the worker, is used while checking that block does not contain worker
     */
    public void noneWorker(){
        this.worker=null;
    }

    /**
     * setter method to set the current worker to worker
     * @param worker to be set
     */
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /**
     * Method checks what value of TypeBlock can be built
     * @return current object instance
     * @throws IllegalStateException if the tower is complete
     */
    public Tile levelUp ()throws IllegalStateException {
        switch (block){
            case FLOOR:
                this.block=TypeBlock.FIRST;
                break;
            case FIRST:
                this.block=TypeBlock.SECOND;
                break;
            case SECOND:
                this.block=TypeBlock.THIRD;
                break;
            case THIRD:
                this.block=TypeBlock.DOME;
                break;
            default: throw new IllegalStateException();
        }
        return this;
    }

    /**
     * method is used while checking if block is Dome
     * @return true if the block under check is Dome
     */
    public boolean isDome(){
        return this.block.equals(TypeBlock.DOME);

    }

    /**
     * method is used while the block under check contains a worker
     * @return true if there is a worker, false otherwise
     */
    public boolean isWorker(){
        return this.worker!=null;

    }


}
