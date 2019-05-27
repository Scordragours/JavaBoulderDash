package model;

public abstract class SlidingBlock extends Entity {

    public SlidingBlock(Model model,int x, int y, EntityType type)
    {
        super(model,x,y,type);
    }

    public void fall() throws Exception
    {
        Entity entity;
        if(this instanceof Diamond)
        {
            entity = new Diamond(this.model, getPositionX(), getPositionY());
        }else
        {
            entity = new RollingRock(this.model, getPositionX(), getPositionY());
        }
        this.model.updateEntity(getPositionX(), getPositionY() - 1,entity);
    }

    public void slide(boolean isDefault) throws Exception
    {
        Entity entity;
        if(this instanceof Diamond)
        {
            entity = new Diamond(this.model, getPositionX(), getPositionY());
        }else
        {
            entity = new RollingRock(this.model, getPositionX(), getPositionY());
        }
        if(isDefault)
        {

            this.model.updateEntity(getPositionX() - 1, getPositionY() - 1,entity);
        }else
        {
            this.model.updateEntity(getPositionX() + 1, getPositionY() - 1,entity);
        }


    }
    public void pathFinder() throws Exception
    {
        if(getRelativeEntity(getPositionX(), getPositionY()-1) != null)
        {
            fall();
        }
        else if(getRelativeEntity(getPositionX()-1, getPositionY()-1) != null)
        {
            slide(true);
        }
        else if(getRelativeEntity(getPositionX()+1, getPositionY()-1) != null)
        {
            slide(false);
        }
    }
}
