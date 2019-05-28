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

    public void explode(boolean generateDiamonds) throws Exception
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
                        if(generateDiamonds)
                        {
                            this.model.updateEntity(getPositionX() + pos[0], getPositionY() + pos[1], new Diamond(this.model, 0,0));
                        }
                        else{
                            this.model.updateEntity(getPositionX() + pos[0], getPositionY() + pos[1], null);
                        }
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

    public abstract void move(int x, int y) throws Exception;

    public abstract void die() throws Exception;
}
