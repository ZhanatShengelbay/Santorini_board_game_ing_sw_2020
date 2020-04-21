package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.PositionWorkers;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;

import java.util.ArrayList;
import java.util.List;

public class SetUpController implements Controller {


    Model model;
    List<String> players;
    int current_player;
    List<String> gods = new ArrayList<>();
    boolean init;
    Controller nextController;
    int numOfPlayerToCreate;


    public SetUpController(Model model, List<String> players){
        this.model = model;
        this.players = players;
        this.current_player = 0;
        this.init=true;
        this.nextController= new GameController(model);
        this.numOfPlayerToCreate=players.size();
    }

    public void startGame(){

    }

    public void handle(PlayerChoice message){

            if (init){
                for(int i=0; i < ((SetUpChoice)message).getInputs().length; i++){
                   gods.set(i,((SetUpChoice)message).getInputs()[i]);
                   //error handler
                }
                current_player++;
                init=false;
            }
            else {
                //WIP
                if(gods.contains(((SetUpChoice)message).getInputs()[0])){
                    model.createPlayer(((SetUpChoice)message).getInputs()[0], players.get(current_player));
                //error handler
                    message.getView().removeObserver(this);
                    message.getView().addObserver(nextController);
                    numOfPlayerToCreate--;
                }
            }
            if(current_player == players.size() - 1){
                current_player = 0;
            }

            if(numOfPlayerToCreate==0){
                model.setCurrentState(new PositionWorkers());
            }
            else current_player++;

    }

    @Override
    public void update(PlayerChoice message) {
        if(message.getPlayer().compareTo(players.get(current_player)) > 0)
            handle(message);
    }
}
