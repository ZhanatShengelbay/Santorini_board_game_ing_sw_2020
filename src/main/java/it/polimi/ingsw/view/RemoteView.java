package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.model.playerChoice.GameChoice;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;
import it.polimi.ingsw.utility.Coordinate;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.utility.Subject;

public class RemoteView extends Subject<PlayerChoice> implements Observer<Model>{

    private Connection connection;
    private Player player;
    State currentState;
    Model model;

    private class MessageReceiver implements Observer<String> {

        @Override
        public void update(String message) {
            try{
                String[] inputs = message.split(" ");
                handleMove(inputs);
            }catch(IllegalArgumentException e){

            }
        }
    }

    public RemoteView(Connection connection) {
        this.connection = connection;
        connection.addObserver(new MessageReceiver());
    }

    public String getPlayerID(){
        return connection.getID();
    }

    protected void handleMove(String[] inputs) {
        try{
            checkInput(inputs);
        }
        catch (Error e){

        }
        if (model.getCurrentState() instanceof GameStart){
            notify(new SetUpChoice(inputs, connection.getID(),this));
        }
        else notify(new GameChoice(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]),connection.getID(), this));
    }

    private void checkInput(String[] inputs) throws Error{

    }
    public void showMessage(String message){

    }
    public void showError(String error){
        showMessage(error);
        //something else
    }

    protected void showModel(Model model) {
        //connection.send(model.getOutcome(getPlayer()).printOutcome() + "\tMake your move");
    }

    @Override
    public void update(Model model) {
        this.currentState = model.getCurrentState();
        if (currentState instanceof GameStart){
            removeObserver(((GameStart)currentState).getOldController());
            addObserver(((GameStart)currentState).getNewController());
        }
    }
}