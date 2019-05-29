package model;

public class Diamond extends SlidingBlock {
    /**
     *Instantiates a new Diamond Block
     * @param model is a call from model class
     * @param x it is the length position
     * @param y it is the height position
     */
    public Diamond(Model model,int x, int y)
    {
        super(model,x,y,EntityType.DIAMOND);
    }
}
