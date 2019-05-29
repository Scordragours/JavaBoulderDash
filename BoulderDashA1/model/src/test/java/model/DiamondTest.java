package model;

import org.junit.Before;

/**
 * Diamond Test
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public class DiamondTest extends SlidingBlockTest {
    /**
     * Instantiation and recovery of a diamond type entity in the level
     * @throws Exception if position values is negatives
     */
    @Before
    public void setUp() throws Exception {
        this.model = new Model(40,6);

        outerloop:
        for(Entity[] etab : this.model.getWorld())
            for(Entity e : etab)
                if(e != null && e.getType() == EntityType.DIAMOND)
                {
                    this.entite = e;
                    break outerloop;
                }
    }


}