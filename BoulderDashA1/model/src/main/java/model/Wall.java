package model;

/**
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class Wall extends Entity {
    /**
     * Instantiates a new Wall
     * @param model recovers the model
     * @param x recovers the X position
     * @param y recovers the Y position
     * @param type recovers the type of block
     */
    Wall(final Model model, final int x, final int y, final EntityType type)
    {
        super(model,x,y,type);
    }

}
