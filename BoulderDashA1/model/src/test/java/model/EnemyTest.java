package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class EnemyTest extends CharacterTest{

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMove() throws Exception
    {
        int antX = this.entite.getPositionX(), antY = this.entite.getPositionY();
        int[] expectedPosition = new int[]{antX + 1, antY};

        ((Enemy)this.entite).move(1,0);
        assertEquals(expectedPosition[0], this.entite.getPositionX());
        assertEquals(expectedPosition[1], this.entite.getPositionY());
        assertNull(this.model.getWorld()[antY][antX]);
    }
}