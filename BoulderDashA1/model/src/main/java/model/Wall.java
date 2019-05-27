package model;

public class Wall extends Entity {
    private boolean destructible;
    public Wall(Model model,int x, int y, EntityType type, boolean destructible)
    {
        super(model,x,y,type);
        this.destructible = destructible;
    }

    public boolean isDestructible()
    {
        return true;
    }
}
