package model;

/**
 * This class represents the enemies who generate points when they die.
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
     * @param suicide true if it's a suicide, false if it's a kill
     * @throws Exception when the given positions are out of the world
     */
    public void die(boolean suicide) throws Exception
    {
        if(!suicide)
        {
            this.model.setScore(this.model.getScore() + 250);
        }

        this.explode(false);
    }
}
