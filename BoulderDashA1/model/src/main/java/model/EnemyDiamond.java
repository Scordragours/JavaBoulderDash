package model;

/**
 * The EnemyDiamond class.
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class EnemyDiamond extends Enemy {

    /**
     * Instantiates a new EnemyDiamond.
     *
     * @param model associate the model
     * @param x sets the X position
     * @param y sets the Y position
     */
    EnemyDiamond(final Model model, final int x, final int y)
    {
        super(model,x,y,EntityType.ENEMYDIAMOND);
    }

    /**
     * Causes the death of the enemy. Instantiates 3x3 diamonds.
     *
     * @throws Exception when the given positions are out of the world
     */
    public void die() throws Exception
    {
        this.explode(true);
    }
}
