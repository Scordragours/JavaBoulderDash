package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class DirtTest extends BlockTest {
    @Override
    @Before
    public void setUp() throws Exception {

        this.entite = new Dirt(5,6,EntityType.DIRT,true);
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