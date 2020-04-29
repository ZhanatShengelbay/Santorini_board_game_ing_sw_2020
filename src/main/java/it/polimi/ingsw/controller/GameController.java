package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.model.playerChoice.GameChoice;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.utility.Observer;



public class GameController implements Controller {

    Model model;
    Player currentPlayer;

    public GameController(Model model) {
        this.model = model;
        this.currentPlayer = model.getPlayer(0);
    }

    @Override // Add view to parameters
    public void update(PlayerChoice message) {
        handler(message);
    }

    private void handler(PlayerChoice message) {
        if(!(message instanceof GameChoice))
            return;

        if(model.getCurrentState() instanceof Power && ((GameChoice) message).powerIsActive()) {
            model.getCurrentPlayer().togglePower();
        }

        try {
            boolean result  =  model.getCurrentState().handle(((GameChoice)message).getChoice(),model);
            if(result) {
                model.notify(model.clone());
                message.getView().showMessage(model.getCurrentState().questionMessage());
            }
            else message.getView().showError("Wrong action, retry");

        }catch (IllegalArgumentException e){
            e.printStackTrace();
            message.getView().showError("Illegal argument");
        }


    }


}
