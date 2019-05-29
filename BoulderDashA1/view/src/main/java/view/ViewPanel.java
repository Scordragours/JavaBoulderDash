package view;

import contract.ControllerOrder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/** */
public class ViewPanel extends JPanel{
    private BufferedImage Breakable, Delemitation_Block, Background, GameOver;
    private int Levels, PlayerX, PlayerY, EtatPlayer = 1, EtatDiamond = 1, EtatExit = 1;
    private boolean Die = false, Live = false, Exploid = false;
    private ArrayList<int[]> Explosions = new ArrayList<>();
    private char[][] CurrentWorld = new char[26][48];
    private ViewLabel LabelScore, LabelTime, LabelDiamond;
    private ControllerOrder ControlleEtatPlayer = ControllerOrder.STAND_BY;

    public ViewPanel(int Levels){
        this.Levels = Levels;
        this.Modifie();

        Font FontLabel = new Font("TimesRoman", Font.BOLD, 25);

        this.LabelScore = new ViewLabel("000000", 150, 40, ViewFrame.Chemin +"Interface\\ScoreBoard.png");
        this.LabelScore.setHorizontalAlignment(SwingConstants.CENTER);
        this.LabelScore.setBounds(188, 10, 150, 40);
        this.LabelScore.setFont(FontLabel);
        this.LabelScore.setForeground(Color.WHITE);
        this.add(this.LabelScore);

        this.LabelTime = new ViewLabel("        000", 113, 40, ViewFrame.Chemin +"\\Interface\\ClockBoard.png");
        this.LabelTime.setBounds(7, 64, 113, 40);
        this.LabelTime.setFont(FontLabel);
        this.LabelTime.setForeground(Color.WHITE);
        this.add(LabelTime);

        this.LabelDiamond = new ViewLabel("        060", 113, 40, ViewFrame.Chemin +"\\Interface\\DiamondBoard.png");
        this.LabelDiamond.setBounds(7, 112, 113, 40);
        this.LabelDiamond.setFont(FontLabel);
        this.LabelDiamond.setForeground(Color.WHITE);
        this.add(this.LabelDiamond);
    }

    protected void setCurrentWorld(char[][] CurrentWorld){
        this.CurrentWorld = CurrentWorld;
    }
    protected char[][] getCurrentWorld(){
        return this.CurrentWorld;
    }
    protected char getCurrentWorldChar(int X, int Y){
        return this.CurrentWorld[Y][X];
    }

    protected void setLevels(int Levels){
        this.Levels = Levels;
    }


    protected void setPlayerX(int PlayerX){
        this.PlayerX = PlayerX;
    }
    protected int getPlayerX(){
        return this.PlayerX;
    }


    protected void setPlayerY(int PlayerY){
        this.PlayerY = PlayerY;
    }
    protected int getPlayerY(){
        return this.PlayerY;
    }


    protected void setDie(boolean Die){
        this.Die = Die;
    }
    protected boolean getDie(){
        return this.Die;
    }


    protected void setLive(boolean Live){
        this.Live = Live;
    }
    protected boolean getLive(){
        return this.Live;
    }


    protected void setExploid(boolean Exploid){
        this.Exploid = Exploid;
    }
    protected boolean getExploid(){
        return this.Exploid;
    }


    protected void setExplosions(ArrayList<int[]> Explosions){
        this.Explosions = Explosions;
    }
    protected ArrayList<int[]> getExplosions(){
        return this.Explosions;
    }

    protected void setEtatPlayer(int EtatPlayer){
        this.EtatPlayer = EtatPlayer;
    }
    protected void setEtatPlayerIncrement(){
        this.EtatPlayer++;
    }
    protected int getEtatPlayer(){
        return this.EtatPlayer;
    }

    protected void setEtatDiamond(int EtatDiamond){
        this.EtatDiamond = EtatDiamond;
    }
    protected void setEtatDiamondIncrement(){
        this.EtatDiamond++;
    }
    protected int getEtatDiamond(){
        return this.EtatDiamond;
    }

    protected void setEtatExit(int EtatExit){
        this.EtatExit = EtatExit;
    }
    protected void setEtatExitIncrement(){
        this.EtatExit++;
    }
    protected int getEtatExit(){
        return this.EtatExit;
    }


    protected void setRemainingTime(int RemainingTime){
        this.LabelTime.setText("        "+ RemainingTime);
    }
    protected void setRemainingDiamonds(int RemainingDiamonds){
        this.LabelDiamond.setText("        "+ RemainingDiamonds);
    }
    protected void setScore(int Score){
        this.LabelScore.setText(""+ Score);
    }

    protected void setControlleEtatPlayer(ControllerOrder ControllerOrder){
        this.ControlleEtatPlayer = ControllerOrder;
    }
    protected ControllerOrder getControlleEtatPlayer(){
        return this.ControlleEtatPlayer;
    }


