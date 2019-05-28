package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest extends CharacterTest {

    @Before
    public void setUp() throws Exception {
        this.model = new Model(2,1);
        this.entite = this.model.getPlayer();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getLastMove() {
    }

    @Test
    public void setLastMove() {
    }
}