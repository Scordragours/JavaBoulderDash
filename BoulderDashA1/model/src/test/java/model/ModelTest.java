/**
 * @author CANDAT ETIENNE AND DENEUVE GREGORY
 * @version 1.0
 */
package model;

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
        for(Field field : this.fields)
        {
            field.setAccessible(true);
        }
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testGetTime() throws Exception
    {
        int expectedTime = 70;

        for(Field field : this.fields)
        {
            if(field.getName().equals("time"))
            {
                field.set(this.model, expectedTime);
            }
        }

        Assert.assertEquals(expectedTime, this.model.getTime());
    }


    @Test
    public void testGetDiamonds() throws Exception
    {
        int expectedDiamonds = 10;
        for(Field field : this.fields)
        {
            if(field.getName().equals("diamonds"))
            {
                field.set(this.model, expectedDiamonds);
            }
        }
        Assert.assertEquals(expectedDiamonds, this.model.getDiamonds());
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
        int expectedTime = 80;
        int expectedDiamonds = 15;
        String expectedLevel = "????";

        int currentTime = 0;
        int currentDiamonds = 0;
        String currentLevel = "";

        for(Field field : this.fields)
        {
            if(field.getName().equals("level")) { currentLevel = (String)field.get(this.model); }
            if(field.getName().equals("diamonds")) { currentDiamonds = (int)field.get(this.model); }
            if(field.getName().equals("time")) { currentTime = (int)field.get(this.model); }
        }


        Assert.assertEquals(expectedTime, currentTime);
        Assert.assertEquals(expectedDiamonds, currentDiamonds);
        Assert.assertEquals(expectedLevel, currentLevel);
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
