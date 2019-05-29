package model;

public class Diamond extends SlidingBlock {
    /**
     *This constructor represent the diamond block
     * @param model is a call from model class
     * @param x it is the length position
     * @param y it is the height position
     */
    public Diamond(Model model,int x, int y)
    {
        super(model,x,y,EntityType.DIAMOND);
    }
}
