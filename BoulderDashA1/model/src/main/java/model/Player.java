package model;

/**
 * Player class
 * @author  DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class Player extends Character {
    /** To allows you to tell if the player is alive or not  */
    private boolean alive;


    /**
     * The player constructor
     * @param model the model
     * @param x the X position
     * @param y the Y position
     */
    public Player(Model model,int x, int y)
    {
        super(model,x,y,EntityType.PLAYER);
        this.alive = true;
    }

    /**
     * Used to move the player
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
            this.model.setRemainingDiamonds(this.model.getRemainingDiamonds()-1);
        }
    }

    /**
     * Used to kills the player
     */
    public void die()
    {
        this.alive = false;
    }

    /**
     * Used to find out if he's alive
     * @return if is alive or not
     */
    public boolean isAlive()
    {
        return this.alive;
    }

}
