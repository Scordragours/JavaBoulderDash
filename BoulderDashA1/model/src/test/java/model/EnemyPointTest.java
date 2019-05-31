package model;

import org.junit.Before;
import org.junit.Test;

/**
 * Enemy Point Test
 * @author DENEUVE GREGORY AND ETIENNE CANDAT
 */

public class EnemyPointTest extends EnemyTest {
    /**
     * Instantiation and recovery of a diamond type entity in the level
     * @throws Exception if positions are negatives
     */
    @Before
    public void setUp() throws Exception {
        this.model = new Model(39,1);

        for(Entity[] etab : this.model.getWorld())
            for(Entity e : etab)
                if(e != null && e.getType() == EntityType.ENEMYPOINT)
                    this.entite = e;
    }



    /**
     * Test method for {@link EnemyPoint#die(boolean)}.
     */
    @Test
    public void die() {
    }
}