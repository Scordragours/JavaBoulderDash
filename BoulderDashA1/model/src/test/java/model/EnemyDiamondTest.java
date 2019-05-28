package model;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class EnemyDiamondTest extends EnemyTest {

    @Before
    public void setUp() throws Exception {
        this.model = new Model(2,1);

        for(Entity[] etab : this.model.getWorld())
            for(Entity e : etab)
                if(e.getType() == EntityType.ENEMYDIAMOND)
                    this.entite = e;
    }

    @After
    public void tearDown() throws Exception {
    }
}