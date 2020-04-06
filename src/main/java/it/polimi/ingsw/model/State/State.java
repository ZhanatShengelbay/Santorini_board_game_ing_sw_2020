package it.polimi.ingsw.model.State;

import it.polimi.ingsw.utility.Coordinate;

public interface State {
    /* gli stati determinano il comportamento del controller.
    Esistono pochi stati semplici (inizioPartita, select, move, build, ending)
    gli stati vengono comunicati alla view o dal Model o dal controller


     */

    public Coordinate getChoice();







    
}
