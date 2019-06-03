package controller;

import contract.IModel;
import contract.IView;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Observable;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * The ControllerTest class.
 *
 * @author Nathan PORET and Etienne CANDAT
 */
public class ControllerTest {

    /** The controller for the test */
    private Controller controller;
    /** The model for the test */
    private IModel model;
    /** The view for the test */
    private IView view;
    /** The reflected fields of the Controller class */
    private Field[] fields;
    /** The reflected methods of the Controller class */
    private Method[] methods;
    /** The reflected fields of the local class "NewModel" in {@link #setUp()} */
    private Field[] modelFields;

    /**
     * Instantiates new Controller, Model, View.
     * Use the reflection in Java.
     *
     * @see java.lang.reflect.Field
     * @see java.lang.reflect.Method
     */
    @Before
    public void setUp() throws Exception {
        class NewModel implements IModel
        {
            private int remainingTime;
            private int[] positionPlayer;

            private NewModel(int remainingTime, int[] positions)
            {
                this.remainingTime = remainingTime;
                this.positionPlayer = positions;
            }

            @Override
            public int getRemainingTime() {
                return this.remainingTime;
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
                return this.positionPlayer;
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
        }
        this.model = new NewModel(50, new int[]{5,5});

        this.view = new IView() {
            @Override
            public void setStandBy(boolean StandBy) {

            }
        };

        Class<?> newModelReflector = NewModel.class;
        this.modelFields = newModelReflector.getDeclaredFields();
        for(Field field : this.modelFields) {
            field.setAccessible(true);
        }

        this.controller = new Controller(this.view, this.model);

        Class<?> controllerReflector = this.controller.getClass();
        this.fields = controllerReflector.getDeclaredFields();
        this.methods = controllerReflector.getDeclaredMethods();
        for(Field field : this.fields) {
            field.setAccessible(true);
        }
        for(Method method : this.methods)
        {
            method.setAccessible(true);
        }

        for (Field field : fields) {
            if (field.getName().equals("lastPositionPlayer"))
            {
                field.set(this.controller, model.getPositionsPlayer());
            }
        }
    }

    /**
     * Test method for {@link Controller#MotionLessControl(boolean)}.
     *
     * @throws Exception for bad direction assign to the player
     */
    @Test
    public void testMotionLessControl() throws Exception
    {
        int expectedTimer = 50;
        int currentTimer = -1;
        for(Method method : this.methods)
        {
            if(method.getName().equals("MotionLessControl"))
            {
                method.invoke(this.controller, true);
            }
        }

        for (Field field : fields) {
            if (field.getName().equals("timer"))
                currentTimer = (int) field.get(this.controller);
        }

        assertEquals(expectedTimer, currentTimer);

        expectedTimer = 0;

        for(Method method : this.methods)
        {
            if(method.getName().equals("MotionLessControl"))
            {
                method.invoke(this.controller, false);
            }
        }

        for (Field field : fields) {
            if (field.getName().equals("timer"))
                currentTimer = (int) field.get(this.controller);
        }

        assertEquals(expectedTimer, currentTimer);
    }

    /**
     * Test method for {@link Controller#run()}.
     *
     * @throws Exception for bad direction assign to the player
     */
    @Test
    public void testRun() throws Exception
    {
        int[] expectedPositions = new int[]{5,5};
        int[] currentPositions = new int[2];

        controller.start();
        Controller.sleep(1);

        for (Field field : fields) {
            if (field.getName().equals("lastPositionPlayer"))
                currentPositions = (int[]) field.get(this.controller);
        }

        assertArrayEquals(expectedPositions, currentPositions);


        for (Field field : this.modelFields) {
            if (field.getName().equals("positionPlayer"))
            {
                field.set(this.model, new int[]{6,8});
            }
        }

        expectedPositions = new int[]{6,8};
        currentPositions = new int[2];

        this.controller = new Controller(this.view, this.model);
        controller.start();
        Controller.sleep(1);

        for (Field field : fields) {
            if (field.getName().equals("lastPositionPlayer"))
                currentPositions = (int[]) field.get(this.controller);
        }

        assertArrayEquals(expectedPositions, currentPositions);
    }

}