package model;

public abstract class Entity {

    protected Model model;
    private int positionX;
    private int positionY;
    private EntityType type;

    public Entity(int x, int y, EntityType type)
    {
        positionY = y;
        positionX = x;
    }

    public int getPositionX()
    {
        return positionX;
    }

    public void setPositionX(int x)
    {
        this.positionX = x;
    }

    public int getPositionY()
    {
        return positionY;
    }

    public void setPositionY(int y)
    {
        this.positionY = y;
    }


    public EntityType getType()
    {
        return null;
    }

    public Entity getRelativeEntity(int posX, int posY)
    {
        return null;
    }

}
