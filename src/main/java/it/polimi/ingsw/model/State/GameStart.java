package it.polimi.ingsw.model.State;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.SetUpController;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.utility.Coordinate;

public class GameStart implements State {

    //WARNING CLASS WIP
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

    @Override
    public boolean handle(Coordinate choice, Model model) {
        return false;
    }

    @Override
    public String questionMessage() {
        return null;
    }
}
