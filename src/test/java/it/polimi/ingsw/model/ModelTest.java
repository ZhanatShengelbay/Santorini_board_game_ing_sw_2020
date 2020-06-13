package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertTrue;

public class ModelTest {
    Model model;
    @Before
    public void setup(){
        model= new Model();
    }
    @Test
    public void createPlayer(){
        for(EnumDivinity d : EnumDivinity.values()){
            model.createPlayer(d.name(),"--"+d.ordinal());

        }

    }
}
