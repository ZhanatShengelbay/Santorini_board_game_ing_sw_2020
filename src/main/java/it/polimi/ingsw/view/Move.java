package it.polimi.ingsw.view;

import it.polimi.ingsw.utility.Cardinal;


public class Move implements State {

    Cardinal choice;

    @Override
    public void handleInput(View view) {
        System.out.println("Selezionare direzione di \n");
        String moveDirection=view.inStream.next();
        try{
            choice =Cardinal.parseInput(moveDirection);
        }
        catch (IllegalArgumentException e){
            view.outStream.println("Direzione non valida");
        }

    }

    public Cardinal getChoice() {
        return choice;
    }
}
