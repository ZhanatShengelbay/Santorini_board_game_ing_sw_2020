package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Cardinal;

public abstract class Player {


        public void makeMovement(Model model, Move move){
            Cardinal direction= move.getChoice();
            Tile from = model.getGrid().getWorkerTile(model.getCurrentWorker());
            Tile destination= model.getGrid().nextTile(from,direction);

            //verifica che la destinazione sia available
            if(model.getCheck().checkDestination(from,destination)){
                    destination.setWorker(model.getCurrentWorker());
                    from.noneWorker();
                    model.setCurrentState(new Build()) ;
            }
            else   model.setCurrentState(new Move()) ; //segnalare la mossa non valida in qualche modo


        }
}
