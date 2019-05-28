package model;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class WallTest extends EntityTest {

    @Before
    public void setUp() throws Exception {
        this.model = new Model(1,6);
        this.entite = new Wall(model,5,6, EntityType.OUTLINE);
    }

    @After
    public void tearDown() throws Exception {
    }
}