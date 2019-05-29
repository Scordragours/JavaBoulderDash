package model;
/**
 * Enemy Diamond Test
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */


import org.junit.Before;

public class EnemyDiamondTest extends EnemyTest {
    /**
     * Instantiation and recovery of a enemy diamond type entity in the level
     * @throws Exception if the position are negatives
     */
    @Before
    public void setUp() throws Exception {
        this.model = new Model(39,1);

        for(Entity[] etab : this.model.getWorld())
            for(Entity e : etab)
                if(e != null && e.getType() == EntityType.ENEMYDIAMOND)
                    this.entite = e;
    }


}