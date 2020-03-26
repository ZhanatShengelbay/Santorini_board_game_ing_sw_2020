package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Subject;
import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Model extends Subject<Model> implements Cloneable {

    private Grid grid;
    private Coordinate currentWorker;
    private State currentState;

    List<GroundEffect>playerEffect;




    public Model clone(){
        Model model = new Model();
        model.grid=this.grid;
        return model;
    }


    @Override
    public String toString() {
        return super.toString();
    }

    protected Grid getGrid() {
        return grid;
    }

    protected Coordinate getCurrentWorker() {
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


