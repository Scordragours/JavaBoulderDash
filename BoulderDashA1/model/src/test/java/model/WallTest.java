package model;


import org.junit.Before;
/**
 * Wall Test
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
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