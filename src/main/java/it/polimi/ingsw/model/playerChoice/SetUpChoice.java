package it.polimi.ingsw.model.playerChoice;

import it.polimi.ingsw.view.RemoteView;

import java.rmi.Remote;

public class SetUpChoice extends PlayerChoice {

    String[] inputs;

    public SetUpChoice(String[] inputs, String player_id, RemoteView view){
        super(player_id,view);
        this.inputs = inputs;

    }

    public String[] getInputs() {
        return inputs;
    }

}
