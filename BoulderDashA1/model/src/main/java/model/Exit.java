package model;

public class Exit extends Entity {
    private boolean open;
    public Exit(int x, int y, EntityType type)
    {
        super(x, y,type);
    }

    public boolean isOpen()
    {
        return true;
    }

}
