package it.polimi.ingsw.model.State;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.SetUpController;

public class GameStart implements State {

    GameController newController;
    SetUpController oldController;

    public GameStart(GameController newController, SetUpController oldController){
        this.newController = newController;
        this.oldController = oldController;
    }

    public GameController getNewController() {
        return newController;
    }

    public SetUpController getOldController() {
        return oldController;
    }
}
