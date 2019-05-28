package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class SlidingBlockTest extends EntityTest{

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFall() throws Exception
    {
        if(this.entite instanceof RollingRock)
        {
            int expectedY = 5;
            ((SlidingBlock)this.entite).fall();
            assertEquals(expectedY, this.entite.getPositionY());

            assertEquals(EntityType.PLAYER, this.model.getPlayer().getType());
            assertTrue(this.model.getPlayer().isAlive());

            ((SlidingBlock)this.entite).fall();

            assertFalse(this.model.getPlayer().isAlive());
            assertNull(this.model.getWorld()[this.entite.getPositionY()][this.entite.getPositionX()]);
        }
    }

    @Test
    public void testSlide() throws Exception
    {
        if(this.entite instanceof Diamond)
        {
            int expectedX = 11;
            assertNull(this.model.getWorld()[4][11]);
            assertNull(this.model.getWorld()[5][11]);
            assertTrue(this.model.getWorld()[5][12] instanceof SlidingBlock);

            ((SlidingBlock)this.entite).slide(true);
            assertEquals(expectedX, this.entite.getPositionX());
        }
    }
}