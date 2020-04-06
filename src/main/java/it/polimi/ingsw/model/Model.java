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

    public Model clone(){
        Model model = new Model(grid);
        model.grid=this.grid;
        return model;
    }

    public Model(Grid grid){
        this.grid = grid;
        this.groundEffects=new ArrayList<>();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    protected Grid getGrid() {
        return grid;
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
}


