package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    public static final int N_ROWS = 5;
    public static final int N_COLS = 5;

    Tile[][] grid = new Tile[N_ROWS][N_COLS];

    public Grid() {
        for(int i=0;i<N_ROWS;i++)
            for(int j=0;j<N_ROWS;j++)
                grid[i][j]=new Tile(TypeBlock.FLOOR);
        }
    public Tile getWorkerTile(Worker work){

    }


}
