package it.polimi.ingsw.model;

import it.polimi.ingsw.model.State.*;
import it.polimi.ingsw.utility.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



class AbstractPlayer extends Player{


    public AbstractPlayer( String playerID,Model model) {
        super( playerID, model);
    }


    @Override
    public boolean makePower(Coordinate destination) {
        return false;
    }
}



public class PlayerTest {

    private Model model;

    private Player player;

    private Player opponent;

    boolean extTest=false;

    private Coordinate positionWorker;



    /*public PlayerTest(Player player){
        this.player=player;
        extTest=true;
    }*/

    @Before
    public void setUp(){

        model = new Model();
        if(!extTest)
            player = new AbstractPlayer( "opponent",model);
        model.getPlayers().add(player);
        player.positionWorker(new Coordinate(1,0));
        player.positionWorker(new Coordinate(0,1));
        assertFalse(player.positionWorker(new Coordinate(1,0)));
        assertTrue(model.getGrid().getTile(1,0).getWorker().equals(player.getWorker(0)));
        opponent = new AbstractPlayer( "opponent",model);
        opponent.positionWorker(new Coordinate(1,2));
        model.getGrid().getTile(1,1).levelUp().levelUp();
        model.getGrid().getTile(2,1).levelUp();
        model.setCurrentPlayer(player);



    }

    @Test
    public void simpleGameRound(){
        model.setCurrentState(new Select());
        model.setCurrentPlayer(player);
        assertFalse(player.makeSelection(new Coordinate(1,1)));
        Coordinate selection=new Coordinate(1,0);
        assertTrue(player.makeSelection(selection));
        assertEquals(model.getGrid().getTile(model.getCurrentWorker()),model.getGrid().getTile(selection));
        assertFalse(player.makeMovement(new Coordinate(1,1)));
        assertFalse("impossible to reach,but possible to the other worker",player.makeMovement(new Coordinate(0,2)));
        Coordinate move= new Coordinate(2,1);
        assertTrue(player.makeMovement(move));
        assertEquals(model.getGrid().getTile(model.getCurrentWorker()),model.getGrid().getTile(move));
        Coordinate build= new Coordinate(1,1);
        int height= model.getGrid().getTile(build).getHeight().ordinal();
        assertTrue(player.makeBuild(build));
        assertEquals(height+1,model.getGrid().getTile(build).getHeight().ordinal());
        assertTrue(model.getCurrentState()instanceof End);

    }




    public void testPositionWorker(){
        player.positionWorker(new Coordinate(2, 3));
        assertEquals(model.getGrid().getTile(positionWorker).getWorker(), player.getWorker(0));
        for(int i = 0; i < 5 && i!= positionWorker.getX(); i++){
            for(int j = 0; j < 5 && j!= positionWorker.getY(); j++){
                Coordinate c= new Coordinate(i,j);
                assertNull(model.getGrid().getTile(c).getWorker());
            }
        }
    }
/*
    @Test
    public void testMakeSelection(){
        player.positionWorker(model, positionWorker);
        player.makeSelection(model, positionWorker);
        assertEquals(model.getGrid().getTile(model.getCurrentWorker()).getWorker(), player.getWorker(0));
        assertEquals(model.getCurrentWorker(), positionWorker);
    }

    @Test
    public void testMakeMovement(){
        player.positionWorker(model, posWorker);
        player.makeSelection(model, select);
        Coordinate oldPosition = model.getCurrentWorker();
        player.makeMovement(model, move);
        assertNull(grid.getTile(oldPosition).getWorker());
        assertEquals(grid.getTile(model.getCurrentWorker()).getWorker(), grid.getTile(moveCoordinate).getWorker());
        assertEquals(grid.getTile(move.getChoice()).getWorker(), grid.getTile(moveCoordinate).getWorker());
    }


    @Test
    public void testMakeBuild(){
        player.positionWorker(model, posWorker);
        player.makeSelection(model, select);
        player.makeBuild(model, build);
        assertEquals(TypeBlock.FIRST, grid.getTile(build.getChoice()).getHeight());
    }

    @Test
    public void testBuildMove(){
        player.positionWorker(model, posWorker);
        player.makeSelection(model, select);
        player.makeBuild(model, build);
        player.makeBuild(model, build);
        player.makeBuild(model, build);
        player.makeBuild(model, build);
        move = new Move(new Coordinate(2,4));
        Coordinate oldPosition = model.getCurrentWorker();
        player.makeMovement(model, move);
        assertEquals(grid.getTile(model.getCurrentWorker()).getWorker(), grid.getTile(oldPosition).getWorker());
        assertEquals(TypeBlock.DOME, grid.getTile(build.getChoice()).getHeight());
    }*/
}
