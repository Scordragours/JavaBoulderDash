package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyPointTest extends EnemyTest {

    @Before
    public void setUp() throws Exception {
        this.model = new Model(39,1);

        for(Entity[] etab : this.model.getWorld())
            for(Entity e : etab)
                if(e != null && e.getType() == EntityType.ENEMYPOINT)
                    this.entite = e;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void die() {
    }
}