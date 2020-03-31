package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

public class Tile {
    private TypeBlock block;
    private Worker worker;

    public Tile(TypeBlock block,Coordinate coordinate) {
        this.block = block;
        this.worker=null;
    }



    public TypeBlock getHeight() {
        return block;
    }

    public Worker getWorker() {
        return worker;
    }

    public void noneWorker(){
        this.worker=null;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

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
}
