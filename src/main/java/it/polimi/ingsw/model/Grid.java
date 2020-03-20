package it.polimi.ingsw.model;

import it.polimi.ingsw.utility.Cardinal;
import it.polimi.ingsw.utility.Coordinate;

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
        int x = work.getPosition().getX();
        int y = work.getPosition().getY();
        return grid[x][y];

    }



    // dato il tile di partenza e la direzione dell'azione, restituisce il tile destinazione
    public Tile nextTile(Tile from, Cardinal direction){
                int x = from.getX() + direction.getX();
                int y = from.getY() + direction.getY();
                if( (0<=x && x< N_ROWS )&&( 0<=y && y< N_COLS))
                        return grid[x][y];
                else throw new IllegalArgumentException();
    }

    public boolean validTile(Tile from, Coordinate c){
        for(Cardinal.){

        }
    }




}
