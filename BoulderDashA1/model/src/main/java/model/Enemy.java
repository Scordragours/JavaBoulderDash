package model;

public abstract class Enemy extends Character{

    public Enemy(Model model,int x, int y, EntityType type)
    {
        super(model,x,y,type);
    }

    public void move(int x, int y)
    {

    }

    public void pathFinder()
    {

    }

}
