package model;

/**
 * The Wall class.
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class Wall extends Entity {
    /**
     * Instantiates a new Wall.
     *
     * @param model associate the model
     * @param x sets the X position
     * @param y sets the Y position
     * @param type sets the type of the wall
     */
    Wall(final Model model, final int x, final int y, final EntityType type)
    {
        super(model,x,y,type);
    }

}
