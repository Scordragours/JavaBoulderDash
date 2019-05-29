package model;

/**
 * The SlidingBlock class.
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public abstract class SlidingBlock extends Entity {

    /** Used when the entity has moved during an update. */
    private boolean hasMove;

    /**
     * Instantiates a new SlidingBlock.
     *
     * @param model associate the model
     * @param x sets the X position
     * @param y sets the Y position
     * @param type sets the type of the sliding block
     */
    SlidingBlock(final Model model, final int x, final int y, final EntityType type)
    {
        super(model,x,y,type);
        this.hasMove = false;
    }

    /**
     * Resets the hasMove attribute to false.
     */
    void resetMove() { this.hasMove = false; }

    /**
     * Causes the entity to fall. If a "Character" type entity is below, causes its death.
     *
     * @throws Exception when the given positions are out of the world
     */
    void fall() throws Exception
    {

        int antX = getPositionX();
        int antY = getPositionY();


        this.model.updateEntity(getPositionX(), getPositionY() + 1,this);
        this.model.updateEntity(antX, antY,null);

        if(getRelativeEntity(0,1) != null && getRelativeEntity(0,1).getType() == EntityType.PLAYER)
        {
            this.model.getPlayer().explode(false);
        }
        if(getRelativeEntity(0,1) != null && (getRelativeEntity(0,1).getType() == EntityType.ENEMYDIAMOND || getRelativeEntity(0,1).getType() == EntityType.ENEMYPOINT))
        {
            ((Enemy)getRelativeEntity(0,1)).die();
        }

        this.hasMove = true;
    }

    /**
     * Slides an entity to the side.
     *
     * @param isDefault true for to slide the entity on the left and false on the right
     * @throws Exception when the given positions are out of the world
     */
    void slide(final boolean isDefault) throws Exception
    {
        int antX = getPositionX();
        int antY = getPositionY();
        if(isDefault)
        {

            this.model.updateEntity(getPositionX() - 1, getPositionY(),this);
            this.model.updateEntity(antX, antY,null);


        }else
        {
            this.model.updateEntity(getPositionX() + 1, getPositionY(),this);
            this.model.updateEntity(antX, antY,null);

        }
        this.hasMove = true;

    }

    /**
     * Allows to check if the entity can slip or fall.
     *
     * @throws Exception when the given positions are out of the world
     */
    void pathFinder() throws Exception
    {
        if(!this.hasMove) {
            if (getRelativeEntity(0, 1) == null) {
                fall();
            } else if (getRelativeEntity(-1, 1) == null && getRelativeEntity(-1, 0) == null && getRelativeEntity(0, 1) instanceof SlidingBlock) {
                slide(true);
            } else if (getRelativeEntity(1, 1) == null && getRelativeEntity(1, 0) == null && getRelativeEntity(0, 1) instanceof SlidingBlock) {
                slide(false);
            }
        }
    }
}
