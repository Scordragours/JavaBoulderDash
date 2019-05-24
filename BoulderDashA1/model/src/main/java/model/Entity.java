package model;

public abstract class Entity {

    protected Model model;
    private int positionX;
    private int positionY;
    private Sprite sprite;
    private EntityType type;

    public Entity(int x, int y, EntityType type)
    {

    }

    public int getPositionX()
    {
        return 0;
    }

    public void setPositionX(int positionX)
    {

    }

    public int getPositionY()
    {
        return 0;
    }

    public void setPositionY(int positionY)
    {

    }

    public Sprite getSprite()
    {
        return null;
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
