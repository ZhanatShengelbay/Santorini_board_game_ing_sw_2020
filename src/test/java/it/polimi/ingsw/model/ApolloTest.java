package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.Build;
import it.polimi.ingsw.model.State.Choice;
import it.polimi.ingsw.model.State.Move;
import it.polimi.ingsw.model.State.Select;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ApolloTest {
    Model model;
    Player apollo;
    Player opponent;

    @Before
    void testSetUpGrid(){
        List<Worker> workers = new ArrayList<~>();
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();
        workers.add(worker1);
        workers.add(worker2);
        apollo = new Apollo(apollo, "apollo");
        List<Worker> workers1 = new ArrayList<>();
        Worker wrk1 = new Worker();
        Worker wrk2 = new Worker();
        workers.add(wrk1);
        workers.add(wrk2);
        opponent = new Player() {

        }


    }


}