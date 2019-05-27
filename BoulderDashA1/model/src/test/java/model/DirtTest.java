package model;

import org.junit.After;
import org.junit.Before;


import java.lang.reflect.Field;



public class DirtTest extends EntityTest {
    @Override
    @Before
    public void setUp() throws Exception {

        this.entite = new Dirt(5,6,EntityType.DIRT);
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
}