package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ModelView implements Serializable {

    private Grid grid;
    private String currentPlayer;
    private List<Player> players;
    private String state;
    private Map<String, String> godsPlayer;

    private String message;


    public ModelView(Model model) {
        Model clone = model.clone();
        this.grid = clone.getGrid();
        this.currentPlayer = clone.getCurrentPlayer().getPlayerID();
        this.players = clone.getPlayers();
        this.state = clone.getCurrentState().getClass().getSimpleName().toLowerCase();
        this.godsPlayer = clone.getGodsPlayer();

    }


    public ModelView(Model model, String message) {
        new ModelView(model).setMessage(message);
    }


    public int getNumOfPlayers() {
        return players.size();
    }

    public String getPlayer(int index) {
        return players.get(index).getPlayerID();
    }


    public Grid getGrid() {
        return grid;
    }

    public String getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public ModelView setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
}