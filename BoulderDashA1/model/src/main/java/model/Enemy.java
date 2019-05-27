package model;

public abstract class Enemy extends Character{

    private Direction lastDirection;

    public Enemy(Model model,int x, int y, EntityType type)
    {
        super(model,x,y,type);
        lastDirection = Direction.UP;
    }

    public void pathFinder()
    {
        switch (lastDirection){
            case UP:
                if(getRelativeEntity(1,0) == null)
                {
                    move(getPositionX()+1,getPositionY());
                    this.lastDirection = Direction.RIGHT;
                }
                else if(getRelativeEntity(0,-1) == null)
                {
                    move(getPositionX(),getPositionY()-1);
                    this.lastDirection = Direction.UP;
                }
                else if(getRelativeEntity(-1,0) == null)
                {
                    move(getPositionX()-1,getPositionY());
                    this.lastDirection = Direction.LEFT;
                }
                else if(getRelativeEntity(0,1) == null)
                {
                    move(getPositionX(),getPositionY()+1);
                    this.lastDirection = Direction.DOWN;
                }
                break;
            case DOWN:
                if(getRelativeEntity(-1,0) == null)
                {
                    move(getPositionX()-1,getPositionY());
                    this.lastDirection = Direction.LEFT;
                }
                else if(getRelativeEntity(0,1) == null)
                {
                    move(getPositionX(),getPositionY()+1);
                    this.lastDirection = Direction.DOWN;
                }
                else if(getRelativeEntity(1,0) == null)
                {
                    move(getPositionX()+1,getPositionY());
                    this.lastDirection = Direction.RIGHT;
                }
                else if(getRelativeEntity(0,-1) == null)
                {
                    move(getPositionX(),getPositionY()-1);
                    this.lastDirection = Direction.UP;
                }
                break;
            case LEFT:
                if(getRelativeEntity(0,-1) == null)
                {
                    move(getPositionX(),getPositionY()-1);
                    this.lastDirection = Direction.UP;
                }
                else if(getRelativeEntity(-1,0) == null)
                {
                    move(getPositionX()-1,getPositionY());
                    this.lastDirection = Direction.LEFT;
                }
                else if(getRelativeEntity(0,1) == null)
                {
                    move(getPositionX(),getPositionY()+1);
                    this.lastDirection = Direction.DOWN;
                }
                else if(getRelativeEntity(1,0) == null)
                {
                    move(getPositionX()+1,getPositionY());
                    this.lastDirection = Direction.RIGHT;
                }
                break;
            case RIGHT:
                if(getRelativeEntity(0,1) == null)
                {
                    move(getPositionX(),getPositionY()+1);
                    this.lastDirection = Direction.DOWN;
                }
                else if(getRelativeEntity(1,0) == null)
                {
                    move(getPositionX()+1,getPositionY());
                    this.lastDirection = Direction.RIGHT;
                }
                else if(getRelativeEntity(0,-1) == null)
                {
                    move(getPositionX(),getPositionY()-1);
                    this.lastDirection = Direction.UP;
                }
                else if(getRelativeEntity(-1,0) == null)
                {
                    move(getPositionX()-1,getPositionY());
                    this.lastDirection = Direction.LEFT;
                }
                break;
        }
    }

}
