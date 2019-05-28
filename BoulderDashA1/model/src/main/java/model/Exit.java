package model;

public class Exit extends Entity {
    private boolean open;
    public Exit(Model model,int x, int y)
    {
        super(model,x, y,EntityType.EXIT);
    }

    public boolean isOpen()
    {
        return this.open;
    }

    public void openned() { this.open = true; }

}
