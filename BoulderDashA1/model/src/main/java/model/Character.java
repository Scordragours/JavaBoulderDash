package model;

public abstract class Character extends Entity {

    public Character(Model model,int x, int y, EntityType type)
    {
        super(model,x,y,type);
    }

    public void explode()
    {
        int[][] positions = {
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

        for(int[] pos : positions)
        {
            this.model.addExplosion(pos);
        }
    }

    public void move(int y, int x)
    {

    }

    public abstract void die();
}
