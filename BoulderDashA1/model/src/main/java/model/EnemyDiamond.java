package model;

/**
 * This class represents the enemies who generate diamonds when they die.
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
     * @param suicide true if it's a suicide, false if it's a kill
     * @throws Exception when the given positions are out of the world
     */
    public void die(boolean suicide) throws Exception
    {
        if(!suicide)
        {
            this.explode(true);
        }
        else
        {
            this.explode(false);
        }
    }
}
