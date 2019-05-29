package model;

/**
 * The EnemyPoint class.
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class EnemyPoint extends Enemy {

    /**
     * Instantiates a new EnemyPoint.
     *
     * @param model associate the model
     * @param x sets the X position
     * @param y sets the Y position
     */
    EnemyPoint(final Model model, final int x, final int y)
    {
        super(model,x,y,EntityType.ENEMYPOINT);
    }

    /**
     * Causes the death of the enemy. Increments the score by 250 points.
     *
     * @throws Exception when the given positions are out of the world
     */
    public void die() throws Exception
    {
        this.model.setScore(this.model.getScore() + 250);
        this.explode(false);
    }
}
