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

public class ViewFrame extends JFrame implements IView, KeyListener, Observer{
    private int LevelTexture = 3, LevelMaxPlayer = 2;
    private boolean Die, Live, Exit;
    private ViewPanel Panel;
    private IController Controller;
    private IModel Model;
    protected static String Chemin = System.getProperty("user.dir") +"\\BoulderDashA1\\model\\src\\main\\resources\\Assets\\";

    public ViewFrame(IModel Model){
        System.out.println(this.Chemin);
        this.Model = Model;
        this.Model.getObservable().addObserver(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Boolder Dash");
        try{
            this.setIconImage(ImageIO.read(new File(this.Chemin +"Personnage\\Left\\1.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
        this.setResizable(false);
        this.addKeyListener(this);
        this.setSize(512, 512);
        this.setLocationRelativeTo(null);
        this.Panel = new ViewPanel(this.LevelTexture);
        this.setContentPane(this.Panel);
        this.setLayout(null);
        this.setVisible(true);
    }


    private void setLevelTexture(int LevelTexture){
        this.LevelTexture = LevelTexture;
    }
    private void setLevelMaxPlayer(int LevelMaxPlayer){
        this.LevelMaxPlayer = LevelMaxPlayer;
    }
    private int getLevelMaxPlayer(){
        return this.LevelMaxPlayer;
    }
    public void setController(IController Controller){
        this.Controller = Controller;
    }
    public IController getControler(){
        return this.Controller;
    }

    public void setStandBy(boolean StandBy){
        if(StandBy){
            this.Panel.setControlleEtatPlayer(ControllerOrder.STAND_BY);
            this.setLevelMaxPlayer(2);
        }
    }

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
    protected ControllerOrder KeyCodeToControllerOrder(final int keyCode){
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
    public void keyTyped(KeyEvent KeyEvent){}
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
    public void keyReleased(KeyEvent KeyEvent){}
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

        this.repaint();
    }
}