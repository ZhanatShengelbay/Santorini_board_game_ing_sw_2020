package it.polimi.ingsw.model.playerChoice;

import it.polimi.ingsw.utility.Coordinate;
import it.polimi.ingsw.view.RemoteView;

public class GameChoice extends PlayerChoice {

    Coordinate coordinate;

    public GameChoice(Coordinate coordinate, String player_id){
        this.coordinate = coordinate;
        this.player_id = player_id;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String getPlayer(){
        return player_id;
    }
}
