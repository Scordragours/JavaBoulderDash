/**
 * @author CANDAT ETIENNE AND DENEUVE GREGORY
 * @version 1.0
 */
package model;

import entity.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;

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
        this.model = new Model(1);
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

        Assert.assertEquals(expectedLevel, this.model.getLevel());
    }

    @Test
    public void testGetRemainingTime()
    {
        int expectedRemainingTime = 80;

        Assert.assertEquals(expectedRemainingTime, this.model.getRemainingTime());
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
        Assert.assertEquals(expectedRemainingTime, remainingTime);
    }

    @Test
    public void testGetRemainingDiamonds()
    {
        int expectedRemainingDiamonds = 15;

        Assert.assertEquals(expectedRemainingDiamonds, this.model.getRemainingDiamonds());
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
        Assert.assertEquals(expectedRemainingDiamonds, remainingDiamonds);
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
        Assert.assertEquals(expectedWorld, this.model.getWorld());
    }


    @Test
    public void testLoadLevel() throws Exception
    {
        Level expectedLevel = new Level(1, 80, 15, "zeub");
        Level currentLevel = null;


        for(Field field : this.fields)
        {
            if(field.getName().equals("level")) { currentLevel = (Level)field.get(this.model); }
        }


        Assert.assertEquals(expectedLevel.getId(), currentLevel.getId());
        Assert.assertEquals(expectedLevel.getLevel(), currentLevel.getLevel());
        Assert.assertEquals(expectedLevel.getNbDiamond(), currentLevel.getNbDiamond());
        Assert.assertEquals(expectedLevel.getTime(), currentLevel.getTime());
    }

    @Test
    public void testBuildWorld() throws Exception
    {
        Entity[][] expectedWorld = null;

        Entity[][] currentWorld = null;

        for(Field field : this.fields)
        {
            if(field.getName().equals("world"))
            {
                currentWorld = (Entity[][])field.get(this.model);
            }
        }
        Assert.assertEquals(expectedWorld, currentWorld);
    }

    @Test
    public void testGetPlayer() throws Exception
    {
        Player expectedPlayer = null;

        for(Field field : this.fields)
        {
            if(field.getName().equals("player")) { expectedPlayer = (Player)field.get(this.model); }
        }

        Assert.assertEquals(expectedPlayer, this.model.getPlayer());
    }


}
