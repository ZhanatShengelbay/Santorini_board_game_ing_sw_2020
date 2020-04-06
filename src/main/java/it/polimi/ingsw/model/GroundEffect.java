package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.State;
import it.polimi.ingsw.utility.Coordinate;

public interface GroundEffect {


     boolean respectEffect(Model model, Coordinate destination, Coordinate from);

     void addEffect(Model model);


}
