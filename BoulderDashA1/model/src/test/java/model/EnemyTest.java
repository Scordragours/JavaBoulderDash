package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyTest extends CharacterTest{

    @Before
    public void setUp() throws Exception {

        this.entite = new EnemyPoint(model,5,6);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void move() {

    }

    @Test
    public void pathFinder()
    {

    }
}