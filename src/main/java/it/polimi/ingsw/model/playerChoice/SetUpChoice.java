package it.polimi.ingsw.model.playerChoice;

import it.polimi.ingsw.view.RemoteView;

import java.rmi.Remote;

public class SetUpChoice extends PlayerChoice {

    String[] inputs;

    public SetUpChoice(String[] inputs, String player_id){
        this.inputs = inputs;
        this.player_id = player_id;
    }

    public String[] getInputs() {
        return inputs;
    }

    @Override
    public String getPlayer(){
        return player_id;
    }
}
