package model;

/**
 * The enemy diamond class
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class EnemyDiamond extends Enemy {
    /**
     * Constructor of EnemyDiamond
     * @param model the model
     * @param x the X position
     * @param y the Y position
     */
    public EnemyDiamond(Model model,int x, int y)
    {
        super(model,x,y,EntityType.ENEMYDIAMOND);
    }

    /**
     * The enemyDiamond dies
     * @throws Exception if the position are negative values
     */
    public void die() throws Exception
    {
        this.explode(true);
    }
}
