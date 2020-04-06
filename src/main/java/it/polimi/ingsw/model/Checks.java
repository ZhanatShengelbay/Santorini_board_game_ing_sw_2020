package it.polimi.ingsw.model;


import it.polimi.ingsw.utility.Coordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Checks {
    private Model model;
    private Coordinate from;
    private List<Coordinate> destinations;
    private List<Boolean> result;



    public Checks( Model model, Coordinate from, Coordinate destination) {
        this.model=model;
        this.from = from;
        this.destinations=new ArrayList<>();
        this.destinations.add(destination);
        result=new ArrayList<>(Collections.nCopies(destinations.size(), false));


    }

    public Checks(Model model, Coordinate from){
        this.model=model;
        this.from = from;
        this.destinations=model.getGrid().validTileAround(from);
        result=new ArrayList<>(Collections.nCopies(destinations.size(), true));


    }

    public Checks isRisible(int MaxPositiveHeight){
        for(Coordinate destination : destinations) {
            int heightDifference = model.getGrid().getTile(destination).getHeight().ordinal() - model.getGrid().getTile(from).getHeight().ordinal();
            if (heightDifference > MaxPositiveHeight) result.set(destinations.indexOf(destination),false);
        }
        return this;
    }

    public Checks isRisible(){
        return isRisible(1);
    }

    public Checks isNotDome(){
        for(Coordinate destination : destinations) {

            if (model.getGrid().getTile(destination).getHeight().equals(TypeBlock.DOME))
                result.set(destinations.indexOf(destination),false);
        }

        return this;
    }

    public Checks isNotWorker(){
        for(Coordinate destination : destinations) {

            if (model.getGrid().getTile(destination).getWorker()!=null)
                result.set(destinations.indexOf(destination),false);
        }

        return this;
    }

    public Checks remove(Coordinate alwaysFalseCoordinate){
        result.remove(destinations.indexOf(alwaysFalseCoordinate));
        destinations.remove(alwaysFalseCoordinate);
        return this;

    }

    public List<Coordinate> getResult() {
        List<GroundEffect> tmp = model.getGroundEffects();
        List<Coordinate> returnList = new ArrayList<>();


        for(Coordinate destination : destinations) {
            if(result.get(destinations.indexOf(destination)))   returnList.add(destination);
            if(!tmp.isEmpty())
                for(GroundEffect g : tmp){
                    if(g.respectEffect(model, from, destination))    destinations.remove(destination);
                }
         }
        return returnList;
    }

}
