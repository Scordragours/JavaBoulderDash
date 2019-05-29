package model;
/**
 * Test Dirt
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */


import org.junit.Before;






public class DirtTest extends EntityTest {
    /**
     * Instantiation and recovery of a dirt type entity in the level
     * @throws Exception if the position values are negatives
     */
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



}