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
        /*this.model = new Model(2,1);
        this.entite = this.model.getPlayer();*/
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testExplode() throws Exception
    {
        int[][] expectedExplosions = {
                {this.entite.getPositionX(),this.entite.getPositionY()},
                {this.entite.getPositionX(),this.entite.getPositionY()+1},
                {this.entite.getPositionX()+1,this.entite.getPositionY()+1},
                {this.entite.getPositionX()+1,this.entite.getPositionY()},
                {this.entite.getPositionX()-1,this.entite.getPositionY()+1},
                {this.entite.getPositionX()-1,this.entite.getPositionY()},
                {this.entite.getPositionX()-1,this.entite.getPositionY()-1},
                {this.entite.getPositionX(),this.entite.getPositionY()-1},
                {this.entite.getPositionX()+1,this.entite.getPositionY()-1}
        };
        int[][] currentExplosions = new int[9][2];

        ((Character)this.entite).explode(false);

        ArrayList<int[]> explosions = this.entite.model.getExplosions();
        for(int i = 0; !explosions.isEmpty(); i++)
        {
            currentExplosions[i] = explosions.remove(0);
        }
        assertArrayEquals(expectedExplosions, currentExplosions);
        assertNull(this.entite.getRelativeEntity(0,-1));
        assertNull(this.entite.getRelativeEntity(0,1));
        assertNull(this.entite.getRelativeEntity(1,0));
        assertNull(this.entite.getRelativeEntity(1,1));
        assertNull(this.entite.getRelativeEntity(1,-1));
        assertNull(this.entite.getRelativeEntity(-1,0));
        assertNull(this.entite.getRelativeEntity(-1,1));
        assertNull(this.entite.getRelativeEntity(-1,-1));
    }

    /*@Test
    public void move() throws Exception
    {
        int[] expectedPosition = new int[]{1,1};
        ((Character)this.entite).move(1,0);
        assertEquals(expectedPosition[1], this.entite.getPositionX());
        assertEquals(expectedPosition[0], this.entite.getPositionY());
        assertNull(this.model.getWorld()[0][1]);
    }*/
}