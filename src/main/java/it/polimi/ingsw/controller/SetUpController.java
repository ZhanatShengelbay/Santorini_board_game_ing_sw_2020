package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.GameStart;
import it.polimi.ingsw.model.State.Init;
import it.polimi.ingsw.model.State.PositionWorkers;
import it.polimi.ingsw.model.State.SelectGods;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;
import it.polimi.ingsw.utility.Observer;

import java.util.List;

public class SetUpController implements Observer<PlayerChoice>, Controller {

    Model model;
    List<String> players;
    int current_player;

    public SetUpController(Model model, List<String> players){
        this.model = model;
        this.players = players;
        this.current_player = 0;
    }

    public void startGame(){

    }

    public void handle(PlayerChoice message){
        if(message.getPlayer().compareTo(players.get(current_player)) > 0){
            if (model.getCurrentState() instanceof Init){
                SelectGods new_state = new SelectGods();
                for(int i=0; i < ((SetUpChoice)message).getInputs().length; i++){
                    new_state.setGod(((SetUpChoice)message).getInputs()[i], i);
                }
                model.setCurrentState(new_state);
                current_player++;
            }
            else if (model.getCurrentState() instanceof SelectGods){
                model.createPlayer(((SetUpChoice)message).getInputs()[0], players.get(current_player));
            }
            if(current_player == players.size() - 1){
                current_player = 0;
            }
            else if(current_player == 0){
                GameController controller = new GameController(model);
                model.setCurrentState(new GameStart(controller, this));
            }
            else current_player++;
        }
    }

    @Override
    public void update(PlayerChoice message) {
        handle(message);
    }
}
