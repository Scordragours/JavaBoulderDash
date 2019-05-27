package model;

public class RollingRock extends SlidingBlock {
    protected Player player;


    public RollingRock(Model model,int x, int y)
    {
        super(model,x,y,EntityType.ROLLINGROCK);
    }

    public void moved() throws Exception
    {

        if( getRelativeEntity(getPositionX()+2,getPositionY()) == null)
        {
            this.model.updateEntity(getPositionX()+1,getPositionY(),this);
        }else if( getRelativeEntity(getPositionX()-2,getPositionY())==null)
        {
            this.model.updateEntity(getPositionX()-1,getPositionY(),this);
        }
    }
}
