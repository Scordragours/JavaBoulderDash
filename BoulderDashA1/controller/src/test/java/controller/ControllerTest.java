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
     * Use the reflexion in Java
     *
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
    }

    /**
     * Test method for {@link Controller#orderPerform(ControllerOrder)}
     * when we use default switch case and so when Controller#MotionLessControl(boolean)
     * have "true" set as parameter.
     *
     * @throws Exception for bad direction assign to the player
     *
     */

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
}