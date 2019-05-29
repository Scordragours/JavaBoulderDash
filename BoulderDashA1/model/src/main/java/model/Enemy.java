package model;

/**
 * The Enemy class.
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public abstract class Enemy extends Character{

    /** The last direction of the enemy. */
    private Direction lastDirection;
    /** Used when the entity has moved during an update. */
    private boolean hasMove;

    /**
     * Instantiates a new Enemy.
     *
     * @param model associate the model
     * @param x sets the X position
     * @param y sets the Y position
     * @param type sets the type of the enemy
     */
    public Enemy(final Model model, final int x, final int y, final EntityType type)
    {
        super(model,x,y,type);
        lastDirection = Direction.UP;
        this.hasMove = false;
    }

    /**
     * Resets the hasMove attribute to false.
     */
    void resetMove() { this.hasMove = false; }

    /**
     * Tries to move the enemy to a relative position.
     * If the player is in a range of one block around the enemy, causes its explosion.
     *
     * @param x sets the X relative position
     * @param y sets the Y relative position
     * @throws Exception when the given positions are out of the world
     */
    public void move(final int x, final int y) throws Exception
    {
        if (getRelativeEntity(x, y) == null) {
            int antX = getPositionX();
            int antY = getPositionY();
            this.model.updateEntity(getPositionX() + x, getPositionY() + y, this);
            this.model.updateEntity(antX, antY, null);

            this.hasMove = true;
        }

        for (int[] pos : this.POSITIONS)
            if (this.getRelativeEntity(pos[0], pos[1]) != null && this.getRelativeEntity(pos[0], pos[1]).getType() == EntityType.PLAYER)
                this.die();
    }

    /**
     * Assign a direction to the enemy. Gives it an autonomous behaviour.
     *
     * @throws Exception when the given positions are out of the world
     */
    void pathFinder() throws Exception
    {
        if(!this.hasMove) {
            switch (lastDirection) {
                case UP:
                    if (getRelativeEntity(1, 0) == null) {
                        move(1, 0);
                        this.lastDirection = Direction.RIGHT;
                    } else if (getRelativeEntity(0, -1) == null) {
                        move(0, -1);
                        this.lastDirection = Direction.UP;
                    } else if (getRelativeEntity(-1, 0) == null) {
                        move(-1, 0);
                        this.lastDirection = Direction.LEFT;
                    } else if (getRelativeEntity(0, 1) == null) {
                        move(0, 1);
                        this.lastDirection = Direction.DOWN;
                    }
                    break;
                case DOWN:
                    if (getRelativeEntity(-1, 0) == null) {
                        move(-1, 0);
                        this.lastDirection = Direction.LEFT;
                    } else if (getRelativeEntity(0, 1) == null) {
                        move(0, 1);
                        this.lastDirection = Direction.DOWN;
                    } else if (getRelativeEntity(1, 0) == null) {
                        move(1, 0);
                        this.lastDirection = Direction.RIGHT;
                    } else if (getRelativeEntity(0, -1) == null) {
                        move(0, -1);
                        this.lastDirection = Direction.UP;
                    }
                    break;
                case LEFT:
                    if (getRelativeEntity(0, -1) == null) {
                        move(0, -1);
                        this.lastDirection = Direction.UP;
                    } else if (getRelativeEntity(-1, 0) == null) {
                        move(-1, 0);
                        this.lastDirection = Direction.LEFT;
                    } else if (getRelativeEntity(0, 1) == null) {
                        move(0, 1);
                        this.lastDirection = Direction.DOWN;
                    } else if (getRelativeEntity(1, 0) == null) {
                        move(1, 0);
                        this.lastDirection = Direction.RIGHT;
                    }
                    break;
                case RIGHT:
                    if (getRelativeEntity(0, 1) == null) {
                        move(0, 1);
                        this.lastDirection = Direction.DOWN;
                    } else if (getRelativeEntity(1, 0) == null) {
                        move(1, 0);
                        this.lastDirection = Direction.RIGHT;
                    } else if (getRelativeEntity(0, -1) == null) {
                        move(0, -1);
                        this.lastDirection = Direction.UP;
                    } else if (getRelativeEntity(-1, 0) == null) {
                        move(-1, 0);
                        this.lastDirection = Direction.LEFT;
                    }
                    break;
            }
        }
    }

}
