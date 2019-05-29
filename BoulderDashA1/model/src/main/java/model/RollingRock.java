package model;

/**
 * The RollingRock class
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class RollingRock extends SlidingBlock {

    RollingRock(final Model model, final int x, final int y)
    {
        super(model,x,y,EntityType.ROLLINGROCK);
    }

    /**
     * movement of the block by the player
     * @param direction It is a list that contains the 4 possible directions
     * @return a boolean to know if the RollingBlock is moved by the player
     * @throws Exception if the position are negative
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

