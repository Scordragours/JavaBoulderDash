package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public abstract class EntityTest {
protected Entity entite;
protected Field[] fields;
    @Before
    public void setUp() throws Exception {
        this.entite = new Dirt(5,6,EntityType.DIRT,true);
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
    public void TestGetPositionY() {
        int posY = 6;
        this.entite.setPositionY(6);
        Assert.assertEquals(posY,entite.getPositionY());
    }

    @Test
    public void TestSetPositionX() throws IllegalAccessException {
        int SetX=6;
        for(Field field : this.fields)
        {
            if(field.getName().equals("positionX"))
            {
                field.set(this.entite, SetX);
            }
        }
        Assert.assertEquals(SetX,this.entite.getPositionX());
    }

    @Test
    public void TestSetPositionY() {
        int PosY = 45;
        this.entite.setPositionY(45);
        Assert.assertEquals(this.entite.getPositionY(),PosY);
    }
    @Test
    public void TestSetNotNull()
    {

    }
    @Test
    public void TestGetSprite() {

    }

    @Test
    public void TestGetType() {
    }

    @Test
    public void TestGetRelativeEntity() {
    }
}