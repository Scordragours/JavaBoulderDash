package model;

/**
 * The Diamond Class
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class Diamond extends SlidingBlock {
    /**
     *Instantiates a new Diamond Block
     * @param model is a call from model class
     * @param x it is the length position
     * @param y it is the height position
     */
    Diamond(final Model model, final int x, final int y)
    {
        super(model,x,y,EntityType.DIAMOND);
    }
}
