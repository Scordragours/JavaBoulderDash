package model;

public abstract class Block extends Entity {

    private boolean destructible;

    public Block(int x, int y, EntityType type, boolean destructible)
    {
        super(x,y,type);
    }

    public boolean isDestructible()
    {
        return true;
    }
}
