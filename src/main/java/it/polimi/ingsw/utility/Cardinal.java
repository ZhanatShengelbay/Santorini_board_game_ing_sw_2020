package it.polimi.ingsw.utility;

/**
 * Class might be useful/ more intuitive to manage the coordinates while using keyboard
 *
 * @author CG51
 * @version 0.1
 */
public enum Cardinal {
    /**
     * costant enum values representing respective cardinal directions
     */
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;
    //potrebbe essere piu utile un sistema cardinale piu intuitivo (esempio tastiera)

    /**
     * Method to parse the string from input into enum value
     *
     * @param input entered string
     * @return corresponding enum value of type Cardinal
     */
    public static Cardinal parseInput(String input) {
        return Enum.valueOf(Cardinal.class, input.toUpperCase());
    }

    /**
     * Getter method to access the abscissa when cardinal system is used
     *
     * @return integer value of the abscissa of the cardinal point
     * @throws RuntimeException if incorrect direction is entered
     */

    public int getX() {
        switch (this) {
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
            default:
                new RuntimeException("Unxpected case!");
        }
        return 0;

    }

    /**
     * getter method of type integer to access the ordinate
     *
     * @return integer value depending on listed constant case
     * @throws RuntimeException if incorrect direction is entered
     */

    public int getY() {
        switch (this) {
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
            default:
                new RuntimeException("Unxpected case!");
        }
        return 0;

    }


}
