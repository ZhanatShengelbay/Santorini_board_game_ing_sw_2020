package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Subject;

public class Model extends Subject<Model> implements Cloneable {

    private Grid grid;
    private Worker currentWorker;
    private State currentState;
    private Checks check;



    @Override
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

    protected Checks getCheck() {
        return check;
    }

    protected Worker getCurrentWorker() {
        return currentWorker;
    }

    protected void setCurrentWorker(Worker worker) {
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


