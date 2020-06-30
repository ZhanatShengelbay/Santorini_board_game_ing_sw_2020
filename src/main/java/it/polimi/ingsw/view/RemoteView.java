package it.polimi.ingsw.view;

import it.polimi.ingsw.model.EnumDivinity;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.ModelView;
import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.model.playerChoice.GameChoice;
import it.polimi.ingsw.model.playerChoice.PlayerChoice;
import it.polimi.ingsw.model.playerChoice.SetUpChoice;
import it.polimi.ingsw.utility.Observer;
import it.polimi.ingsw.utility.Subject;

import java.io.*;

/**
 * Represents the unique client. Placed between the Client and Controller.
 * @author CG51
 * @version 1.1
 */
public class RemoteView extends Subject<PlayerChoice> implements Observer<ModelView>{
    /**
     * attributes
     */
    ByteArrayOutputStream os;
    ObjectOutputStream out;
    private Connection connection;
    //State currentState;
    private Model model;

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

    /**
     *
     * @param connection
     * @param model
     */
    public RemoteView(Connection connection, Model model) {
        this.model = model;
        this.connection = connection;
        connection.addObserver(new MessageReceiver());
        try{
            os = new ByteArrayOutputStream();
            out = new ObjectOutputStream(os);
        }
        catch (IOException e){
            showError("Serialization setup error");
        }
    }

    public String getPlayerID(){
        return connection.getID();
    }

    protected void handleMove(String[] inputs) {
        try{
            checkInput(inputs);
        }
        catch (Error e){
            return;
        }
        catch (Exception ex){
            showError("Coordinates need to be numbers");
            return;
        }
        if (model.getCurrentState() instanceof Power){

            GameChoice choice;
            if(inputs[0].toLowerCase().compareTo("@") == 0) {
                choice = new GameChoice(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]), connection.getID(), this);
                choice.activePower();
            }
            else  choice = new GameChoice(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), connection.getID(), this);
            notify(choice);
        }
        else if (model.getCurrentState() instanceof GameStart){
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
    // include error strings in error object and then show it in it.polimi.ingsw.client?
    private void checkInput(String[] inputs) throws Error, Exception{
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
        else{
            if(inputs.length == 3){
                if(model.getCurrentState() instanceof Power){
                    if(inputs[0].compareTo("@") != 0){
                        showError("Need @ symbol to activate power");
                        throw new Error();
                    }
                    if(!checkInputCoordinates(inputs[1], inputs[2])){
                        showError("Wrong coordinates");
                        throw new Error();
                    }
                }
                else{
                    showError("Wrong number of arguments");
                    throw new Error();
                }
            }
            else if(inputs.length == 2){
                if(!checkInputCoordinates(inputs[0], inputs[1])){
                    showError("Wrong coordinates");
                    throw new Error();
                }
            }
            else{
                showError("Wrong number of arguments");
                throw new Error();
            }
        }
    }

    /**
     * Method is used in CLI mode, throws an exception if invalid coordinates are entered
     * @param c1
     * @param c2
     * @return true or false
     * @throws Exception
     */
    public boolean checkInputCoordinates(String c1, String c2) throws Exception{
        if(Integer.parseInt(c1) > 5 || Integer.parseInt(c1) < 0 || Integer.parseInt(c2) > 5 || Integer.parseInt(c2) < 0) {
            return false;
        }
        else return true;
    }

    /**
     * Sends the message to the connection
     * @param message
     */
    public void showMessage(Object message){
        connection.send(message);
    }

    /**
     * Sends the event to the connection
     * @param event
     */
    public void showEvent(Event event){
        connection.send(event);

    }

    /**
     * displays the error
     * @param error
     */
    public void showError(String error){
        showMessage(error);
        //something else
    }

    protected void showModel(Model model) {
        //connection.send(model.getOutcome(getPlayer()).printOutcome() + "\tMake your move");
    }

    /**
     * is used to make the player aware of the current state
     * @param model
     */
    @Override
    public void update(ModelView model){
        try {
            connection.send(model);
            if (model.getState().equals("select") && model.getCurrentPlayer().equals(getPlayerID()))
                showMessage("Select the worker you want to move\n");
            if (model.getState().equals("positionworkers") && model.getCurrentPlayer().equals(getPlayerID()))
                showMessage("Place your worker\n");
            if (model.getState().equals("win")) {
                if (model.getWinner().compareTo(connection.getID()) == 0) showEvent(Event.WIN);
                else showEvent(Event.LOST);

            }
        }catch (NullPointerException e)
        {
            System.out.println("Null connection\n");
        }
    }
}