package it.polimi.ingsw.model;
import it.polimi.ingsw.utility.Coordinate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class describes the validity conditions of the chosen coordinate and it is used by the main actions of the game: move and build.
 * @author CG51
 * @version 0.1
 */

public class Checks {

    /** In this field the attributes of the class that hold the values of declared types */
    private Model model;
    private Coordinate from;
    private List<Coordinate> destinations;
    private List<Boolean> result;


    /**
     * in this construction result is made up from the potential destination points as an Array list which contains the number of false boolean object that equals to the array size
     * @param model of type Model
     * @param from Coordinate
     * @param destination of type Coordinate
     */
    public Checks( Model model, Coordinate from, Coordinate destination) {
        this.model=model;
        this.from = from;
        this.destinations = new ArrayList<>();
        if(model.getGrid().contains(destination)) {
            this.destinations.add(destination);
            result = new ArrayList<>(Collections.nCopies(destinations.size(), true));
        }else{
            //WIP
            this.destinations.add(from);
            result = new ArrayList<>(Collections.nCopies(destinations.size(), false));
        }

    }

    /**
     * overloading Checks construction to made up the valid coordinates in the tile as an array. It's instances are true boolean objects.
     * @param model current model
     * @param coordinate current coordinate
     */
    public Checks(Model model, Coordinate coordinate){
        this.model=model;
        this.from = coordinate;
        this.destinations=model.getGrid().validTileAround(coordinate);
        result=new ArrayList<>(Collections.nCopies(destinations.size(), true));


    }

    /**
     * method calculates the level difference between destination and from coordinates, if the difference is more that maximum possible height, in the array of result false is set in corresponding index
     * @param MaxPositiveHeight integer value
     * @return current object instance
     */
    public Checks isRisible(int MaxPositiveHeight){
        for(Coordinate destination : destinations) {
            int heightDifference = model.getGrid().getTile(destination).getHeight().ordinal() - model.getGrid().getTile(from).getHeight().ordinal();
            if (heightDifference > MaxPositiveHeight) result.set(destinations.indexOf(destination),false);
        }
        return this;
    }

    /**
     * method isRisible() is overloaded for the default case, i.e according to the game rules, without using any power, it is possible to move up to 1 level;
     * @return  isRisible(1)
     */
    public Checks isRisible(){
        return isRisible(1);
    }

    /**
     * method verifies each coordinate in the destinations array whether it is a dome or not, positive results are filled with false element in the corresponding indexes
     * @return current object instance
     */
    public Checks isNotDome(){
        for(Coordinate destination : destinations) {

            if (model.getGrid().getTile(destination).getHeight().equals(TypeBlock.DOME))
                result.set(destinations.indexOf(destination),false);
        }

        return this;
    }


    public Checks isNotBuild(int height){
        for(Coordinate destination : destinations) {

            if (model.getGrid().getTile(destination).getHeight().ordinal()==(height))
                result.set(destinations.indexOf(destination),false);
        }
        return this;
    }


    /**
     * Method verifies that in the possible destinations there are no worker, otherwise false is set into corresponding index of result array
     * @return current object instance
     */
    public Checks isNotWorker(){
        for(Coordinate destination : destinations) {

            if (model.getGrid().getTile(destination).getWorker()!=null)
                result.set(destinations.indexOf(destination),false);
        }

        return this;
    }

    /**
     * method deletes the coordinate that is never reachable from the corresponding index of the destination array
     * @param alwaysFalseCoordinate i.e the coordinate that is impossible to reach
     * @return current object instance
     */
    public Checks remove(Coordinate alwaysFalseCoordinate){
        result.remove(destinations.indexOf(alwaysFalseCoordinate));
        destinations.remove(alwaysFalseCoordinate);
        return this;

    }
    public Checks add(Coordinate additionalValidCoordinate){
        result.add(true);
        destinations.add(additionalValidCoordinate);
        return this;
    }

    /**
     *  getter method to access the result array, verifies the destinations with accessible set of destinations,
     *  in case if one of the players possess the Athena's power
     *  and the destination can not be reachable according to GroundEffect rule, this destination is removed from the array
     * @return the array containing the accessible destination
     */
    public List<Coordinate> getResult() {
        List<PlayerWithGroundEffect> tmp = model.getGroundEffects();
        List<Coordinate> returnList = new ArrayList<>();


        for(Coordinate destination : destinations) {
            if (result.get(destinations.indexOf(destination))) {
                returnList.add(destination);
                if (!tmp.isEmpty()) {
                    for (PlayerWithGroundEffect g : tmp) {
                        if(!g.equals(model.getCurrentPlayer()))
                            if (g.respectEffect(from, destination)) returnList.remove(destination);
                    }
                }
            }
        }
        return returnList;
    }

    public boolean simpleGetResult(){
        if(result==null||result.size()>1) return false; //wrong function
        else return !result.get(0);
    }

}
