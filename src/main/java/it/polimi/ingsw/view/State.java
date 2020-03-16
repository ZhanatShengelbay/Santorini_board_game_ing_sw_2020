package it.polimi.ingsw.view;

import java.util.Scanner;

public interface State {
    /* gli stati gestiscono la fase di input all'interno della view
    e determinano il comportamento del controller.
    Esistono pochi stati semplici (inizioPartita, select, move, build, ending)
    ma possono essere espansi con future estensione (es buildChoice per Atlante)
    gli stati vengono comunicati alla view o dal Model o dal controller
     */

    public void handleInput(View view);
}
