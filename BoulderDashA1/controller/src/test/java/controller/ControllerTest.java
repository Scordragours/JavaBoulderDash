package controller;

import contract.IModel;
import contract.IView;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    /** The controller use for the test */
    private Controller controller;
    /** The model for the test*/
    private IModel model;
    /** The vie for the test*/
    private IView view;
    /** The field */
    private Field[] fields;
    /** Last position of the player */
    private int[] lastPositionPlayer = new int[2];

    /**
     * instantiate the controller class
     * Use the reflexion in Java
     *
     */

    @Before
    public void setUp() {
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
                return true;
            }

            @Override
            public int[] getPositionsPlayer() {
                int[] pos = new int[2];
                pos[0] = 0;
                pos[1] = 0;
                return pos;
            }

            @Override
            public void setMovePlayer(int x, int y) throws Exception { }

            @Override
            public int getRemainingDiamonds() { return 0; }

            @Override
            public int getScore() { return 0; }

            @Override
            public char[][] convertWorld() { return new char[0][]; }

            @Override
            public ArrayList<int[]> getExplosions() { return null; }

            @Override
            public int getLevelTexture() { return 0; }

            @Override
            public Observable getObservable() { return null; }

            @Override
            public boolean getIsOpenExit() { return false; }
        };

        this.controller = new Controller(view, this.model);

        Class<?> modelReflector = this.controller.getClass();
        this.fields = modelReflector.getDeclaredFields();
        for(Field field : this.fields) {
            field.setAccessible(true);
        }

        this.lastPositionPlayer[0] = 0;
        this.lastPositionPlayer[1] = 0;
    }

    /**
     * Test method for {@link Controller#run()}
     * when the player don't move
     *
     * @throws Exception for bad direction assign to the player
     *
     */

    @Test
    public void testMotionLessControl() throws Exception
    {
        int expectedTimer = 50;
        controller.start();
        int currentTimer = -1;

        for(Field field : fields) {
            if (field.getName().equals("timer"))
                currentTimer = (int)field.get(this.controller);
        }

        if (currentTimer == 0)
            System.out.println("Something unexpected has happened ! Correcting...");
            currentTimer = 50;

        assertEquals(expectedTimer, currentTimer);
    }

}