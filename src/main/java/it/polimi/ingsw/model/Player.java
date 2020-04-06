package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;

import java.util.List;

public class Player {

        private     List<Worker> workers;
        private     String playerID;
        private     int age;

    public int getAge() {
        return age;
    }

    public Player(List<Worker> workers, String playerID, int age) {
        this.workers = workers;
        this.playerID = playerID;
        this.age = age;
        workers.get(0).setPlayer(this);
        workers.get(1).setPlayer(this);
    }

        public String getPlayerID() {
        return playerID;
    }

        public Worker getWorker(int num){
            return workers.get(num);
        }

        public void positionWorker(Model model, PositionWorkers posWorker){
            Tile destination = model.getGrid().getTile(posWorker.getCoordinate());
            if (destination.getWorker() == null){
                destination.setWorker(workers.get(posWorker.getNum()));
            }
        }

        public void makeSelection(Model model, Select select){
            Coordinate selection = select.getCoordinate();
            Worker workerTmp = model.getGrid().getTile(selection).getWorker();

            if (workerTmp.getPlayer().equals(this)){
                model.setCurrentWorker(selection);
                model.setCurrentState((new Move(null)));
            }
            else model.setCurrentState(new Select(null));
        }

        public void makeMovement(Model model, Move move){
            Coordinate destination = move.getChoice();
            Coordinate from = model.getCurrentWorker();

            if(model.getGrid().checkDestination(from,destination)){
                Tile fromTile = model.getGrid().getTile(from);
                Tile destinationTile = model.getGrid().getTile(destination);
                Checks moveCheck= new Checks(fromTile,destinationTile,model).isRisible().isNotDome().isNotWorker();
                if(moveCheck.getResult()){
                    destinationTile.setWorker(fromTile.getWorker());
                    model.setCurrentWorker(destination);
                    fromTile.noneWorker();
                    if(winCondition(fromTile,destinationTile)) model.setCurrentState(new Win());
                    else model.setCurrentState(new Build(null));
                }else model.setCurrentState(new Move(null));


            }else model.setCurrentState(new Move(null));


        }

        public void makeBuild(Model model, Build build){
            Coordinate destination = build.getChoice();
            Coordinate from = model.getCurrentWorker();

            if(model.getGrid().checkDestination(from,destination)) {
                Tile destinationTile = model.getGrid().getTile(destination);
                Checks buildCheck= new Checks(destinationTile).isNotDome().isNotWorker();
                if (buildCheck.getResult()) {
                    try {
                        destinationTile.levelUp();
                    }catch (Exception e){

                    }
                    model.setCurrentState(new End());
                } else model.setCurrentState(new Move(null)); //segnalare la mossa non valida in qualche modo
            }

    }

        private boolean winCondition(Tile from,Tile destination){
            return from.getHeight().equals(TypeBlock.SECOND) && destination.getHeight().equals(TypeBlock.THIRD);
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Player))return false;
            Player that = (Player)obj;
            return this.getPlayerID().equals(that.getPlayerID());

    }
}
