package it.polimi.ingsw.model;

public class Tile {
    TypeBlock block;
    Worker worker=null;
    public Tile(TypeBlock block) {
        this.block = block;
    }

    public void setHigh(TypeBlock block) {
        this.block = block;
    }

    public TypeBlock getBlock() {
        return block;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
