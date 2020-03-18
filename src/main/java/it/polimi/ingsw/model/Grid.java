package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Cardinal;

import java.util.ArrayList;
import java.util.List;

class Grid {

    public static final int N_ROWS = 5;
    public static final int N_COLS = 5;

    private Tile[][] grid = new Tile[N_ROWS][N_COLS];

    public Grid() {
        for(int i=0;i<N_ROWS;i++)
            for(int j=0;j<N_ROWS;j++)
                grid[i][j]=new Tile(TypeBlock.FLOOR,i,j);
        }


     public Tile getWorkerTile(Worker work){



    }



    // dato il tile di partenza e la direzione dell'azione, restituisce il tile destinazione
    public Tile nextTile(Tile from, Cardinal direction){
                int x = from.getX() + direction.getX();
                int y = from.getY() + direction.getY();
                //check coordinate
                return grid[x][y];
    }



}
