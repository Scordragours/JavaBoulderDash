package view;

import model.Entity;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import static org.junit.Assert.*;

/**
 * Entity Test
 * @author Arthur Lecras
 */
public class ViewPanelTest{
    private ViewPanel ViewPanel;
    private Field[] fields;

    /** SetUp the junit tests of ViewPanel class. */
    @Before
    public void setUp(){
        this.ViewPanel = new ViewPanel(1);

        Class<?> modelReflector = this.ViewPanel.getClass();
        this.fields = modelReflector.getDeclaredFields();
        for(Field field : this.fields) {
            field.setAccessible(true);
        }
    }

    @After
    public void tearDown() throws Exception{
    }

    /**
     * Test method for {@link ViewPanel#setStatePlayer(int)}
     * @throws Exception bad value
     */
    @Test
    public void setStatePlayer() throws Exception {
        int expected = 0;
        this.ViewPanel.setStatePlayer(expected);
        int current = 0;
        for(Field field : fields){
            if(field.getName().equals("StatePlayer")){
                current = (int) field.get(this.ViewPanel);
            }
        }
        assertEquals(expected, current);
    }

    /**
     * Test method for {@link ViewPanel#setStatePlayerIncrement()}
     * @throws Exception bad value
     */
    @Test
    public void setStatePlayerIncrement() throws Exception{
        int expected = 1;
        int current = 0;
        this.ViewPanel.setStatePlayer(current);
        this.ViewPanel.setStatePlayerIncrement();
        for(Field field : fields){
            if(field.getName().equals("StatePlayer")){
                current = (int) field.get(this.ViewPanel);
            }
        }
        assertEquals(expected, current);
    }

    /**
     * Test method for {@link ViewPanel#getStatePlayer()}
     * @throws Exception bad value
     */
    @Test
    public void getStatePlayer() throws Exception{
        int expected = 0;
        for(Field field : fields){
            if(field.getName().equals("StatePlayer")){
                expected = (int) field.get(this.ViewPanel);
            }
        }
        assertEquals(expected,this.ViewPanel.getStatePlayer());
    }

    /**
     * Test method for {@link ViewPanel#setStateDiamond(int)}
     * @throws Exception bad value
     */
    @Test
    public void setStateDiamond() throws Exception{
        int expected = 0;
        this.ViewPanel.setStateDiamond(expected);
        int current = 0;
        for(Field field : fields){
            if(field.getName().equals("StateDiamond")){
                current = (int) field.get(this.ViewPanel);
            }
        }
        assertEquals(expected, current);
    }

    /**
     * Test method for {@link ViewPanel#setStateDiamondIncrement()}
     * @throws Exception bad value
     */
    @Test
    public void setStateDiamondIncrement() throws Exception{
        int expected = 1;
        int current = 0;
        this.ViewPanel.setStateDiamond(current);
        this.ViewPanel.setStateDiamondIncrement();
        for(Field field : fields){
            if(field.getName().equals("StateDiamond")){
                current = (int) field.get(this.ViewPanel);
            }
        }
        assertEquals(expected, current);
    }

    /**
     * Test method for {@link ViewPanel#getStateDiamond()}
     * @throws Exception bad value
     */
    @Test
    public void getStateDiamond() throws Exception{
        int expected = 0;
        for(Field field : fields){
            if(field.getName().equals("StateDiamond")){
                expected = (int) field.get(this.ViewPanel);
            }
        }
        assertEquals(expected,this.ViewPanel.getStateDiamond());
    }

    /**
     * Test method for {@link ViewPanel#setStateExit(int)}
     * @throws Exception bad value
     */
    @Test
    public void setStateExit() throws Exception{
        int expected = 0;
        this.ViewPanel.setStateExit(expected);
        int current = 0;
        for(Field field : fields){
            if(field.getName().equals("StateExit")){
                current = (int) field.get(this.ViewPanel);
            }
        }
        assertEquals(expected, current);
    }

    /**
     * Test method for {@link ViewPanel#setStateExitIncrement()}
     * @throws Exception bad value
     */
    @Test
    public void setStateExitIncrement() throws Exception{
        int expected = 1;
        int current = 0;
        this.ViewPanel.setStateExit(current);
        this.ViewPanel.setStateExitIncrement();
        for(Field field : fields){
            if(field.getName().equals("StateExit")){
                current = (int) field.get(this.ViewPanel);
            }
        }
        assertEquals(expected, current);
    }

    /**
     * Test method for {@link ViewPanel#getStateExit()}
     * @throws Exception bad value
     */
    @Test
    public void getStateExit() throws Exception{
        int expected = 0;
        for(Field field : fields){
            if(field.getName().equals("StateExit")){
                expected = (int) field.get(this.ViewPanel);
            }
        }
        assertEquals(expected,this.ViewPanel.getStateExit());
    }
}