package model;

/**
 * Exit class
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class Exit extends Entity {
    /** Whether the output is visible or not */
    private boolean open;

    /**
     * Instantiates a new Exit block
     * @param model recovers the model
     * @param x it is the length position
     * @param y it is the height position
     */
    public Exit(Model model,int x, int y)
    {
        super(model,x, y,EntityType.EXIT);
    }

    /**
     *
     * @return Returns an instance of type boolean which corresponds if the exit is true or false.
     */
    public boolean isOpen()
    {
        return this.open;
    }

    /**
     * This method is used to activate the exit.
     */
    public void openned() { this.open = true; }

}
