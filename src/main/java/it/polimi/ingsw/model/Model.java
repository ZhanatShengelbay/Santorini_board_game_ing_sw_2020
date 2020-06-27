package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.End;
import it.polimi.ingsw.model.State.GameStart;
import it.polimi.ingsw.model.State.State;
import it.polimi.ingsw.utility.Subject;
import it.polimi.ingsw.utility.Coordinate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class describes the Model functionality
 * @author CG51
 * @version 1.1
 */
public class Model extends Subject<ModelView> implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
    private Grid grid;
    private Coordinate currentWorker;
    private State currentState;
    private List<PlayerWithGroundEffect>groundEffects;
    List<Player> players;
    private Map<String,String> godsPlayer;
    private Player currentPlayer;

 String winner;


    public Model clone(){
        Model model = new Model();
        model.grid=this.grid;
        model.players=this.players;
        model.currentState=this.currentState;
        model.godsPlayer=this.godsPlayer;
        model.currentPlayer=this.currentPlayer;
        model.currentWorker=this.currentWorker;
        model.winner=this.winner;
        return model;
    }

    /**
     * Class constructor to initialize the objects
     */
    public Model(){
        this.godsPlayer=new HashMap<>();
        this.grid = new Grid();
        this.groundEffects=new ArrayList<>();
        this.players=new ArrayList<>();
        this.currentState = new GameStart();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * getter method to access the number of players in the game
     * @return
     */
    public int getNumOfPlayers(){return players.size();}

    /**
     * getter method to access the grid
     * @return
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * getter to get the current player
     * @return
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * setter to set the current player
     * @param currentPlayer
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * getter to get the coordinate of the current worker
     * @return
     */
    public Coordinate getCurrentWorker() {
        return currentWorker;
    }

    /**
     * setter to set the current worker's coordinate
     * @param worker
     */
    public void setCurrentWorker(Coordinate worker) {
        this.currentWorker = worker;
    }

    /**
     * getter to access the current state
     * @return
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * setter to set the current state
     * @param currentState
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    /**
     * getter to access the players that have ground effect
     * @return
     */
    protected List<PlayerWithGroundEffect> getGroundEffects() {
        return groundEffects;
    }

    /**
     * getter to access the player
     * @param index
     * @return
     */
    public Player getPlayer(int index){
        return players.get(index);
    }

    /**
     * getter to access the players of player list
     * @return
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * creates the player of the game
     * @param god
     * @param id
     */
    // THE GOD NAME NEEDS TO BE THE SAME AS THE CLASS
    public void createPlayer(String god, String id){
        String godStandard = god.substring(0, 1).toUpperCase() + god.substring(1).toLowerCase();
        try {
            Class [] classes = {String.class, Model.class};
            Player new_player = (Player)Class.forName("it.polimi.ingsw.model." + godStandard).getDeclaredConstructor(classes).newInstance(id, this);
            players.add(new_player);
            godsPlayer.put(id,god);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if(players.size() == 1){
            currentPlayer = players.get(0);
        }
    }

    /**
     * to access the next player of the game
     */
    public void nextPlayer(){
        if(players.size()==0)return;
        int index= players.indexOf(currentPlayer);
        for(int j=0; j < players.size(); j++) {
            for (int i=0; i < players.size(); i++) {
                if (index == players.size() - 1) index = 0;
                else index++;
                if (!players.get(index).gameOver) break;
            }
            if (!players.get(index).checkGameOver()) break;
        }
        this.currentPlayer=players.get(index);
    }

    /**
     * setter to set the winner of the game
     * @param winner
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }

    /**
     * getter to access the winner
     * @return
     */
    public String getWinner() {
        return winner;
    }

    /**
     * getter to access the player who owns the God
     * @return
     */
    public Map<String, String> getGodsPlayer() {
        return godsPlayer;
    }

    /**
     * updates the state in ModelView
     * @return
     */
    public ModelView updateState(){
        return new ModelView(this);
    }
}


