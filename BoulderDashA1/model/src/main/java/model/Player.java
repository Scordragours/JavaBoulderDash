package model;

public class Player extends Character {

    private boolean alive;
    private float lastMove;
    public Player(int x, int y, EntityType type)
    {
        super(x,y,type);
    }

    public void move(int x, int y)
    {

    }

    public void die()
    {

    }
    public boolean isAlive()
    {
        return true;
    }
    public float getLastMove()
    {

    }
}
