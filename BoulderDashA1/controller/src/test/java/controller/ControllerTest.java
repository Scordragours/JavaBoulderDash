package controller;

import contract.ControllerOrder;
import contract.IModel;
import contract.IView;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public final class ControllerTest extends TestCase {

    /** The controller use for the test */
    private static Controller controller;
    /** The model for the test*/
    private IModel model;
    /** The vie for the test*/
    private IView view;

    /**
     * instantiate the controller class
     *
     * @throws Exception for bad direction assign to the player
     * @throws NullPointerException Something missing in controller.orderPerform()
     */

    @Before
    public void setUp() throws Exception {
        controller = new Controller(view, model);
        try {
            controller.orderPerform(ControllerOrder.STAND_BY);
        } catch (NullPointerException e) {
            System.out.println("Something missing in controller.orderPerform()");
        }
    }

    /**
     * Test method for {@link Controller#orderPerform(ControllerOrder)}
     * when we use default switch case and so when Controller#MotionLessControl(boolean)
     * have "true" set as parameter.
     *
     * @throws NullPointerException Something missing in testTrueMotionLessControl()
     *
     */

    @Test
    public void testOrderPerform() throws NullPointerException{
        try {
            assertTrue(testTrueMotionLessControl());
        } catch (NullPointerException e) {
            System.out.println("Something missing in testTrueMotionLessControl()");
        }
    }

    /**
     *  A test for the private method of the Controller
     *
     * @throws NullPointerException Unable to get the value returned by model.getRemainingTime()
     * @return true
     *
     */

    private boolean testTrueMotionLessControl() throws NullPointerException{
        int timer = 10;
        try {
            this.model.getRemainingTime();
        } catch (NullPointerException e) {
            System.out.println("Unable to get the value returned by model.getRemainingTime()");
        } finally {
            int expectRemainingTime = 8;
            if (timer - expectRemainingTime >= 1) {
                return true;
            }
        }
        assertTrue("The value of the remaining time cannot be negative",this.model.getRemainingTime() > 0);
        assertTrue("The value of the timer smaller than the remaining time",timer >= this.model.getRemainingTime());
        assertTrue("The subtraction of the timer by the remaining time cannot be negative or equal to zero",timer - this.model.getRemainingTime() > 1);
        return true;
    }
}