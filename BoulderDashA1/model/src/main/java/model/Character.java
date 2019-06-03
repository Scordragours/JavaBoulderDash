package model;

/**
 * This class represents the different characters present in the game and the behavior they can adopt.
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public abstract class Character extends Entity {

    /**
     * Instantiates a new Character.
     *
     * @param model associate the model
     * @param x sets the X position
     * @param y sets the Y position
     * @param type sets the type of the character
     */
    Character(final Model model, final int x, final int y, final EntityType type)
    {
        super(model,x,y,type);
    }

    /**
     * Causes the entity to explode in a one block radius.
     * Can generate diamonds.
     * If the player is in the radius, causes its death.
     *
     * @param generateDiamonds true to generate diamonds, false otherwise
     * @throws Exception when the given positions are out of the world
     */
    void explode(final boolean generateDiamonds) throws Exception
    {
        final int[][] POSITIONS = {
                {0,0},
                {0,1},
                {1,1},
                {1,0},
                {-1,1},
                {-1,0},
                {-1,-1},
                {0,-1},
                {1,-1}
        };

        for(int[] pos : POSITIONS)
        {
            if(getRelativeEntity(pos[0], pos[1]) != null)
            {
                if(getRelativeEntity(pos[0], pos[1]).getType() != EntityType.OUTLINE && getRelativeEntity(pos[0], pos[1]).getType() != EntityType.EXIT)
                {
                    if(!generateDiamonds)
                    {
                        this.model.addExplosion(new int[]{getPositionX()+pos[0],getPositionY()+pos[1]});
                    }

                    if(getRelativeEntity(pos[0], pos[1]).getType() != EntityType.PLAYER)
                    {
                        if(generateDiamonds)
                        {
                            this.model.updateEntity(getPositionX() + pos[0], getPositionY() + pos[1], new Diamond(this.model, 0,0));
                        }
                        else{
                            this.model.updateEntity(getPositionX() + pos[0], getPositionY() + pos[1], null);
                        }
                    }
                    else
                    {
                        this.model.getPlayer().die(true);
                    }
                }
            }else
            {
                if(!generateDiamonds)
                {
                    this.model.addExplosion(new int[]{getPositionX()+pos[0],getPositionY()+pos[1]});
                }
                else
                {
                    this.model.updateEntity(getPositionX() + pos[0], getPositionY() + pos[1], new Diamond(this.model, 0,0));
                }
            }

        }
    }

    /**
     * The move method. Need to be redefined.
     *
     * @param x sets the X position
     * @param y sets the Y position
     * @throws Exception when the given positions are out of the world
     */
    public abstract void move(final int x, final int y) throws Exception;

    /**
     * The die method. Need to be redefined.
     *
     * @param option true to active option, false so as not to
     * @throws Exception when the given positions are out of the world
     */
    public abstract void die(boolean option) throws Exception;
}
