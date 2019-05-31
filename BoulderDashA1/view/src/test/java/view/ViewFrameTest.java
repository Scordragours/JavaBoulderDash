package view;

import contract.IModel;
import org.junit.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;
import static org.junit.Assert.*;

/**
 * Entity Test
 * @author Arthur Lecras
 */
public class ViewFrameTest{
    protected ViewFrame ViewFrame;
    protected IModel Model;

    private Field[] fields;

    /**
     * SetUp the junit tests of ViewFrame class.
     */
    @Before
    public void setUp(){
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
            public void setMovePlayer(int x, int y) throws Exception {}
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

        this.ViewFrame = new ViewFrame();

        Class<?> modelReflector = this.ViewFrame.getClass();
        this.fields = modelReflector.getDeclaredFields();
        for(Field field : this.fields) {
            field.setAccessible(true);
        }
    }

    @After
    public void tearDown() throws Exception{}

    /**
     * Test method for {@link ViewFrame#setLevelMaxPlayer(int)}
     * @throws Exception bad value
     */
    @Test
    public void testSetLevelMaxPlayer() throws Exception{
        int expected = 0;
        this.ViewFrame.setLevelMaxPlayer(expected);
        int current = 0;
        for(Field field : fields){
            if(field.getName().equals("LevelMaxPlayer")){
                current = (int) field.get(this.ViewFrame);
            }
        }
        assertEquals(expected, current);
    }

    /**
     * Test method for {@link ViewFrame#getLevelMaxPlayer()}
     * @throws Exception bad value
     */
    @Test
    public void testGetLevelMaxPlayer() throws Exception{
        int expected = 0;
        for(Field field : fields){
            if(field.getName().equals("LevelMaxPlayer")){
                expected = (int) field.get(this.ViewFrame);
            }
        }
        assertEquals(expected,this.ViewFrame.getLevelMaxPlayer());
    }
}