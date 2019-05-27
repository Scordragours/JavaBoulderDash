package model;

public abstract class Character extends Entity {

    public Character(Model model,int x, int y, EntityType type)
    {
        super(model,x,y,type);
    }

    public void explode()
    {

    }

    public abstract void move(int y, int x);

    public abstract void die();
}
