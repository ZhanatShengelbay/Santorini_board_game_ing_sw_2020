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
}
