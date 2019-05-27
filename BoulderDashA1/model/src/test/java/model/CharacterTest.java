package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public abstract class CharacterTest extends EntityTest {

    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testExplode()
    {
        int[][] expectedExplosions = {
                {0,0},
                {0,1},
                {1,1},
                {1,0},
                {-1,1},
                {-1,0},
                {-1,-1},
                {0,-1},
                {1,-1}
        };
        int[][] currentExplosions = new int[9][2];

        ((Character)this.entite).explode();

        ArrayList<int[]> explosions = this.entite.model.getExplosions();
        for(int i = 0; !explosions.isEmpty(); i++)
        {
            currentExplosions[i] = explosions.remove(0);
        }
        assertArrayEquals(expectedExplosions, currentExplosions);
    }

    /*@Test
    public void move() {

    }

    @Test
    public void die() {
    }*/
}