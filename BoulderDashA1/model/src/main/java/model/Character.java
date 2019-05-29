package model;

/**
 * The Character class.
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public abstract class Character extends Entity {

    /** Every position possible in a one block radius. */
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
        for(int[] pos : this.POSITIONS)
        {
            if(getRelativeEntity(pos[0], pos[1]) != null)
            {
                if(getRelativeEntity(pos[0], pos[1]).getType() != EntityType.OUTLINE)
                {
                    this.model.addExplosion(new int[]{getPositionX()+pos[0],getPositionY()+pos[1]});
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
                        this.model.getPlayer().die();
                    }
                }
            }else
            {
                this.model.addExplosion(new int[]{getPositionX()+pos[0],getPositionY()+pos[1]});
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
     * @throws Exception when the given positions are out of the world
     */
    public abstract void die() throws Exception;
}
