package model;


public abstract class Entity {

    protected Model model;
    private int positionX;
    private int positionY;
    private EntityType type;

    public Entity(Model model,int positionX, int positionY, EntityType type)
    {
        this.model = model;
        this.positionY = positionY;
        this.positionX = positionX;
        this.type = type;
    }

    public int getPositionX()
    {
        return this.positionX;
    }

    public void setPositionX(int x) throws Exception
    {
        if(x<0)
        {
            throw new Exception("The X position cannot be negative");
        }else{
            this.positionX = x;
        }

    }

    public int getPositionY()
    {
        return positionY;
    }

    public void setPositionY(int y) throws Exception
    {
        if(y<0)
        {
            throw new Exception("The Y position cannot be negative");
        }else{
            this.positionY = y;
        }

    }


    public EntityType getType()
    {
        return type;
    }

    public Entity getRelativeEntity(int x, int y)
    {
        return this.model.getWorld()[positionX+x][positionY+y];
    }

}
