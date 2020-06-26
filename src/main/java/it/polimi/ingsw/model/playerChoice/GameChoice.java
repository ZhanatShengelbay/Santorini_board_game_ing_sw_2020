package it.polimi.ingsw.model.playerChoice;

import it.polimi.ingsw.model.Grid;
import it.polimi.ingsw.utility.Coordinate;
import it.polimi.ingsw.view.RemoteView;

/**
 * Class describes the GameChoice
 * @author CG51
 * @version 1.1
 */
public class GameChoice extends PlayerChoice {
    /**
     * Class attributes
     */
    Coordinate coordinate;
    boolean power;

    /**
     * Constructor to initialize the class object
     * @param x
     * @param y
     * @param playerId
     * @param view
     */
    public GameChoice(int x, int y,String playerId, RemoteView view) {
        super(playerId,view);
        x--;
        y--;
        if(0 > x || x >= Grid.N_ROWS || 0 > y || y >= Grid.N_COLS)
            throw new IllegalArgumentException();
        else this.coordinate=new Coordinate(x,y);
    }

    /**
     * activates the Power
     */
    public void activePower(){power=true;}

    /**
     * getter to access the chosen coordinate
     * @return
     */
    public Coordinate getChoice(){return coordinate;}

    /**
     * Method is used everytime when makePower() is called
     * @return true or false depending on the power
     */
    public boolean powerIsActive() {
        return power;
    }



}
