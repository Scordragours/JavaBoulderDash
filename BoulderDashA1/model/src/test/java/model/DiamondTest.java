package model;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class DiamondTest extends SlidingBlockTest {

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

    @After
    public void tearDown() throws Exception {
    }
}