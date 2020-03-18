package it.polimi.ingsw.utility;

public enum Cardinal {
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;
    //potrebbe essere piu utile un sistema cardinale piu intuitivo (esempio tastiera)

    public static Cardinal parseInput(String input){
        return Enum.valueOf(Cardinal.class, input.toUpperCase());
    }

    //@ restituisce l'ascissa del punto cardinale
    public int getX(){
        switch(this){
            case N:
            case S:
                return 0;
            case NE:
            case SE:
            case E:
                return 1;
            case SW:
            case NW:
            case W:
                return -1;
            default: new RuntimeException("Unxpected case!");
        }
        return 0;

    }

    //@ restituisce l'ascissa del punto cardinale
    public int getY(){
        switch(this){
            case E:
            case W:
                return 0;
            case NE:
            case NW:
            case N:
                return 1;
            case SW:
            case SE:
            case S:
                return -1;
            default: new RuntimeException("Unxpected case!");
        }
        return 0;

    }


}
