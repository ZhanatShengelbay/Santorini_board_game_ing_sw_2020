package it.polimi.ingsw.model.State;

import java.util.ArrayList;
import java.util.List;

public class SelectGods implements State {

    List<String> gods = new ArrayList<>();

    public List<String> getGods(){
        return gods;
    }

    public void setGod(String god, int index){
        this.gods.set(index, god);
    }
}
