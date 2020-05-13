package it.polimi.ingsw.model.playerChoice;

import it.polimi.ingsw.model.Grid;
import it.polimi.ingsw.utility.Coordinate;
import it.polimi.ingsw.view.RemoteView;

public class GameChoice extends PlayerChoice {

    Coordinate coordinate;
    boolean power;

    public GameChoice(int x, int y,String playerId, RemoteView view) {
        super(playerId,view);
//        x--;
//        y--;
        if(0 > x || x >= Grid.N_ROWS || 0 > y || y >= Grid.N_COLS)
            throw new IllegalArgumentException();
        else this.coordinate=new Coordinate(x,y);
    }

    public void activePower(){power=true;}

    public Coordinate getChoice(){return coordinate;}

    public boolean powerIsActive() {
        return power;
    }



}