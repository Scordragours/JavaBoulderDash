package model;

/**
 * Player class
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */

public class Player extends Character {
    /** allows you to tell if the player is alive or not */
    private boolean alive;

    /**
     * Player Constructor
     * @param model The model
     * @param x The X position
     * @param y The Y position
     */
    public Player(Model model,int x, int y)
    {
        super(model,x,y,EntityType.PLAYER);
        this.alive = true;
    }

    /**
     * Player move
     * @param x recovers the X position
     * @param y recovers the Y position
     * @throws Exception
     */
    public void move(int x, int y) throws Exception
    {
        int antX = getPositionX();
        int antY = getPositionY();
        if(getRelativeEntity(x,y) == null || getRelativeEntity(x,y).getType() == EntityType.DIRT)
        {
            this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
            this.model.updateEntity(antX, antY, null);

        }
        else if(getRelativeEntity(x,y) != null && getRelativeEntity(x,y).getType() == EntityType.ROLLINGROCK)
        {
            if(x < 0)
            {
                if(((RollingRock)getRelativeEntity(x,y)).moved(Direction.LEFT))
                {
                    this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
                    this.model.updateEntity(antX, antY, null);

                }
            }
            else if (x > 0)
            {
                if(((RollingRock)getRelativeEntity(x,y)).moved(Direction.RIGHT))
                {
                    this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
                    this.model.updateEntity(antX, antY, null);

                }
            }
        }
        else if(getRelativeEntity(x,y) != null && getRelativeEntity(x,y).getType() == EntityType.EXIT && ((Exit)getRelativeEntity(x,y)).isOpen())
        {
            this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
            this.model.updateEntity(antX, antY, null);
            this.model.winned();
        }
        else if(getRelativeEntity(x,y) != null && getRelativeEntity(x,y).getType() == EntityType.DIAMOND)
        {
            this.model.updateEntity(getPositionX()+x, getPositionY()+y, this);
            this.model.updateEntity(antX, antY, null);
            this.model.decrementRemainingDiamonds();
        }
    }

    /**
     * Player Die
     */
    public void die()
    {
        this.alive = false;
    }

    /**
     * Check if player is steel alive
     * @return
     */
    public boolean isAlive()
    {
        return this.alive;
    }

}
