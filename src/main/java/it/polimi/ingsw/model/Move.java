package it.polimi.ingsw.model;


public class Move implements State {

    int X, Y;
    Worker worker;

    public Move(Worker worker) {
        this.worker = worker;
    }

    public void setX(int x) { X = x; }

    public void setY (int y) { Y = y; }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
