package model;

import model.level.Level;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Model Test
 * @author CANDAT ETIENNE AND DENEUVE GREGORY
 * @version 1.0
 */
public class ModelTest {
   /**  Model declaration */
    private Model model;
    /** Field declaration */
    private Field[] fields;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }



    @Before
    public void setUp() throws Exception
    {
        this.model = new Model(38, 1);
        Class<?> modelReflector = this.model.getClass();
        this.fields = modelReflector.getDeclaredFields();
        for(Field field : this.fields) {
            field.setAccessible(true);
        }
    }

    /**
     * Min range for the level texture
     */
    @Test
    public void excepLevelTextureMinRange()
    {
        try
        {
            new Model(1,0);
            fail("Should throw exception when levelTexture < 1");
        } catch (final Exception e)
        {
            final String excepted = "levelTexture out of range";
            assertEquals(excepted, e.getMessage());
        }
    }
    /**
     * Max range for the level texture
     */
    @Test
    public void excepLevelTextureMaxRange()
    {
        try
        {
            new Model(1,7);
            fail("Should throw exception when levelTexture > 6");
        } catch (final Exception e)
        {
            final String excepted = "levelTexture out of range";
            assertEquals(excepted, e.getMessage());
        }
    }
    /**
     * Get Observable
     * Test method for {@link Model#getObservable()}
     */
    @Test
    public void testGetObservable()
    {
        Model expectedModel = this.model;

        assertEquals(expectedModel, this.model.getObservable());
    }

    /**
     * Get Level
     * Test method for {@link Model#getLevel()}
     * @throws Exception value negative
     */
    @Test
    public void testGetLevel() throws Exception
    {
        Level expectedLevel = new Level(1, 80, 15, "");

        for(Field field : this.fields)
        {
            if(field.getName().equals("level"))
            {
                field.set(this.model, expectedLevel);
            }
        }

        assertEquals(expectedLevel, this.model.getLevel());
    }

    /**
     * Get Level Texture
     * Test method for {@link Model#getLevelTexture()}
     * @throws Exception value negative
     */
    @Test
    public void testGetLevelTexture() throws Exception
    {
        int expectedLevelTexture = 1;

        assertEquals(expectedLevelTexture, this.model.getLevelTexture());
    }

    /**
     * Get Remaining time
     * Test method for {@link Model#getRemainingTime()}
     */
    @Test
    public void testGetRemainingTime()
    {
        int expectedRemainingTime = 80;

        assertEquals(expectedRemainingTime, this.model.getRemainingTime());
    }

    /**
     * Set remaining time
     * Test method for {@link Model#setRemainingTime(int)}
     * @throws Exception value negative
     */
    @Test
    public void testSetRemainingTime() throws Exception
    {
        int expectedRemainingTime = 80;
        this.model.setRemainingTime(expectedRemainingTime);
        int remainingTime = 0;

        for (Field field : this.fields)
        {
            if(field.getName().equals("remainingTime"))
            {
                remainingTime = (int)field.get(this.model);
            }
        }
        assertEquals(expectedRemainingTime, remainingTime);
    }

    /**
     * Get remaining time
     * Test method for {@link Model#getRemainingDiamonds()}
     */
    @Test
    public void testGetRemainingDiamonds()
    {
        int expectedRemainingDiamonds = 15;

        assertEquals(expectedRemainingDiamonds, this.model.getRemainingDiamonds());
    }

    /**
     * Set remaining Diamonds
     * Test method for {@link Model#decrementRemainingDiamonds()}
     * @throws Exception value negative
     */
    @Test
    public void testDecrementRemainingDiamonds() throws Exception
    {
        int expectedRemainingDiamonds = 14;
        this.model.decrementRemainingDiamonds();
        int remainingDiamonds = 0;

        for (Field field : this.fields)
        {
            if(field.getName().equals("remainingDiamonds"))
            {
                remainingDiamonds = (int)field.get(this.model);
            }
        }
        assertEquals(expectedRemainingDiamonds, remainingDiamonds);
    }

    /**
     * Get Score
     * Test method for {@link Model#getScore()}
     * @throws Exception value negative
     */
    @Test
    public void testGetScore() throws Exception
    {
        int expectedScore = 15;

        for (Field field : this.fields)
        {
            if(field.getName().equals("score"))
            {
                field.set(this.model, expectedScore);
            }
        }

        assertEquals(expectedScore, this.model.getScore());
    }

    /**
     * Set Score
     * Test method for {@link Model#setScore(int)}
     * @throws Exception value negative
     */
    @Test
    public void testSetScore() throws Exception
    {
        int expectedScore = 20;
        this.model.setScore(expectedScore);
        int score = 0;

        for (Field field : this.fields)
        {
            if(field.getName().equals("score"))
            {
                score = (int)field.get(this.model);
            }
        }
        assertEquals(expectedScore, score);
    }

    /**
     * Get World
     * Test method for {@link Model#getWorld()}
     * @throws Exception negative values
     */
    @Test
    public void testGetWorld() throws Exception
    {
        Entity[][] expectedWorld = null;

        for(Field field : this.fields)
        {
            if(field.getName().equals("world"))
            {
                expectedWorld = (Entity[][])field.get(this.model);
            }
        }
        assertArrayEquals(expectedWorld, this.model.getWorld());
    }

    /**
     * Load Level
     * Test method for {@link Model#loadLevel(int)}
     * @throws Exception value negative
     */
    @Test
    public void testLoadLevel() throws Exception
    {
        Level expectedLevel = new Level(38, 80, 15, "I;P;I.");
        Level currentLevel = null;


        for(Field field : this.fields)
        {
            if(field.getName().equals("level")) { currentLevel = (Level)field.get(this.model); }
        }


        assertEquals(expectedLevel.getId(), currentLevel.getId());
        assertEquals(expectedLevel.getLevel(), currentLevel.getLevel());
        assertEquals(expectedLevel.getNbDiamond(), currentLevel.getNbDiamond());
        assertEquals(expectedLevel.getTime(), currentLevel.getTime());
    }

    /**
     * Build World
     * Test method for {@link Model#buildWorld()}
     * @throws Exception value negative
     */
    @Test
    public void testBuildWorld() throws Exception
    {
        Entity[][] expectedWorld = {
                {new Wall(this.model, 0,0,EntityType.WALL)},
                {new Player(this.model,0,1)},
                {new Wall(this.model,0,2,EntityType.WALL)}
        };

        Entity[][] currentWorld = null;

        for(Field field : this.fields)
        {
            if(field.getName().equals("world"))
            {
                currentWorld = (Entity[][])field.get(this.model);
            }
        }
        assertEquals(expectedWorld[0][0].getType(), currentWorld[0][0].getType());
        assertEquals(expectedWorld[1][0].getType(), currentWorld[1][0].getType());
        assertEquals(expectedWorld[2][0].getType(), currentWorld[2][0].getType());

        assertEquals(expectedWorld[0][0].getPositionX(), currentWorld[0][0].getPositionX());
        assertEquals(expectedWorld[1][0].getPositionX(), currentWorld[1][0].getPositionX());
        assertEquals(expectedWorld[2][0].getPositionX(), currentWorld[2][0].getPositionX());

        assertEquals(expectedWorld[0][0].getPositionY(), currentWorld[0][0].getPositionY());
        assertEquals(expectedWorld[1][0].getPositionY(), currentWorld[1][0].getPositionY());
        assertEquals(expectedWorld[2][0].getPositionY(), currentWorld[2][0].getPositionY());
    }

    /**
     * Get Player
     * Test method for {@link Model#getPlayer()}
     * @throws Exception value negative
     */
    @Test
    public void testGetPlayer() throws Exception
    {
        Player expectedPlayer = null;

        for(Field field : this.fields)
        {
            if(field.getName().equals("player")) { expectedPlayer = (Player)field.get(this.model); }
        }

        assertEquals(expectedPlayer, this.model.getPlayer());
    }

    /**
     * Convert world
     * Test method for {@link Model#convertWorld()}
     */
    @Test
    public void testConvertWorld()
    {
        char[][] expectedConvertedWorld = new char[26][48];
        for(int y =0; y<26;y++)
            for(int x=0; x<48;x++)
                expectedConvertedWorld[y][x] = ' ';
        expectedConvertedWorld[0][0] = 'I';
        expectedConvertedWorld[1][0] = 'P';
        expectedConvertedWorld[2][0] = 'I';

        assertArrayEquals(expectedConvertedWorld, this.model.convertWorld());
    }

    /**
     *
     * Update entity
     * Test method for {@link Model#updateEntity(int, int, Entity)}
     * @throws Exception value negative
     */
    @Test
    public void testUpdateEntity() throws Exception
    {
        Entity expectedEntity = new Dirt(this.model, 3,0);

        this.model.updateEntity(4,1, expectedEntity);

        assertEquals(4, expectedEntity.getPositionX());
        assertEquals(1, expectedEntity.getPositionY());
        assertEquals(expectedEntity, this.model.getWorld()[1][4]);

    }

    /**
     * Add an explosion
     * Test method for {@link Model#addExplosion(int[])}
     * @throws Exception value negative
     */
    @Test
    public void testAddExplosion() throws Exception
    {
        int[] expected = {5,5};

        this.model.addExplosion(expected);
        ArrayList<int[]> current = null;
        for(Field field : this.fields)
        {
            if(field.getName().equals("explosions")) { current = (ArrayList<int[]>) field.get(this.model); }
        }

        int[] currentCoords = new int[] {current.get(0)[0],current.get(0)[1]};
        assertArrayEquals(expected, currentCoords);
    }

    /**
     * Get Explosion
     * Test method for {@link Model#getExplosions()}
     * @throws Exception value negative
     */
    @Test
    public void testGetExplosion() throws Exception
    {
        ArrayList<int[]> expected = null;
        for(Field field : this.fields)
        {
            if(field.getName().equals("explosions")) { expected = (ArrayList<int[]>) field.get(this.model); }
        }

        assertEquals(expected, this.model.getExplosions());
    }
}
