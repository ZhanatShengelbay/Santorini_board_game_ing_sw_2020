package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

public class Tile {
    private TypeBlock block;
    private Worker worker;
    private Coordinate position;

    public Tile(TypeBlock block,Coordinate coordinate) {
        this.block = block;
        this.position=coordinate;
        this.worker=null;
    }

    public Coordinate getPosition() {
        return position;
    }



    public void setHigh(TypeBlock block) {
        this.block = block;
    }

    public TypeBlock getHigh() {
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

    public Tile levelUp() {
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
            default:
        }
        return this;
    }
}
