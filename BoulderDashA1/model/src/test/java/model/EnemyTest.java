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
    public void move() throws Exception
    {
        int[] expectedPosition = new int[]{1,1};
        ((Enemy)this.entite).move(1,0);
        assertEquals(expectedPosition[1], this.entite.getPositionX());
        assertEquals(expectedPosition[0], this.entite.getPositionY());
        assertNull(this.model.getWorld()[0][1]);
    }
}