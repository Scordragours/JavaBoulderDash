package model;

public abstract class Enemy extends Character{

    private Direction lastDirection;

    public Enemy(Model model,int x, int y, EntityType type)
    {
        super(model,x,y,type);
        lastDirection = Direction.UP;
    }

    public void move(int x, int y) throws Exception
    {
        if(getRelativeEntity(x,y) == null)
        {
            int antX = getPositionX();
            int antY = getPositionY();
            this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
            this.model.updateEntity(antX, antY, null);
        }

        for(int[] pos : this.POSITIONS)
            if(this.getRelativeEntity(pos[0], pos[1]) != null && this.getRelativeEntity(pos[0], pos[1]).getType() == EntityType.PLAYER)
                this.die();
    }

    public void pathFinder() throws Exception
    {
        switch (lastDirection){
            case UP:
                if(getRelativeEntity(1,0) == null)
                {
                    move(1,0);
                    this.lastDirection = Direction.RIGHT;
                }
                else if(getRelativeEntity(0,-1) == null)
                {
                    move(0,-1);
                    this.lastDirection = Direction.UP;
                }
                else if(getRelativeEntity(-1,0) == null)
                {
                    move(-1,0);
                    this.lastDirection = Direction.LEFT;
                }
                else if(getRelativeEntity(0,1) == null)
                {
                    move(0,1);
                    this.lastDirection = Direction.DOWN;
                }
                break;
            case DOWN:
                if(getRelativeEntity(-1,0) == null)
                {
                    move(-1,0);
                    this.lastDirection = Direction.LEFT;
                }
                else if(getRelativeEntity(0,1) == null)
                {
                    move(0,1);
                    this.lastDirection = Direction.DOWN;
                }
                else if(getRelativeEntity(1,0) == null)
                {
                    move(1,0);
                    this.lastDirection = Direction.RIGHT;
                }
                else if(getRelativeEntity(0,-1) == null)
                {
                    move(0,-1);
                    this.lastDirection = Direction.UP;
                }
                break;
            case LEFT:
                if(getRelativeEntity(0,-1) == null)
                {
                    move(0,-1);
                    this.lastDirection = Direction.UP;
                }
                else if(getRelativeEntity(-1,0) == null)
                {
                    move(-1,0);
                    this.lastDirection = Direction.LEFT;
                }
                else if(getRelativeEntity(0,1) == null)
                {
                    move(0,1);
                    this.lastDirection = Direction.DOWN;
                }
                else if(getRelativeEntity(1,0) == null)
                {
                    move(1,0);
                    this.lastDirection = Direction.RIGHT;
                }
                break;
            case RIGHT:
                if(getRelativeEntity(0,1) == null)
                {
                    move(0,1);
                    this.lastDirection = Direction.DOWN;
                }
                else if(getRelativeEntity(1,0) == null)
                {
                    move(1,0);
                    this.lastDirection = Direction.RIGHT;
                }
                else if(getRelativeEntity(0,-1) == null)
                {
                    move(0,-1);
                    this.lastDirection = Direction.UP;
                }
                else if(getRelativeEntity(-1,0) == null)
                {
                    move(-1,0);
                    this.lastDirection = Direction.LEFT;
                }
                break;
        }
    }

}
