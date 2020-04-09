package it.polimi.ingsw.utility;

/**
 * Class describes how the Coordinate is used
 * @author CG51
 * @version 0.1
 */

public class Coordinate {
    /**
     * Instance variables, x - abscissa, y - ordinate, both hold integer values
     */
    int x;
    int y;

    /**
     * Constructor is created to set entered x and y integer values to respectively first and second coordinates of a point in coordinate system
     * @param x integer value of abscissa
     * @param y integer value of ordinate
     */
    public Coordinate(int x, int y) {

        this.x = x;
        this.y = y;
    }

    /**
     * getter method to access the abscissa
     * @return x integer value of abscissa
     */
    public int getX() {
        return x;
    }

    /**
     * setter method to update the abscissa
     * @param x integer value to be set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter method to access the ordinate
     * @return y integer value of the ordinate
     */
    public int getY(){
            return y;
    }

    /**
     * setter method to update ordinate value
     * @param y integer value of the ordinate to be set
     */
    public void setY( int y){
            this.y = y;
    }

    /**
     *
     * @param cardinal
     * @return
     */
    public Coordinate shift(Cardinal cardinal){
            int x = this.getX() + cardinal.getX();
            int y = this.getY() + cardinal.getY();
            return new Coordinate(x, y);
    }

    /**
     * method is overridden to verify if the object is instance of the Coordinate
     * @param obj to be ckecked
     * @return current coordinate otherwise false
     */
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
