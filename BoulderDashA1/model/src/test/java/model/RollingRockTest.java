package model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class RollingRockTest extends SlidingBlockTest {
    /**
     * Instantiation and recovery of a Rolling Rock type entity in the level
     * @throws Exception value negative
     */
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


    /**
     * Moved
     * Method use for {@link RollingRock#moved(Direction)}
     * @throws Exception value negative
     */
    @Test
    public void moved() throws Exception {
        ((RollingRock) this.entite).moved(Direction.RIGHT);
        int expectedX = 8;
        Assert.assertEquals(expectedX,this.entite.getPositionX());

    }




}