    protected void Modifie(){
        try{
            this.Breakable = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Breakable\\1.png"));
            this.Delemitation_Block = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Outline\\1.png"));
            this.Background = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Background\\1.png"));
            this.GameOver = ImageIO.read(new File(ViewFrame.Chemin +"\\Interface\\Game_Over.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics Graphic){
        for(int y = 0; y < 526; y+= 32){
            for(int x = 0; x < 526; x+= 32){
                Graphic.drawImage(this.Background, x, y, 32, 32, this);
            }
        }
        int Y1 = 0, X1 = 0, X2 = 0, Y2 = 0;
        if(this.getPlayerX()*32 - 256 <= 0){
            X1 = 0;
            X2 = 512;
        }else if(this.getPlayerX()*32 + 256 >= 1536){
            X1 = 1024;
            X2 = 1536;
        }else{
            X1 = this.getPlayerX()*32 - 256;
            X2 = this.getPlayerX()*32 + 256;
        }
        if(this.getPlayerY()*32 - 256 <= 0){
            Y1 = 0;
            Y2 = 512;
        }else if(this.getPlayerY()*32 + 256 >= 832){
            Y1 = 320;
            Y2 = 832;
        }else{
            Y1 = this.getPlayerY()*32 - 256;
            Y2 = this.getPlayerY()*32 + 256;
        }
        for(int y = Y1; y < Y2; y += 32){
            for(int x = X1; x < X2; x += 32){
                if(this.Explosions.size() != 0){
                    BufferedImage Image = this.TestCase(this.getCurrentWorldChar(x/32, y/32));
                    boolean Test = false;
                    for(int i = 0; i <= this.Explosions.size()-1; i++){
                        int[] ExplosionXY = this.Explosions.get(i);
                        if(!Test){
                            if((this.getExploid())&&(ExplosionXY[0]*32 == x)&&(ExplosionXY[1]*32 == y)){
                                Test = true;
                                Image = this.AnimationDie();
                            }
                        }
                    }
                    Graphic.drawImage(Image, x - X1, y - Y1, 32, 32, this);
                    if((this.getExploid())&&(this.getPlayerX() == x/32)&&(this.getPlayerY() == y/32)){
                        Graphic.drawImage(this.TestCase(this.getCurrentWorldChar(x/32, y/32)), x - X1, y - Y1, 32, 32, this);
                    }
                    Test = false;
                }else if(this.getLive()){

                }else{
                    BufferedImage Image = this.TestCase(this.getCurrentWorldChar(x/32, y/32));
                    Graphic.drawImage(Image, x - X1, y - Y1, 32, 32, this);
                }
            }
        }
        if(this.getDie()){
            Graphic.drawImage(this.TestCase('G'), 0, 0, 512, 512, this);
        }
    }

    private BufferedImage TestCase(char Char){
        BufferedImage Image = null;
        switch(Char){
            case 'P':
                switch(this.getControlleEtatPlayer()){
                    case LEFT:
                        Image = this.AnimationPeronnage("Left");
                        break;
                    case RIGHT:
                        Image = this.AnimationPeronnage("Right");
                        break;
                    case UP:
                        Image = this.AnimationPeronnage("Up");
                        break;
                    case DOWN:
                        Image = this.AnimationPeronnage("Down");
                        break;
                    case STAND_BY:
                        Image = this.AnimationPeronnage("Motionless");
                        break;
                    case LOSE:
                        Image = this.AnimationPeronnage("Lose");
                        break;
                    case WIN:
                        Image = this.AnimationPeronnage("Win");
                        break;
                }
                break;
            case 'B':
                Image = this.Breakable;
                break;
            case 'O':
                Image = this.Delemitation_Block;
                break;
            case 'I':
                Image = this.Delemitation_Block;
                break;
            case 'R':
                Image = this.AnimationGravity();
                break;
            case 'D':
                Image = this.AnimationDiamond();
                break;
            case 'E':
                Image = this.AnimationExit();
                break;
            case '1':
                Image = this.AnimationEnnemy(1);
                break;
            case '2':
                Image = this.AnimationEnnemy(2);
                break;
            case ' ':
                // Nothing.
                break;
            case 'G':
                Image = this.GameOver;
                break;
        }
        return Image;
    }

    private BufferedImage AnimationExit(){
        try{
            BufferedImage Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Delemitation_Block_1\\1.png"));
            switch(this.getEtatExit()){
                case 1:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Delemitation_Block_1\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Delemitation_Block_1\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Delemitation_Block_1\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Delemitation_Block_1\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage AnimationEnnemy(int N){
        try{
            BufferedImage Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Enemy\\"+ N +"\\1.png"));
            switch(this.getEtatDiamond()){
                case 1:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Enemy\\"+ N +"\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Enemy\\"+ N +"\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Enemy\\"+ N +"\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Enemy\\"+ N +"\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage AnimationDie(){
        try{
            BufferedImage Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Star\\1.png"));
            switch(this.getEtatPlayer()){
                case 1:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Star\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Star\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Star\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Star\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage AnimationDiamond(){
        try{
            BufferedImage Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Diamond\\1.png"));
            switch(this.getEtatDiamond()){
                case 1:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Diamond\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Diamond\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Diamond\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Diamond\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage AnimationGravity(){
        try{
            BufferedImage Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Gravity\\1.png"));
            switch(this.getEtatPlayer()){
                case 1:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Gravity\\1.png"));
                    break;
                case 2:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Gravity\\2.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Gravity\\3.png"));
                    break;
                case 4:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Level_"+ this.Levels +"\\Gravity\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage AnimationPeronnage(String Repertory){
        try{
            BufferedImage Image = ImageIO.read(new File(ViewFrame.Chemin +"Personnage\\"+ Repertory +"\\1.png"));
            switch(this.getEtatPlayer()){
                case 1:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Personnage\\"+ Repertory +"\\1.png"));
                    break;
                case 2:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Personnage\\"+ Repertory +"\\2.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Personnage\\"+ Repertory +"\\3.png"));
                    break;
                case 4:
                    Image = ImageIO.read(new File(ViewFrame.Chemin +"Personnage\\"+ Repertory +"\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}