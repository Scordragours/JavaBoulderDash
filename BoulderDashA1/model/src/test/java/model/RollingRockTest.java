package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RollingRockTest {
protected Direction direction;
protected Entity entite;
protected Model model;
    @Before
    public void setUp() throws Exception {
        this.model = new Model(5,6);
        this.entite = new RollingRock(model,5,6);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void moved() {
        Entity expected = model.getWorld()[1][0];

        Assert.assertEquals(expected,this.entite.getRelativeEntity(-5,-5));

    }


    @Test
    public void RIGHTdirection()
    {
        direction = Direction.RIGHT;
        Assert.assertEquals(Direction.RIGHT, direction);
    }
    @Test
    public void LEFTdirection()
    {
        direction = Direction.LEFT;
        Assert.assertEquals(Direction.LEFT, direction);
    }

}