package model;

import entity.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import static org.junit.Assert.*;

/**
 * @author CANDAT ETIENNE AND DENEUVE GREGORY
 * @version 1.0
 */
public class ModelTest {
    private Model model;
    private Field[] fields;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception
    {
        this.model = new Model(1, 1);
        Class<?> modelReflector = this.model.getClass();
        this.fields = modelReflector.getDeclaredFields();
        for(Field field : this.fields) {
            field.setAccessible(true);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

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

    @Test
    public void testGetObservable()
    {
        Model expectedModel = this.model;

        assertEquals(expectedModel, this.model.getObservable());
    }

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

    @Test
    public void testGetLevelTexture() throws Exception
    {
        int expectedLevelTexture = 1;

        assertEquals(expectedLevelTexture, this.model.getLevelTexture());
    }

    @Test
    public void testGetRemainingTime()
    {
        int expectedRemainingTime = 80;

        assertEquals(expectedRemainingTime, this.model.getRemainingTime());
    }

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

    @Test
    public void testGetRemainingDiamonds()
    {
        int expectedRemainingDiamonds = 15;

        assertEquals(expectedRemainingDiamonds, this.model.getRemainingDiamonds());
    }

    @Test
    public void testSetRemainingDiamonds() throws Exception
    {
        int expectedRemainingDiamonds = 15;
        this.model.setRemainingDiamonds(15);
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

    @Test
    public void testGetWorld() throws Exception
    {
        Entity[][] expectedWorld = null;

        for(Field field : this.fields)
        {
            if(field.getName().equals("world"))
            {
                field.set(this.model, expectedWorld);
            }
        }
        assertEquals(expectedWorld, this.model.getWorld());
    }


    @Test
    public void testLoadLevel() throws Exception
    {
        Level expectedLevel = new Level(1, 80, 15, "I;P;I.");
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

    @Test
    public void testBuildWorld() throws Exception
    {
        Entity[][] expectedWorld = {
                {new Wall(this.model, 0,0,EntityType.INLINE, false)},
                {new Player(this.model,1,0,EntityType.PLAYER)},
                {new Wall(this.model,2,0,EntityType.INLINE, false)}
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
    }

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


}
