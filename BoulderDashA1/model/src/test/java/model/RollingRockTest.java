package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RollingRockTest extends SlidingBlockTest {

    @Before
    public void setUp() throws Exception {
        this.model = new Model(40,6);

        outerloop:
        for(Entity[] etab : this.model.getWorld())
            for(Entity e : etab)
                if(e != null && e.getType() == EntityType.ROLLINGROCK)
                {
                    this.entite = e;
                    break outerloop;
                }

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void moved() throws Exception {
        ((RollingRock) this.entite).moved(Direction.RIGHT);
        int expectedX = 8;
        Assert.assertEquals(expectedX,this.entite.getPositionX());

    }




}