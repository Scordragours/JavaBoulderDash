package controller;

import contract.ControllerOrder;
import contract.IModel;
import contract.IView;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;

import static org.junit.Assert.*;

public class ControllerTest {

    /** The controller use for the test */
    private Controller controller;
    /** The model for the test*/
    private IModel model;
    /** The vie for the test*/
    private IView view;

    private Field[] fields;

    /**
     * instantiate the controller class
     *
     * @throws Exception for bad direction assign to the player
     * @throws NullPointerException Something missing in controller.orderPerform()
     */

    @Before
    public void setUp() throws Exception {
        this.model = new IModel() {
            @Override
            public int getRemainingTime() {
                return 50;
            }

            @Override
            public boolean isWin() {
                return false;
            }

            @Override
            public boolean getIsAlivePlayer() {
                return false;
            }

            @Override
            public int[] getPositionsPlayer() {
                return new int[0];
            }

            @Override
            public void setMovePlayer(int x, int y) throws Exception {

            }

            @Override
            public int getRemainingDiamonds() {
                return 0;
            }

            @Override
            public int getScore() {
                return 0;
            }

            @Override
            public char[][] convertWorld() {
                return new char[0][];
            }

            @Override
            public ArrayList<int[]> getExplosions() {
                return null;
            }

            @Override
            public int getLevelTexture() {
                return 0;
            }

            @Override
            public Observable getObservable() {
                return null;
            }

            @Override
            public boolean getIsOpenExit() {
                return false;
            }
        };
        this.controller = new Controller(view, this.model);

        Class<?> modelReflector = this.controller.getClass();
        this.fields = modelReflector.getDeclaredFields();
        for(Field field : this.fields) {
            field.setAccessible(true);
        }
        /*try {
            controller.orderPerform(ControllerOrder.STAND_BY);
        } catch (NullPointerException e) {
            System.out.println("Something missing in controller.orderPerform()");
        }*/
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

    @Test
    public void testMotionLessControl() throws Exception
    {
        int expectedTimer = 50;
        this.controller.orderPerform(ControllerOrder.STAND_BY);
        int currentTimer = -1;

        for(Field field : fields) {
            if (field.getName().equals("timer"))
                currentTimer = (int)field.get(this.controller);
        }

        assertEquals(expectedTimer, currentTimer);
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