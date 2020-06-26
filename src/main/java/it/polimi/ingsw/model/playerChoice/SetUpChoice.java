package it.polimi.ingsw.model.playerChoice;

import it.polimi.ingsw.view.RemoteView;

import java.rmi.Remote;

/**
 * Class describes the setting up the player's Choices
 * @author CG51
 * @version 1.1
 */
public class SetUpChoice extends PlayerChoice {
    /**
     * Class attributes
     */
    String[] inputs;

    /**
     * Constructor to initialize the class object
     * @param inputs
     * @param player_id
     * @param view
     */
    public SetUpChoice(String[] inputs, String player_id, RemoteView view){
        super(player_id,view);
        this.inputs = inputs;

    }

    /**
     * getter to access the input strings
     * @return inputs
     */
    public String[] getInputs() {
        return inputs;
    }

}
