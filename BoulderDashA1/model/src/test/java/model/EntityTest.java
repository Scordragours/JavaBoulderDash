package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public  class EntityTest {
protected Entity entite;
    @Before
    public void setUp() throws Exception {
        this.entite = new Dirt(5,6,EntityType.DIRT,true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void TestGetPositionX() {

        int posX = 5;

        int result = this.entite.getPositionX();
        Assert.assertEquals(result,posX);


    }

    @Test
    public void TestGetPositionY() {
        int posY = 6;
        this.entite.setPositionY(6);
        Assert.assertEquals(posY,entite.getPositionY());
    }

    @Test
    public void TestSetPositionX() {
        int SetX=6;
        this.entite = new Dirt(5,6,EntityType.DIRT,true);
        Assert.assertEquals(this.entite.getPositionX(),SetX);
    }

    @Test
    public void TestSetPositionY() {
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