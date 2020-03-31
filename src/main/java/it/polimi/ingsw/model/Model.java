package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Subject;
import it.polimi.ingsw.utility.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Model extends Subject<Model> implements Cloneable {

    private Grid grid;
    private Coordinate currentWorker;
    private State currentState;

    List<GroundEffect>playerEffect = new ArrayList<GroundEffect>();




    public Model clone(){
        Model model = new Model(grid);
        model.grid=this.grid;
        return model;
    }

    public Model(Grid grid){
        this.grid = grid;
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

    protected void setCurrentState(State currentState) {
        this.currentState = currentState;
        notify(this);
    }


}


