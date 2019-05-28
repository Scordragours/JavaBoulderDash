package model;


import contract.IModel;

public class Player extends Character {

    private boolean alive;
    private float lastMove;
    public Player(Model model,int x, int y)
    {
        super(model,x,y,EntityType.PLAYER);
        this.alive = true;
    }

    public void move(int x, int y) throws Exception
    {
        int antX = getPositionX();
        int antY = getPositionY();
        if(getRelativeEntity(x,y) == null || getRelativeEntity(x,y).getType() == EntityType.DIRT)
        {
            this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
            this.model.updateEntity(antX, antY, null);
            this.lastMove = System.currentTimeMillis();
        }
        else if(getRelativeEntity(x,y) != null && getRelativeEntity(x,y).getType() == EntityType.ROLLINGROCK)
        {
            if(x < 0)
            {
                if(((RollingRock)getRelativeEntity(x,y)).moved(Direction.LEFT))
                {
                    this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
                    this.model.updateEntity(antX, antY, null);
                    this.lastMove = System.currentTimeMillis();
                }
            }
            else if (x > 0)
            {
                if(((RollingRock)getRelativeEntity(x,y)).moved(Direction.RIGHT))
                {
                    this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
                    this.model.updateEntity(antX, antY, null);
                    this.lastMove = System.currentTimeMillis();
                }
            }
        }
        else if(getRelativeEntity(x,y) != null && getRelativeEntity(x,y).getType() == EntityType.EXIT && ((Exit)getRelativeEntity(x,y)).isOpen())
        {
            this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
            this.model.updateEntity(antX, antY, null);
            this.lastMove = System.currentTimeMillis();
            this.model.winned();
        }
    }

    public void die()
    {
        this.alive = false;
    }
    public boolean isAlive()
    {
        return this.alive;
    }
    public float getLastMove()
    {
        return this.lastMove;
    }
}
