package model;

/**
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public abstract class SlidingBlock extends Entity {



    public SlidingBlock(Model model,int x, int y, EntityType type)
    {
        super(model,x,y,type);
    }

    /**
     * Method used when a block falls
     * @throws Exception When the position values are negatives
     */

    public void fall() throws Exception
    {
        int antX = getPositionX();
        int antY = getPositionY();




        this.model.updateEntity(getPositionX(), getPositionY() + 1,this);
        this.model.updateEntity(antX, antY,null);

        if(getRelativeEntity(0,1) != null && getRelativeEntity(0,1).getType() == EntityType.PLAYER)//this.model.getPlayer().getPositionX() == this.getPositionX()&& this.model.getPlayer().getPositionY()-1 == this.getPositionY())
        {
            this.model.getPlayer().explode(false);
        }

    }

    /**
     * Method used when a block slides on another one
     * @param isDefault A boolean which allows to know if the block slide to the left or the right
     * @throws Exception When the position values are negatives
     */
    public void slide(boolean isDefault) throws Exception
    {
        int antX = getPositionX();
        int antY = getPositionY();
        if(isDefault)
        {

            this.model.updateEntity(getPositionX() - 1, getPositionY(),this);
            this.model.updateEntity(antX, antY,null);


        }else
        {
            this.model.updateEntity(getPositionX() + 1, getPositionY(),this);
            this.model.updateEntity(antX, antY,null);

        }


    }

    /**
     * Method used to determine if the block will slip or fall
     * @throws Exception When the position values are negatives
     */
    public void pathFinder() throws Exception
    {
        if(getRelativeEntity(0, -1) == null)
        {
            fall();
        }
        else if(getRelativeEntity(-1, 1) == null && getRelativeEntity(-1,0)==null && getRelativeEntity(0,1) instanceof  SlidingBlock)
        {
            slide(true);
        }
        else if(getRelativeEntity(1, 1) == null && getRelativeEntity(1,0)==null && getRelativeEntity(0,1) instanceof  SlidingBlock)
        {
            slide(false);
        }
    }
}
