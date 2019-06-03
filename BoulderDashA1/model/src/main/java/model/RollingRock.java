package model;

/**
 * This class represents the rolling block entity.
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
class RollingRock extends SlidingBlock {

    /**
     * Instantiates a new RollingRock.
     *
     * @param model associate the model
     * @param x sets the X position
     * @param y sets the Y position
     */
    RollingRock(final Model model, final int x, final int y)
    {
        super(model,x,y,EntityType.ROLLINGROCK);
    }

    /**
     * Try to move the entity if the player pushes it from the side.
     *
     * @param direction sets the direction you try to pushed it
     * @return true if the entity move is a success and false if not
     * @throws Exception when the given positions are out of the world
     */
    boolean moved(final Direction direction) throws Exception
    {
        int antX = getPositionX();
        int antY = getPositionY();

        if(direction==Direction.RIGHT)
        {
           if ( getRelativeEntity(1,0) == null)
           {
               this.model.updateEntity(getPositionX()+1,getPositionY(),this);
               this.model.updateEntity(antX,antY,null);
               return true;
           }
           return false;
        }else if(direction==Direction.LEFT)
        {
            if(getRelativeEntity(-1,0) == null)
            {
                this.model.updateEntity(getPositionX()-1,getPositionY(),this);
                this.model.updateEntity(antX,antY,null);
                return true;
            }
            return false;
        }
        return false;
    }
}

