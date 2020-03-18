package it.polimi.ingsw.model;

import it.polimi.ingsw.view.View;

import java.util.Scanner;

public interface State {
    /* gli stati determinano il comportamento del controller.
    Esistono pochi stati semplici (inizioPartita, select, move, build, ending)
    ma possono essere espansi con future estensione (es buildChoice per Atlante)
    gli stati vengono comunicati alla view o dal Model o dal controller
     */

    
}
