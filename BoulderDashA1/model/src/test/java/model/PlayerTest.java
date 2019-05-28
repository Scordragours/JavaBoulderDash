package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest extends CharacterTest {

    @Before
    public void setUp() throws Exception {
        this.model = new Model(39,1);
        this.entite = this.model.getPlayer();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getLastMove() {
    }

    @Test
    public void testMove() throws Exception
    {
        int expectedX = 6;
        int expectedY = 3;

        assertEquals(EntityType.ROLLINGROCK, this.model.getWorld()[3][6].getType());
        assertNull(this.model.getWorld()[3][7]);

        ((Player)this.entite).move(1,0);

        assertEquals(expectedX, this.entite.getPositionX());
        assertEquals(expectedY, this.entite.getPositionY());
        assertNull(this.model.getWorld()[3][5]);
        assertEquals(this.entite, this.model.getWorld()[3][6]);
        assertEquals(EntityType.ROLLINGROCK, this.model.getWorld()[3][7].getType());
    }
}