package it.polimi.ingsw.model.playerChoice;

import it.polimi.ingsw.view.RemoteView;

public abstract class PlayerChoice {
    private RemoteView view;
    private String player_id;

    public PlayerChoice(String player_id, RemoteView view) {
        this.view = view;
        this.player_id=player_id;
    }

    public RemoteView getView() {
        return view;
    }

    public String getPlayer(){
        return player_id;
    }
}
