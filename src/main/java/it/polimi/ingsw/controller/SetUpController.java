package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.GameStart;
import it.polimi.ingsw.model.State.Init;
import it.polimi.ingsw.model.State.SelectGods;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.view.RemoteView;

import java.util.List;

public class SetUpController implements Observer<PlayerChoice>, Controller {

    Model model;
    List<RemoteView> views;
    List<String> players;
    int current_player;

    public SetUpController(Model model, List<String> players, List<RemoteView> views){
        this.model = model;
        this.players = players;
        this.current_player = 0;
        this.views = views;
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
                if((((SelectGods) model.getCurrentState()).getGods()).contains(((SetUpChoice)message).getInputs()[0]))
                    model.createPlayer(((SetUpChoice)message).getInputs()[0], players.get(current_player));
            }
            if(current_player == players.size() - 1){
                current_player = 0;
            }
            else if(current_player == 0){
                GameController controller = new GameController(model, views);
                for(RemoteView rmv : views){
                    rmv.removeObserver(this);
                    rmv.addObserver(controller);
                }
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
