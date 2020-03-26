package it.polimi.ingsw.model;

import java.util.List;


public class Checks {
    private Tile from;
    private Tile destination;
    private boolean result;

    public Checks(Tile from, Tile destination,Model model) {
        this.from = from;
        this.destination = destination;
        this.result = true;
        if(!model.playerEffect.isEmpty()){
            for(GroundEffect g : model.playerEffect){
                if(!g.respectEffect(from, destination))
                    result=false;
            }
        }
    }

    public Checks(Tile tile){
        this.destination = tile;
        this.from=null;
        this.result = true;
    }

    static int HIGH_DIFF = 1;



    public Checks isRisible(){
        int highDifference = this.destination.getHigh().ordinal() - this.from.getHigh().ordinal();
        if(highDifference>HIGH_DIFF)result=false;
        return this;
    }

    public Checks isNotDome(){
        if(this.destination.getHigh().equals(TypeBlock.DOME))
            result=false;
        return this;
    }

    public Checks isNotWorker(){
        if(this.destination.getWorker()==null) result=false;
        return this;
    }

    public boolean getResult() {
        return result;
    }
}
