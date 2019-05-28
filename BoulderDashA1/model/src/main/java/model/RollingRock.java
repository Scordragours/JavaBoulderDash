package model;

public class RollingRock extends SlidingBlock {
    protected Player player;
    protected Direction direction;

    public RollingRock(Model model,int x, int y)
    {
        super(model,x,y,EntityType.ROLLINGROCK);
    }

    public void moved(Direction direction) throws Exception
    {
        int antX = getPositionX();
        int antY = getPositionY();

        if(direction==Direction.RIGHT)
        {

           if ( getRelativeEntity(getPositionX()+1,getPositionY()) == null)
           {
               this.model.updateEntity(getPositionX()+1,getPositionY(),this);
               this.model.updateEntity(antX,antY,null);
           }
        }else if(direction==Direction.LEFT)
        {
            if(getRelativeEntity(getPositionX()-1,getPositionY())==null)
            {
                this.model.updateEntity(getPositionX()-1,getPositionY(),this);
                this.model.updateEntity(antX,antY,null);

            }
        }
    }
}

