package model;
/**
 * @author CANDAT ETIENNE AND DENEUVE GREGORY
 * @version 1.0
 */
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;



public  class EntityTest {
protected Entity entite;
protected Field[] fields;
protected Model model;
    @Before
    public void setUp() throws Exception {
        this.model = new Model(5,6);
        this.entite = new Dirt(model,5,6);
        //this.entite = this.model.getWorld()[0][0];
        Class<?> entiteReflector = this.entite.getClass();
        this.fields = entiteReflector.getDeclaredFields();
        for(Field field : this.fields)
        {
            field.setAccessible(true);
        }

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void TestGetPositionX() throws IllegalAccessException {

        int expectedPositionX = 5;
        for(Field field : this.fields)
        {
            if(field.getName().equals("positionX"))
            {
                field.get(this.entite);
            }
        }

        Assert.assertEquals(expectedPositionX,this.entite.getPositionX());
    }

    @Test
    public void TestGetPositionY() throws IllegalAccessException {

        int expectedPositionY = 6;
        for(Field field : this.fields)
        {
            if(field.getName().equals("positionY"))
            {
                field.get(this.entite);
            }
        }

        Assert.assertEquals(expectedPositionY,this.entite.getPositionY());
    }

    @Test
    public void TestSetPositionX() throws Exception {
        int expectedX=15;
        this.entite.setPositionX(expectedX);
        for(Field field : this.fields)
        {
            if(field.getName().equals("positionX"))
            {
                field.get(this.entite);
            }
        }

        Assert.assertEquals(expectedX,this.entite.getPositionX());
    }

    @Test
    public void TestSetPositionY() throws Exception{
        int expectedY = 10;
        this.entite.setPositionY(expectedY);
        for(Field field : this.fields)
        {
            if(field.getName().equals("positionY"))
            {
                field.get(this.entite);
            }
        }
            Assert.assertEquals(expectedY, this.entite.getPositionY());
    }

    @Test
    public void TestYNotNegative() throws Exception
    {
     try
     {
         this.entite.setPositionY(-18);

     }catch(final Exception e)
     {
         final String expected="The Y position cannot be negative";
         Assert.assertEquals(expected,e.getMessage());
     }
    }
    @Test
    public void TestXNotNegative() throws Exception
    {
        try
        {
            this.entite.setPositionX(-9);
        }catch (final Exception e)
        {
            final String expected="The X position cannot be negative";
            Assert.assertEquals(expected,e.getMessage());
        }
    }
    @Test
    public void TestGetType() throws IllegalAccessException
    {
        EntityType expectedType =  EntityType.DIRT;
        for(Field field : this.fields)
        {
            if(field.getName().equals("type"))
            {
                field.get(this.entite);
            }
        }

        Assert.assertEquals(expectedType,this.entite.getType());
    }



    @Test
    public void TestGetRelativeEntity() {
        Entity expected = model.getWorld()[1][0];

        Assert.assertEquals(expected,this.entite.getRelativeEntity(-4,-6));
    }
}