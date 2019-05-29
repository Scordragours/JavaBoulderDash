package model;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * CharacterTest
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public abstract class CharacterTest extends EntityTest {

    @Before
    public void setUp() throws Exception
    {

    }



    /**
     * Test method for {@link model.Character#explode(boolean)}.
     * @throws Exception if position are negatives
     */
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


}