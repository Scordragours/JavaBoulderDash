package model;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 *Test suits
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {


                DiamondTest.class,
                DirtTest.class,
                EnemyDiamondTest.class,
                EnemyPointTest.class,
                ExitTest.class,
                ModelTest.class,
                PlayerTest.class,
                RollingRockTest.class,
                WallTest.class
        }
)
public class testsuits {
}
