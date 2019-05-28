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
    private boolean Die, Live;
    private ViewPanel Panel;
    private IController Controller;
    private IModel Model;

    public ViewFrame(IModel Model){
        this.Model = Model;
        this.Model.getObservable().addObserver(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Boolder Dash");
        try{
            this.setIconImage(ImageIO.read(new File("..\\model\\Assets\\Ressource\\Personnage\\Left\\1.png")));
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
    public void setStandBy(boolean StandBy){
        if(StandBy){
            this.Panel.setControlleEtatPlayer(ControllerOrder.STAND_BY);
            this.setLevelMaxPlayer(2);
        }
    }
    public void keyTyped(KeyEvent KeyEvent){}
    public void keyPressed(KeyEvent KeyEventTouch){
        if(Die||Live){

        }else{
            try{
                this.getControler().orderPerform(this.KeyCodeToControllerOrder(KeyEventTouch.getKeyCode()));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
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
            this.Panel.setLive(true);
            this.Live = true;
        }

        this.Panel.setPlayerX(this.Model.getPlayer().getPositionX());
        this.Panel.setPlayerY(this.Model.getPlayer().getPositionY());

        if(!(this.Model.getPlayer().isAlive())){
            this.Panel.setDie(true);
            this.Die = true;
        }

        if(!this.Model.getExplosions().isEmpty()){
            this.Panel.setExplosions(this.Model.getExplosions());
            this.Panel.setExploid(true);
        }
        this.repaint();
    }
}