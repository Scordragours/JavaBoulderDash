package model;
/**
 * The Entity class
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */

public abstract class Entity {
   /** The model*/
    protected Model model;
    /** The X position */
    private int positionX;
    /** The Y position */
    private int positionY;
    /** The type of entity */
    private EntityType type;
    /**
     * This the entity constructor
     * @param model is a call from model class
     * @param positionX it is the length position
     * @param positionY it is the height position
     * @param type it is the type of entity
     */
    public Entity(Model model,int positionX, int positionY, EntityType type)
    {
        this.model = model;
        this.positionY = positionY;
        this.positionX = positionX;
        this.type = type;
    }
    /**
     *
     * @return Returns an instance of type int which corresponds to the length of the entity
     */
    public int getPositionX()
    {
        return this.positionX;
    }

    /**
     * Allows to add a position x to the entity
     * @param x it is the length position
     * @throws Exception if position values is negative
     */
    public void setPositionX(int x) throws Exception
    {
        if(x<0)
        {
            throw new Exception("The X position cannot be negative");
        }else{
            this.positionX = x;
        }

    }
    /**
     *
     * @return Returns an instance of type int which corresponds to the height of the entity
     */
    public int getPositionY()
    {
        return positionY;
    }
    /**
     * Allows to add update a position y to the entity
     * @param y it is the height position
     * @throws Exception if position values is negative
     */

    public void setPositionY(int y) throws Exception
    {
        if(y<0)
        {
            throw new Exception("The Y position cannot be negative");
        }else{
            this.positionY = y;
        }

    }

    /**
     *
     * @return Returns an instance of type int which corresponds to the type of the entity
     */
    public EntityType getType()
    {
            return type;
    }
    /**
     *
     * @param x it is length position of entity
     * @param y it is height position of entity
     * @return relativePosition around the entity
     */
    public Entity getRelativeEntity(int x, int y)
    {
        return this.model.getWorld()[positionY+y][positionX+x];
    }

}
