package model;

import org.junit.After;
import org.junit.Before;


import java.lang.reflect.Field;



public class DirtTest extends EntityTest {

    @Before
    public void setUp() throws Exception {
        this.model = new Model(38,6);
        this.entite = new Dirt(model,5,6);
        /*Class<?> entiteReflector = this.entite.getClass();
        this.fields = entiteReflector.getDeclaredFields();
        for(Field field : this.fields)
        {
            field.setAccessible(true);
        }*/
    }


    @After
    public void tearDown() throws Exception {
    }
}