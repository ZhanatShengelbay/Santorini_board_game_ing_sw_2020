package it.polimi.ingsw.model;

import java.io.Serializable;

/**
 * Enum class TypeBlock represents building blocks with indicated levels.
 * Blocks are buildable in ascending order, 1 at a time (special cases are specified in god powers)
 * The building with level 4 is considered as a complete tower.
 *  @author CG51
 *  @version 0.1
 */
public enum TypeBlock implements Serializable {
    FLOOR (0),
    FIRST(1),
    SECOND(2),
    THIRD(3),
    DOME(4);

    private static final long serialVersionUID = 14L;


    /**
     * level attribute of type int
     */
    private int level;

    /**
     * constructor to assign the level attribute into constants
     * @param level
     */
    TypeBlock(int level) {
        this.level=level;
    }






}
