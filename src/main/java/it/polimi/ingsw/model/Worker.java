package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

class Worker {

    Player player;
    Coordinate position;

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }
}
