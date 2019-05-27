package model;

public class Player extends Character {

    private boolean alive;
    private float lastMove;
    public Player(Model model,int x, int y)
    {
        super(model,x,y,EntityType.PLAYER);
        this.alive = true;
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
        return 3.55457f;
    }
}
