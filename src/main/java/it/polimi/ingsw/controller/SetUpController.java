package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.PositionWorkers;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;

import java.util.ArrayList;
import java.util.List;

public class SetUpController implements Controller {

    public Model model;
    public List<String> players;
    public int current_player;
    public List<String> gods = new ArrayList<>();
    public boolean init;
    public Controller nextController;
    public int numOfPlayerToCreate;

    public SetUpController(Model model, List<String> players){
        this.model = model;
        this.players = players;
        this.current_player = 0;
        this.init=true;
        this.numOfPlayerToCreate=players.size();
        System.out.println("STARTING PLAYER: " + players.get(current_player) + " " + current_player);
    }

    public void addPlayer(String player){
        players.add(player);
        numOfPlayerToCreate++;
    }

    public void handle(PlayerChoice message) throws Error{

            if (init){
                if(((SetUpChoice)message).getInputs().length == 3){
                    for(int i=0; i < ((SetUpChoice)message).getInputs().length; i++){
                        gods.add(i,((SetUpChoice)message).getInputs()[i]);
                    }
                    init=false;
                }
                else{
                    message.getView().showError("Need to select 3 Gods");
                    throw new Error();
                }
            }
            else {
                if(model.getNumOfPlayers() != 0){
                    for(int i = 0; i < model.getNumOfPlayers(); i++){
                        if(((SetUpChoice)message).getInputs()[0].compareTo(model.getPlayer(i).getClass().getSimpleName()) == 0){
                            message.getView().showError("God already selected");
                            throw new Error();
                        }
                    }
                }
                if(gods.contains(((SetUpChoice)message).getInputs()[0])){
                    model.createPlayer(((SetUpChoice)message).getInputs()[0], players.get(current_player));
                    if(nextController == null)
                        this.nextController= new GameController(model);
                    message.getView().removeObserver(this);
                    message.getView().addObserver(nextController);
                    numOfPlayerToCreate--;
                }
                else{
                    message.getView().showError("God needs to be in Gods list");
                    throw new Error();
                }
            }
            if(current_player == players.size() - 1){
                current_player = 0;
            }
            else current_player++;
            if(numOfPlayerToCreate==0){
                model.setCurrentState(new PositionWorkers());
            }

            System.out.println("CURRENT PLAYER: " + players.get(current_player) + " " + current_player);

    }

    @Override
    public void update(PlayerChoice message){
        if(message.getPlayer().compareTo(players.get(current_player)) == 0){
            try{
                handle(message);
            }
            catch (Error e){

            }
        }
        else {
            message.getView().showError("Not your turn");
        }
    }
}
