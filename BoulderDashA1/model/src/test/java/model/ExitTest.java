package model;

import org.junit.Before;

/**
 * Exit Test
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */


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