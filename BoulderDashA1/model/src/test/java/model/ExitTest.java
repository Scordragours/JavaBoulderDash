package model;
/**
 * Exit Test
 * @autor DENEUVE GREGORY
 */


import org.junit.Before;



public class ExitTest extends EntityTest {
    /**
     * @throws Exception position not null
     */
    @Before
    public void setUp() throws Exception {
        this.model = new Model(38,6);
        this.entite = new Exit(model,5,6);
    }


}