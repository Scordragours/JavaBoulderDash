package model;

/**
 * Character class
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public abstract class Character extends Entity {
    /** This attribute is composed of every position possible */
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
     * Constructor of Character
     * @param model recovers the model
     * @param x recovers the X position
     * @param y recovers the Y position
     * @param type recovers the type of block
     */
    Character(final Model model, final int x, final int y, final EntityType type)
    {
        super(model,x,y,type);
    }

    /**
     * If the entity explode
     * @param generateDiamonds the boolean allows
     * @throws Exception if position values is negative
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
     * The movement of player or enemy
     * @param x recovers the X position
     * @param y recovers the Y position
     * @throws Exception if position values is negative
     */
    public abstract void move(final int x, final int y) throws Exception;

    /**
     * Enemy or player die
     * @throws Exception if position values is negative
     */
    public abstract void die() throws Exception;
}
