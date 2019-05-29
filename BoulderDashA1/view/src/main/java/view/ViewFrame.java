package view;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * The class View Frame.
 *
 * @author Arthur Lecras
 * @see javax.swing.JFrame
 * @see contract.IView
 * @see java.awt.event.KeyListener
 * @see java.util.Observer
 *
 */
public class ViewFrame extends JFrame implements IView, KeyListener, Observer{
    /** Texture condition with respect to time. */
    private int LevelTexture = 5, LevelMaxPlayer = 2;

    /**  */
    private boolean Die, Live, Exit;

    /** View panel */
    private ViewPanel Panel;

    /** Project controller module. */
    private IController Controller;

    /** Project model module. */
    private IModel Model;

    /** Interprets the project path for images. */
    protected static String Chemin = System.getProperty("user.dir") +"\\BoulderDashA1\\model\\src\\main\\resources\\Assets\\";

    /** Frame class constructor. */
    public ViewFrame(){
        this.Model = Model;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Boulder Dash");
        try{
            this.setIconImage(ImageIO.read(new File(this.Chemin +"Personnage\\Left\\1.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
        this.setResizable(false);
        this.addKeyListener(this);
        this.setSize(512, 512);
        this.setLocationRelativeTo(null);
        this.Panel = new ViewPanel(this.getLevelTexture());
        this.setContentPane(this.Panel);
        this.setLayout(null);
        this.setVisible(true);
    }

    /** Defined to observe it from the view. */
    public void Observer(IModel Model){
        this.Model = Model;
        this.Model.getObservable().addObserver(this);
    }

    /**
     * Defines the level of the textures.
     *
     * @param LevelTexture Number of textures.
     *
     */
    protected void setLevelTexture(int LevelTexture){
        this.LevelTexture = LevelTexture;
    }

    /**
     * Recovers the level of the textures.
     *
     * @return int
     *
     */
    protected int getLevelTexture(){
        return this.LevelTexture;
    }


    /**
     * Defined the character's texture number for annimation.
     *
     * @param LevelMaxPlayer Defines the number of animation of the character.
     *
     */
    protected void setLevelMaxPlayer(int LevelMaxPlayer){
        this.LevelMaxPlayer = LevelMaxPlayer;
    }

    /**
     * Allows you to retrieve the character's texture number for annimation.
     *
     * @return int
     *
     */
    protected int getLevelMaxPlayer(){
        return this.LevelMaxPlayer;
    }



    /**
     * Defined the controller.
     *
     * @param Controller Defined the controller.
     *
     */
    public void setController(IController Controller){
        this.Controller = Controller;
    }

    /**
     * Allows you to retrieve the controller.
     *
     * @return IController
     *
     */
    public IController getControler(){
        return this.Controller;
    }

    /**
     * Define the character in fixed vision.
     *
     * @param StandBy Recovers whether the character is fixed or not.
     */
    public void setStandBy(boolean StandBy){
        if(StandBy){
            this.Panel.setControlleEtatPlayer(ControllerOrder.STAND_BY);
            this.setLevelMaxPlayer(2);
        }
    }

    /**
     * Allows to start the display of the game every 150 miliseconds.
     *
     */
    public void Run(){
        while(true){
            this.Panel.repaint();
            this.Panel.setEtatPlayerIncrement();
            this.Panel.setEtatDiamondIncrement();

            if(this.Panel.getEtatPlayer() > this.getLevelMaxPlayer()){
                this.Panel.setEtatPlayer(1);
            }

            if(this.Panel.getEtatDiamond() > 8){
                this.Panel.setEtatDiamond(1);
            }

            if(Exit){
                this.Panel.setEtatExitIncrement();
                if(this.Panel.getEtatExit() > 8){
                    this.Panel.setEtatExit(1);
                }
            }

            try{
                Thread.sleep(150);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the key code press.
     *
     * @return ControllerOrder
     * @param keyCode Detect key code.
     */
    public ControllerOrder KeyCodeToControllerOrder(int keyCode){
        this.setLevelMaxPlayer(3);
        switch(keyCode){
            case KeyEvent.VK_LEFT:
                this.Panel.setControlleEtatPlayer(ControllerOrder.LEFT);
                return ControllerOrder.LEFT;
            case KeyEvent.VK_RIGHT:
                this.Panel.setControlleEtatPlayer(ControllerOrder.RIGHT);
                return ControllerOrder.RIGHT;
            case KeyEvent.VK_UP:
                this.setLevelMaxPlayer(4);
                this.Panel.setControlleEtatPlayer(ControllerOrder.UP);
                return ControllerOrder.UP;
            case KeyEvent.VK_DOWN:
                this.setLevelMaxPlayer(4);
                this.Panel.setControlleEtatPlayer(ControllerOrder.DOWN);
                return ControllerOrder.DOWN;
            default:
                this.setLevelMaxPlayer(2);
                this.Panel.setControlleEtatPlayer(ControllerOrder.STAND_BY);
                return ControllerOrder.STAND_BY;
        }
    }

    /**
     * @see java.awt.event.KeyListener
     */
    public void keyTyped(KeyEvent KeyEvent){}

    /**
     * @see java.awt.event.KeyListener
     */
    public void keyPressed(KeyEvent KeyEventTouch){
        if(Die||Live){}else{
            try{
                this.getControler().orderPerform(this.KeyCodeToControllerOrder(KeyEventTouch.getKeyCode()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        this.Panel.repaint();
    }

    /**
     * @see java.awt.event.KeyListener
     */
    public void keyReleased(KeyEvent KeyEvent){}

    /**
     * @see java.util.Observer
     */
    public void update(Observable Observable, Object arg){
        this.Panel.setCurrentWorld(this.Model.convertWorld());

        this.Panel.setRemainingTime(this.Model.getRemainingTime());
        this.Panel.setRemainingDiamonds(this.Model.getRemainingDiamonds());
        this.Panel.setScore(this.Model.getScore());

        this.setLevelTexture(this.Model.getLevelTexture());
        this.Panel.setLevels(this.Model.getLevelTexture());

        if(this.Model.isWin()){
            this.setLevelMaxPlayer(2);
            this.Panel.setEtatPlayer(1);
            this.Panel.setControlleEtatPlayer(ControllerOrder.WIN);
            this.Panel.setLive(true);
            this.Live = true;
        }

        int[] Player = this.Model.getPositionsPlayer();

        this.Panel.setPlayerX(Player[0]);
        this.Panel.setPlayerY(Player[1]);

        if(!(this.Model.getIsAlivePlayer())){
            this.setLevelMaxPlayer(2);
            this.Panel.setEtatPlayer(1);
            this.Panel.setControlleEtatPlayer(ControllerOrder.LOSE);
            this.Panel.setDie(true);
            this.Die = true;
        }

        if(!this.Model.getExplosions().isEmpty()){
            this.Panel.setExplosions(this.Model.getExplosions());
            this.Panel.setExploid(true);
        }else{
            this.Panel.setExploid(false);
        }

        this.Panel.Modifie();
        this.repaint();
    }
}