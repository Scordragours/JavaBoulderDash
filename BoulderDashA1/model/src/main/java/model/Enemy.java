package model;

/**
 *The enemy class
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public abstract class Enemy extends Character{
    /** The last direction of enemy*/
    private Direction lastDirection;
    private boolean hasMove;

    /**
     * Constructor of Enemy
     * @param model the model
     * @param x the X position
     * @param y the Y position
     * @param type the Type of entity
     */
    public Enemy(final Model model, final int x, final int y, final EntityType type)
    {
        super(model,x,y,type);
        lastDirection = Direction.UP;
        this.hasMove = false;
    }

    void resetMove() { this.hasMove = false; }

    /**
     * The move method for enemy
     * @param x recovers the X position
     * @param y recovers the Y position
     * @throws Exception if the position are negatives
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
     * A method that allows an enemy to choose their path
     * @throws Exception if the position are negatives
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
