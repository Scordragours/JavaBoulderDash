package model;

public abstract class Character extends Entity {

    protected int[][] positions = {
            {0,0},
            {0,1},
            {1,1},
            {1,0},
            {-1,1},
            {-1,0},
            {-1,-1},
            {0,-1},
            {1,-1}
    };

    public Character(Model model,int x, int y, EntityType type)
    {
        super(model,x,y,type);
    }

    public void explode() throws Exception
    {
        for(int[] pos : this.positions)
        {
            if(getRelativeEntity(pos[0], pos[1]) != null)
            {
                if(getRelativeEntity(pos[0], pos[1]).getType() != EntityType.OUTLINE)
                {
                    this.model.addExplosion(new int[]{getPositionX()+pos[0],getPositionY()+pos[1]});
                    if(getRelativeEntity(pos[0], pos[1]).getType() != EntityType.PLAYER)
                    {
                        this.model.updateEntity(getPositionX() + pos[0], getPositionY() + pos[1], null);
                    }
                    else
                    {
                        this.model.getPlayer().die();
                    }
                }
            }else
            {
                this.model.addExplosion(new int[]{getPositionX()+pos[0],getPositionY()+pos[1]});
            }

        }
    }

    public boolean move(int x, int y) throws Exception
    {
        if(getRelativeEntity(x,y) == null)
        {
            int antX = getPositionX();
            int antY = getPositionY();
            this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
            this.model.updateEntity(antX, antY, null);
            return true;
        }
        return false;
    }

    public abstract void die() throws Exception;
}
