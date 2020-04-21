package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.State;
import it.polimi.ingsw.utility.Subject;
import it.polimi.ingsw.utility.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Model extends Subject<Model> implements Cloneable {

    private Grid grid;
    private Coordinate currentWorker;
    private State currentState;
    private List<GroundEffect>groundEffects;
    private List<Player> players;
    private Player currentPlayer;

    public Model clone(){
        Model model = new Model();
        model.grid=this.grid;
        return model;
    }

    public Model(){
        this.grid = new Grid();
        this.groundEffects=new ArrayList<>();
        this.players=new ArrayList<>();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    protected Grid getGrid() {
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

    protected void setCurrentWorker(Coordinate worker) {
        this.currentWorker = worker;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    protected List<GroundEffect> getGroundEffects() {
        return groundEffects;
    }

    public Player getPlayer(int index){
        return players.get(index);
    }

    public void createPlayer(String god, String id){
        if(god.toUpperCase().compareTo("APOLLO") == 1){
            List<Worker> workers = new ArrayList<>();
            workers.add(new Worker());
            workers.add(new Worker());
            players.add(new Apollo(id));
        }
    }

    public void nextPlayer(){
        int index= players.indexOf(currentPlayer);
        if(index==players.size()-1)index=0;
        this.currentPlayer=players.get(index);
    }
}


