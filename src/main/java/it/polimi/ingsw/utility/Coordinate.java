package it.polimi.ingsw.utility;

public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY(){
            return y;
    }

    public void setY( int y){
            this.y = y;
    }

    public Coordinate shift(Cardinal cardinal){
            int x = this.getX() + cardinal.getX();
            int y = this.getY() + cardinal.getY();
            return new Coordinate(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate that;
        if(obj instanceof Coordinate){
            that = (Coordinate)obj;
            return this.getX() == that.getX() && this.getY() == that.y;
        }
        else return false;
    }
}
