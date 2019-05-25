package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirtTest extends BlockTest {

    @Before
    public void setUp() throws Exception {
        Dirt dirt = new Dirt(5, 3, EntityType.DIRT, true);

    }


    @After
    public void tearDown() throws Exception {
    }
}