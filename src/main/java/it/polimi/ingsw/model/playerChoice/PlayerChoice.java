package it.polimi.ingsw.model.playerChoice;

import it.polimi.ingsw.view.RemoteView;

/**
 * Class describes the PlayerChoice. It has the following methods:
 * @author CG51
 * @version 1.1
 */
public abstract class PlayerChoice {
    /**
     * Class attributes
     */
    private RemoteView view;
    private String player_id;

    /**
     * Constructor to initialize the class object
     * @param player_id of type String (player's name)
     * @param view of type RemoteView
     */
    public PlayerChoice(String player_id, RemoteView view) {
        this.view = view;
        this.player_id=player_id;
    }

    /**
     * getter to access the View, different to each player
     * @return
     */
    public RemoteView getView() {
        return view;
    }

    /**
     * getter to access the Players name
     * @return player's name
     */
    public String getPlayer(){
        return player_id;
    }
}
