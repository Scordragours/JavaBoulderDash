package model;

import org.junit.*;

import java.lang.reflect.Field;
import static org.junit.Assert.*;

/**
 * Entity Test
 * @author CANDAT ETIENNE AND DENEUVE GREGORY
 * @version 1.0
 */
public abstract class EntityTest {
    protected Entity entite;
    private static Field[] fields;
    protected Model model;

    /**
     * Use the reflection in Java
     * @throws Exception if the position are negatives
     *
     * @see java.lang.reflect.Field
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        Class<?> entiteReflector = Entity.class;
        fields = entiteReflector.getDeclaredFields();
        for(Field field : fields)
        {
            field.setAccessible(true);
        }
    }

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link Entity#getPositionX()} ()}
     * @throws IllegalAccessException reflexion
     */
    @Test
    public void testGetPositionX() throws IllegalAccessException {

        int expectedPositionX = 0;
        for(Field field : fields)
        {
            if(field.getName().equals("positionX"))
            {
                expectedPositionX = (int)field.get(this.entite);
            }
        }

        assertEquals(expectedPositionX,this.entite.getPositionX());
    }

    /**
     * Test method for {@link Entity#getPositionY()}
     * @throws IllegalAccessException reflexion
     */
    @Test
    public void testGetPositionY() throws IllegalAccessException {

        int expectedPositionY = 0;
        for(Field field : fields)
        {
            if(field.getName().equals("positionY"))
            {
                expectedPositionY = (int)field.get(this.entite);
            }
        }

        assertEquals(expectedPositionY,this.entite.getPositionY());
    }

    /**
     * Test method for {@link Entity#setPositionX(int)}
     * @throws Exception value negative
     */
    @Test
    public void testSetPositionX() throws Exception {
        int expectedX=15;
        this.entite.setPositionX(expectedX);
        int currentX = 0;

        for(Field field : fields)
        {
            if(field.getName().equals("positionX"))
            {
                currentX = (int)field.get(this.entite);
            }
        }

        assertEquals(expectedX,currentX);
    }

    /**
     * Test method for {@link Entity#setPositionY(int)}
     * @throws Exception value negative
     */
    @Test
    public void testSetPositionY() throws Exception{
        int expectedY = 10;
        this.entite.setPositionY(expectedY);
        int currentY = 0;

        for(Field field : fields)
        {
            if(field.getName().equals("positionY"))
            {
                currentY = (int)field.get(this.entite);
            }
        }

        assertEquals(expectedY, currentY);
    }

    /**
     * Test if Y position is negative
     * @throws Exception value negative
     */
    @Test
    public void testYNotNegative() throws Exception
    {
     try
     {
         this.entite.setPositionY(-18);

     }catch(final Exception e)
     {
         final String expected="The Y position cannot be negative";
         assertEquals(expected,e.getMessage());
     }
    }

    /**
     * Test if X is not negative
     * @throws Exception value negative
     */
    @Test
    public void testXNotNegative() throws Exception
    {
        try
        {
            this.entite.setPositionX(-9);
        }catch (final Exception e)
        {
            final String expected="The X position cannot be negative";
            assertEquals(expected,e.getMessage());
        }
    }

    /**
     * Test method for {@link Entity#getType()}
     * @throws IllegalAccessException reflexion
     */
    @Test
    public void testGetType() throws IllegalAccessException
    {
        EntityType expectedType =  null;
        for(Field field : fields)
        {
            if(field.getName().equals("type"))
            {
                expectedType = (EntityType)field.get(this.entite);
            }
        }

        assertEquals(expectedType,this.entite.getType());
    }

    /**
     * Test method for {@link Entity#getRelativeEntity(int, int)}
     * @throws Exception values not negatives
     */
    @Test
    public void TestGetRelativeEntity() throws Exception
    {
        int entityPosX = 0, entityPosY = 0;

        for(Field field : fields)
        {
            if(field.getName().equals("positionX")) { entityPosX = (int)field.get(this.entite); }
            if(field.getName().equals("positionY")) { entityPosY = (int)field.get(this.entite); }
        }

        Entity expected = model.getWorld()[entityPosY-1][entityPosX-1];
        assertEquals(expected,this.entite.getRelativeEntity(-1,-1));
    }
}