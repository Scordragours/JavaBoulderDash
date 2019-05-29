package view;

import contract.IModel;
import org.junit.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ViewFrameTest{
    protected ViewFrame ViewFrame1;
    protected IModel Model;

    private Field[] fields;

    @Before
    public void setUp() throws Exception{
        this.Model = new IModel(){
            @Override
            public int getRemainingTime() {
                return 0;
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

        this.ViewFrame1 = new ViewFrame();

        Class<?> modelReflector = this.ViewFrame1.getClass();
        this.fields = modelReflector.getDeclaredFields();
        for(Field field : this.fields) {
            field.setAccessible(true);
        }

    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void setLevelTexture(){
        final int expected = 0;
        this.ViewFrame1.setLevelTexture(0);
        assertEquals(expected, this.ViewFrame1.getLevelTexture());
    }

    @Test
    public void getLevelTexture(){
    }

    @Test
    public void setLevelMaxPlayer(){
    }

    @Test
    public void getLevelMaxPlayer(){

    }
}