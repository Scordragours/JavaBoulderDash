package model;

public abstract class Character extends Entity {

    public Character(int x, int y, EntityType type)
    {
        super(x,y,type);
    }

    public void explode()
    {

    }

    public abstract void move();

    public abstract void die();
}
