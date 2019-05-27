package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyTest {
protected Entity entite;
protected Model model;
    @Before
    public void setUp() throws Exception {

        this.entite = new EnemyPoint(model,5,6,EntityType.ENEMYPOINT);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void move() {

    }

    @Test
    public void pathFinder() {
    }
}