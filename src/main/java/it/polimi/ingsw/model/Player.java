package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Cardinal;

public abstract class Player {


        public void makeSelection(Model model, Select select, Player currentPlayer){
            Worker workerTmp = model.getGrid().getTile(select.getCoordinate().getX(), select.getCoordinate().getY()).getWorker();
            if (workerTmp.player == currentPlayer){
                model.setCurrentWorker(model.getGrid().getTile(select.getCoordinate().getX(), select.getCoordinate().getY()).getWorker());
                model.setCurrentState((new Move(null)));
            }
            else model.setCurrentState(new Select(null));
        }

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
