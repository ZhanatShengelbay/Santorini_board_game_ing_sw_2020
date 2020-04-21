package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ChecksTest {

    private Model model;

    @Before
    public void gridSetUp(){


        Worker worker1 = new Worker();
        Worker worker2 = new Worker();

        model=new Model();
        model.getGrid().getTile(new Coordinate(2,0)).levelUp(); //1
        model.getGrid().getTile(new Coordinate(2,0)).setWorker(worker1);
        model.getGrid().getTile(new Coordinate(2,1)).levelUp().levelUp().levelUp().levelUp();//dome
        model.getGrid().getTile(new Coordinate(1,1)).levelUp().levelUp().levelUp();//3
        model.getGrid().getTile(new Coordinate(1,0)).levelUp().levelUp();//2
        model.getGrid().getTile(new Coordinate(3,1)).levelUp();//1


    }

    @Test
    public void isRisibleTest(){
        List<Coordinate> result= new Checks(model,new Coordinate(2,0)).isRisible().getResult();
        assertTrue( result.contains(new Coordinate(1,0)));
        assertTrue( result.contains(new Coordinate(3,1)));
        assertTrue( result.contains(new Coordinate(3,0)));
        assertFalse( result.contains(new Coordinate(1,1)));
        assertFalse( result.contains(new Coordinate(2,1)));
        result= new Checks(model,new Coordinate(2,0)).isRisible(2).isNotDome().getResult();
        assertTrue( result.contains(new Coordinate(1,1)));

    }


     @Test
    public void getResultTest(){
         List<Coordinate> result= new Checks(model,new Coordinate(2,-1)).isRisible().getResult();
         assertTrue(result.isEmpty());

     }

}
