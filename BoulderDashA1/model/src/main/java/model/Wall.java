package model;

public class Wall extends Entity {
    private boolean destructible;
    public Wall(Model model,int x, int y, EntityType type)
    {
        super(model,x,y,type);
    }

    public boolean isDestructible()
    {
        return true;
    }
}
