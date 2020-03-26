package it.polimi.ingsw.model;

enum TypeBlock {
    FLOOR (0),
    FIRST(1),
    SECOND(2),
    THIRD(3),
    DOME(4);

    //ToString da implementare

    private int level;

    TypeBlock(int level) {
        this.level=level;
    }






}
