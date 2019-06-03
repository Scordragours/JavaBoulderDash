package model;

/**
 * This class represents the diamond entity.
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
class Diamond extends SlidingBlock {

    /**
     * Instantiates a new Diamond.
     *
     * @param model associate the model
     * @param x sets the X position
     * @param y sets the Y position
     */
    Diamond(final Model model, final int x, final int y)
    {
        super(model,x,y,EntityType.DIAMOND);
    }
}
