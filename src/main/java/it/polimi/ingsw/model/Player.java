package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Cardinal;

import javax.swing.text.Position;
import java.util.List;

public abstract class Player {

        List<Worker> workers;

        public void positionWorker(Model model, PositionWorkers posWorker){
            Tile destination = model.getGrid().getTile(posWorker.getCoordinate().getX(), posWorker.getCoordinate().getY());
            if (destination.getWorker() == null){
                destination.setWorker(workers.get(posWorker.getNum()));
            }
        }

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
            Tile destination = model.getGrid().nextTile(from,direction);

            //verifica che la destinazione sia available
            if(model.getCheck().checkDestination(from,destination)){
                    destination.setWorker(model.getCurrentWorker());
                    from.noneWorker();
                    model.setCurrentState(new Build()) ;
            }
            else   model.setCurrentState(new Move()) ; //segnalare la mossa non valida in qualche modo


        }
}
