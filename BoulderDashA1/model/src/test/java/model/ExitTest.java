package model;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class ExitTest extends EntityTest {

    @Before
    public void setUp() throws Exception {
        this.model = new Model(38,6);
        this.entite = new Exit(model,5,6);
    }

    @After
    public void tearDown() throws Exception {
    }
}