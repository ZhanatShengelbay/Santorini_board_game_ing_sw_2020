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
        return model;
    }

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

    public int getNumOfPlayers(){return players.size();}

    public Grid getGrid() {
        return grid;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Coordinate getCurrentWorker() {
        return currentWorker;
    }

    public void setCurrentWorker(Coordinate worker) {
        this.currentWorker = worker;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    protected List<PlayerWithGroundEffect> getGroundEffects() {
        return groundEffects;
    }

    public Player getPlayer(int index){
        return players.get(index);
    }

    public List<Player> getPlayers() {
        return players;
    }

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

    public void nextPlayer(){
        if(players.size()==0)return;
        int index= players.indexOf(currentPlayer);
        for(int j=0; j < players.size(); j++) {
            for (int i=0; i < players.size(); i++) {
                if (index == players.size() - 1) index = 0;
                else index++;
                if (!players.get(index).gameOver) {
                    System.out.println("player not gameover: " + players.get(index).getPlayerID());
                    break;
                }
            }
            if (!players.get(index).checkGameOver()) {
                System.out.println("player not checked gameover: " + players.get(index).getPlayerID());
                break;
            }
        }
        this.currentPlayer=players.get(index);
    }

    public Map<String, String> getGodsPlayer() {
        return godsPlayer;
    }

    public ModelView updateState(){
        return new ModelView(this);
    }
}


