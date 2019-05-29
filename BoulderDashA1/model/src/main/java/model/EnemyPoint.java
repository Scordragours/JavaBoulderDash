package model;

/**
 * The enemy point class
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class EnemyPoint extends Enemy {
    /**
     * Constructor of EnemyPoint
     * @param model the model
     * @param x the X position
     * @param y the Y position
     */
    EnemyPoint(final Model model, final int x, final int y)
    {
        super(model,x,y,EntityType.ENEMYPOINT);
    }

    /**
     * The enemyDiamond dies
     * @throws Exception if the position are negatives
     */
    public void die() throws Exception
    {
        this.model.setScore(this.model.getScore() + 250);
        this.explode(false);
    }
}
