package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.model.playerChoice.GameChoice;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.view.Event;


/**
 * Handles the communication between Controller and Remote View during the match. Has the following methods:
 * @author CG51
 * @version 1.1
 */

public class GameController implements Controller {
    /**
     * attributes
     */
    Model model;
    Player currentPlayer;

    // For testing

    public PlayerChoice lastChoice;

    /**
     * Initializes the model with current player
     * @param model
     */
    public GameController(Model model) {
        this.model = model;
        this.currentPlayer = model.getPlayer(0);
    }

    /**
     * sends the message to the specific ReomteView (= specific player) during the match
     * @param message
     */
    @Override
    public void update(PlayerChoice message) {
        this.currentPlayer = model.getCurrentPlayer();
        this.lastChoice = message;
        if(currentPlayer.getPlayerID().compareTo(message.getPlayer())==0){
            handler(message);
            System.out.println("CURRENT PLAYER: " + model.getCurrentPlayer().getPlayerID());
            System.out.println("CURRENT STATE: " + model.getCurrentState().getClass().getName());
        }
        else message.getView().showError("Not your turn");
    }

    void handler(PlayerChoice message) {
        if(!(message instanceof GameChoice))
            return;

        if(model.getCurrentState() instanceof Power && ((GameChoice) message).powerIsActive()) {
            if(!model.getCurrentPlayer().isActive())model.getCurrentPlayer().togglePower();
        }

        try {
            boolean result  =  model.getCurrentState().handle(((GameChoice)message).getChoice(),model);
            if(model.getCurrentPlayer().isGameOver()) {
                ModelView modelView=model.updateState();
                model.setCurrentState(new End());
                model.getCurrentState().handle(null,model);

                if(!modelView.getState().equals("win")){
                    message.getView().showEvent(Event.LOST);
                    model.notify(model.updateState().setMessage(modelView.getCurrentPlayer()+" lose!"
                    +" It's now "+model.getCurrentPlayer().getPlayerID()+"'s turn."));
                }




            }
            if(result) {
                if(model.getCurrentState() instanceof End){

                    model.getCurrentState().handle(null,model);
                }
                if(model.getCurrentState() instanceof Win) {
                    model.notify(model.updateState().setMessage("Game Over"));
                }
                else
                    model.notify(model.updateState().setMessage("It's " + model.getCurrentPlayer().getPlayerID() + "'s turn."));
                if(model.getCurrentState().questionMessage() != null)
                    message.getView().showMessage(model.getCurrentState().questionMessage());

            }
            else message.getView().showError("Wrong action, retry");

        }catch (IllegalArgumentException e){
            e.printStackTrace();
            message.getView().showError("Illegal argument");
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }


}
