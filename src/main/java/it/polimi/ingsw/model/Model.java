package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Subject;

public class Model extends Subject<Model> implements Cloneable {
    Grid grid;

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




}


