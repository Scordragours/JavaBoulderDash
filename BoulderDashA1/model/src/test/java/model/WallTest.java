package model;
/**
 * Wall Test
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */

import org.junit.Before;

public class WallTest extends EntityTest {
    /**
     * Instantiation
     * @throws Exception value negative
     */
    @Before
    public void setUp() throws Exception {
        this.model = new Model(38,6);
        this.entite = new Wall(model,5,6, EntityType.OUTLINE);
    }


}