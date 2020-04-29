package it.polimi.ingsw.view;

import it.polimi.ingsw.model.EnumDivinity;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.model.playerChoice.GameChoice;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.utility.Subject;

public class RemoteView extends Subject<PlayerChoice> implements Observer<Model>{

    private Connection connection;
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

    public RemoteView(Connection connection, Model model) {
        this.model = model;
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
            String[] stdInputs = new String[inputs.length];
            for(int i=0; i<inputs.length; i++){
                stdInputs[i] = inputs[i].substring(0, 1).toUpperCase() + inputs[i].substring(1).toLowerCase();
            }
            notify(new SetUpChoice(stdInputs, connection.getID(),this));
        }
        else {
            notify(new GameChoice(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]),connection.getID(), this));
        }
    }

    // Better to substitute Strings with errors objects, even custom ones
    private void checkInput(String[] inputs) throws Error{
        boolean godFound;
        if (model.getCurrentState() instanceof GameStart){
            for (String input : inputs){
                godFound = false;
                for(EnumDivinity e : EnumDivinity.values()){
                    if (input.toUpperCase().compareTo(e.toString()) == 0) {
                        godFound = true;
                    }
                }
                if(!godFound){
                    showError("Not a God");
                    throw new Error();
                }
            }
        }
        else if(model.getCurrentState() instanceof Power){

        }
        else{
            if(inputs.length > 2){
                showError("Expected 2 arguments, received " + inputs.length);
                throw new Error();
            }
            else {
                if(Integer.parseInt(inputs[0]) > 5 || Integer.parseInt(inputs[0]) < 0 || Integer.parseInt(inputs[1]) > 5 || Integer.parseInt(inputs[1]) < 0){
                    showError("Coordinates not valid");
                    throw new Error();
                }
            }
        }
    }

    public void showMessage(String message){
        connection.send(message);
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
    }
}