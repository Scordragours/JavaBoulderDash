package view;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private int LevelTexture = 1, LevelMaxPlayer = 2;

    /** Previous score */
    private int PreviousScore = 0;

    /**  States of the game. */
    private boolean Die, Live, Exit, SoundExit = true;

    /** View panel */
    private ViewPanel Panel;

    /** Project controller module. */
    private IController Controller;

    /** Project model module. */
    private IModel Model;

    /** Interprets the project path for images. */
    private static String Path = System.getProperty("user.dir") +"\\BoulderDashA1\\model\\src\\main\\resources\\Assets\\";

    /** Frame class constructor. */
    public ViewFrame(){
        try{
            InputStream in = new FileInputStream(this.Path +"\\Sounds\\Atmosphere.wav");
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        }catch(IOException e){
            System.err.println(e);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Boulder Dash");
        this.setResizable(false);
        this.addKeyListener(this);
        this.setSize(512, 512);
        this.setLocationRelativeTo(null);
    }

    /**
     * Defined to observe it from the view.
     *
     * @param Model Defines the model and Observer. {@link contract.IModel}
     *
     */
    public void Observer(IModel Model){
        try{
            this.setIconImage(ImageIO.read(new File(this.Path +"\\Personnage\\Left\\1.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
        this.Panel = new ViewPanel(this.getLevelTexture());
        this.setContentPane(this.Panel);
        this.setLayout(null);
        this.setVisible(true);
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
     * Defines the previous score.
     *
     * @param PreviousScore Previous score.
     *
     */
    protected void setPreviousScore(int PreviousScore){
        this.PreviousScore = PreviousScore;
    }

    /**
     * Recovers the previous score.
     *
     * @return int
     *
     */
    protected int getPreviousScore(){
        return this.PreviousScore;
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
     * @param Controller Defined the controller. {@link contract.IController}
     *
     */
    public void setController(IController Controller){
        this.Controller = Controller;
    }

    /**
     * Allows you to retrieve the model.
     *
     * @return IController {@link contract.IModel}
     *
     */
    public IModel getModel(){
        return this.Model;
    }

    /**
     * Defined the model.
     *
     * @param Model Defined the model. {@link contract.IModel}
     *
     */
    public void setModel(IModel Model){
        this.Model = Model;
    }

    /**
     * Allows you to retrieve the controller.
     *
     * @return IController {@link contract.IController}
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
            this.Panel.setControlleStatePlayer(ControllerOrder.STAND_BY);
            this.setLevelMaxPlayer(2);
            this.Panel.setStatePlayer(1);
            this.repaint();
        }
    }

    /**
     * Allows to start the display of the game every 150 miliseconds.
     *
     */
    public void Run(){
        while(true){
            this.Panel.repaint();
            this.Panel.setStatePlayerIncrement();
            this.Panel.setStateDiamondIncrement();

            if(this.Panel.getStatePlayer() > this.getLevelMaxPlayer()){
                this.Panel.setStatePlayer(1);
            }

            if(this.Panel.getStateDiamond() > 8){
                this.Panel.setStateDiamond(1);
            }

            if(Exit){
                this.Panel.setStateExitIncrement();
                if(this.Panel.getStateExit() > 8){
                    this.Panel.setStateExit(1);
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
     * @param keyCode Detect key code. {@link contract.ControllerOrder}
     */
    public ControllerOrder KeyCodeToControllerOrder(int keyCode){
        try{
            InputStream in = new FileInputStream(this.Path  +"\\Sounds\\Movement.wav");
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        }catch(IOException e){
            System.err.println(e);
        }
        this.setLevelMaxPlayer(3);
        this.Panel.setStatePlayer(1);
        switch(keyCode){
            case KeyEvent.VK_LEFT:
                this.Panel.setControlleStatePlayer(ControllerOrder.LEFT);
                return ControllerOrder.LEFT;
            case KeyEvent.VK_RIGHT:
                this.Panel.setControlleStatePlayer(ControllerOrder.RIGHT);
                return ControllerOrder.RIGHT;
            case KeyEvent.VK_UP:
                this.setLevelMaxPlayer(4);
                this.Panel.setControlleStatePlayer(ControllerOrder.UP);
                return ControllerOrder.UP;
            case KeyEvent.VK_DOWN:
                this.setLevelMaxPlayer(4);
                this.Panel.setControlleStatePlayer(ControllerOrder.DOWN);
                return ControllerOrder.DOWN;
            default:
                this.setLevelMaxPlayer(2);
                this.Panel.setControlleStatePlayer(ControllerOrder.STAND_BY);
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
        if(this.Model.getRemainingDiamonds() == 0){
            if(SoundExit){
                try{
                    InputStream in = new FileInputStream(this.Path +"\\Sounds\\Exit_Unlock.wav");
                    AudioStream as = new AudioStream(in);
                    AudioPlayer.player.start(as);
                }catch(IOException e){
                    System.err.println(e);
                }
                this.SoundExit = false;
            }
            this.Panel.setExitPossible(true);
            this.Exit = true;
        }
        this.Panel.setScore(this.Model.getScore());
        if(this.Model.getScore() != this.getPreviousScore()){
            this.setPreviousScore(this.Model.getScore());
            try{
                InputStream in = new FileInputStream(this.Path +"\\Sounds\\Take_diamond.wav");
                AudioStream as = new AudioStream(in);
                AudioPlayer.player.start(as);
            }catch(IOException e){
                System.err.println(e);
            }
        }

        this.setLevelTexture(this.Model.getLevelTexture());
        this.Panel.setLevels(this.Model.getLevelTexture());

        if(this.Model.isWin()){
            this.setLevelMaxPlayer(2);
            this.Panel.setStatePlayer(1);
            this.Panel.setControlleStatePlayer(ControllerOrder.WIN);
            this.Panel.setWinBoolean(true);
            this.Live = true;
        }

        int[] Player = this.Model.getPositionsPlayer();

        this.Panel.setPlayerX(Player[0]);
        this.Panel.setPlayerY(Player[1]);

        if(!(this.Model.getIsAlivePlayer())){
            this.setLevelMaxPlayer(2);
            this.Panel.setStatePlayer(1);
            this.Panel.setControlleStatePlayer(ControllerOrder.LOSE);
            this.Panel.setDie(true);
            this.Die = true;
        }

        if(!this.Model.getExplosions().isEmpty())
        {
            try{
                InputStream in = new FileInputStream(this.Path +"\\Sounds\\Exploid.wav");
                AudioStream as = new AudioStream(in);
                AudioPlayer.player.start(as);
            }catch(IOException e){
                System.err.println(e);
            }
            while(!this.Model.getExplosions().isEmpty()){
                int[] removedExp = this.Model.getExplosions().remove(0);
                this.Panel.addExplosion(new int[]{removedExp[0],removedExp[1],1});
                this.Panel.setExploid(true);
            }
        }

        this.Panel.Modify();
        this.repaint();
    }
}