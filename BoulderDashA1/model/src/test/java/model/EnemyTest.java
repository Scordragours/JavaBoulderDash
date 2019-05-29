package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Enemy Test
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public abstract class EnemyTest extends CharacterTest{

    @Before
    public void setUp() throws Exception {

    }


    /**
     * Test method for {@link Enemy#move(int, int)}
     * @throws Exception values are negatives
     */
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