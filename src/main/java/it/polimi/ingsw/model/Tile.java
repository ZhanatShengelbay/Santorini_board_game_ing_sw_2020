package it.polimi.ingsw.model;

class Tile {
    TypeBlock block;
    Worker worker=null;
    final int X, Y;

    public Tile(TypeBlock block, int x, int y) {
        this.block = block;
        this.X=x;
        this.Y=y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
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
}
