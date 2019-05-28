package model;

public abstract class SlidingBlock extends Entity {

    protected Player player;
    public SlidingBlock(Model model,int x, int y, EntityType type)
    {
        super(model,x,y,type);
    }

    public void fall() throws Exception
    {
        Entity entity;
        int antX = getPositionX();
        int antY = getPositionY();
        if(this instanceof Diamond)
        {
            entity = new Diamond(this.model, getPositionX(), getPositionY());
        }else
        {
            entity = new RollingRock(this.model, getPositionX(), getPositionY());
        }
        //Not sure
        if(getRelativeEntity(getPositionX(),getPositionY()+1) ==null && player.getPositionX() == entity.getPositionX()&& player.getPositionY()-1 == entity.getPositionY())
        {
            player.explode();
        }

        this.model.updateEntity(getPositionX(), getPositionY() - 1,entity);
        this.model.updateEntity(antX, antY - 1,null);

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

            this.model.updateEntity(getPositionX() - 1, getPositionY(),entity);

            fall();
        }else
        {
            this.model.updateEntity(getPositionX() + 1, getPositionY(),entity);

            fall();
        }


    }
    public void pathFinder() throws Exception
    {
        if(getRelativeEntity(getPositionX(), getPositionY()-1) == null)
        {
            fall();
        }
        else if(getRelativeEntity(getPositionX()-1, getPositionY()+1) == null && getRelativeEntity(getPositionX()-1,getPositionY())==null && getRelativeEntity(getPositionX()-1,getPositionY()+1) instanceof  SlidingBlock)
        {
            slide(true);
        }
        else if(getRelativeEntity(getPositionX()+1, getPositionY()-1) == null)
        {
            slide(false);
        }
    }
}
