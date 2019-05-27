package model;

public class Exit extends Entity {
    private boolean open;
    public Exit(Model model,int x, int y, EntityType type)
    {
        super(model,x, y,type);
    }

    public boolean isOpen()
    {
        return true;
    }

}
