package it.polimi.ingsw.model;

public class Checks {

    static int HIGH_DIFF = 1;

    public boolean checkDestination(Tile from, Tile destination){
        if(!isDome(destination)&&!isWorker(destination)&&
        isRisible(from,destination))return true;
        else return false;

    }
    public boolean isRisible(Tile from, Tile destination){
        int highDifference = destination.getHigh().ordinal() - from.getHigh().ordinal();
        return highDifference<=HIGH_DIFF;

    }

    public boolean isDome(Tile t){
        if(t.getHigh().equals(TypeBlock.DOME))return true;
        else return false;
    }

    public boolean isWorker(Tile t){
        if(t.getWorker()!=null) return true;
        else return false;
    }





}
