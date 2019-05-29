package model;

/**
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class Dirt extends Entity {

    /**
     * Instantiates a new Dirt Block
     * @param model recovers the model
     * @param x it is the length position
     * @param y it is the height position
     */
    public Dirt(Model model,int x, int y)
    {
        super(model,x,y,EntityType.DIRT);
    }
}
