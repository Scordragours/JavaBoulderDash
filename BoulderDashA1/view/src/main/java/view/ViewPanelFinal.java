package view;

import contract.ControllerOrder;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ViewPanelFinal extends JPanel{
    private BufferedImage Breakable, Delemitation_Block, Background;
    private int Levels, PlayerX, PlayerY, EtatPlayer = 1, EtatDiamond = 1;
    private boolean Die = true, Live = false, Exploid = true;
    private ArrayList<int[]> Explosions;
    private char[][] CurrentWorld;
    private ViewLabel LabelScore, LabelTime, LabelDiamond;
    private ControllerOrder ControlleEtatPlayer = ControllerOrder.STAND_BY;

    public ViewPanelFinal(int Levels){
        this.Levels = Levels;
        this.Modifie(Levels);

        Font FontLabel = new Font("TimesRoman", Font.BOLD, 25);

        this.LabelScore = new ViewLabel("000000", 150, 40, "C:\\Users\\lecra\\Desktop\\Ressource\\Interface\\ScoreBoard.png");
        this.LabelScore.setHorizontalAlignment(SwingConstants.CENTER);
        this.LabelScore.setBounds(188, 10, 150, 40);
        this.LabelScore.setFont(FontLabel);
        this.LabelScore.setForeground(Color.WHITE);
        this.add(this.LabelScore);

        this.LabelTime = new ViewLabel("        000", 113, 40, "C:\\Users\\lecra\\Desktop\\Ressource\\Interface\\DiamondBoard.png");
        this.LabelTime.setBounds(7, 64, 113, 40);
        this.LabelTime.setFont(FontLabel);
        this.LabelTime.setForeground(Color.WHITE);
        this.add(LabelTime);

        this.LabelDiamond = new ViewLabel("        060", 113, 40, "C:\\Users\\lecra\\Desktop\\Ressource\\Interface\\ClockBoard.png");
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


    protected void setRemainingTime(int RemainingTime){
        this.LabelTime.setText("        "+ RemainingTime);
    }
    protected void setRemainingDiamonds(int RemainingDiamonds){
        this.LabelDiamond.setText("     "+ RemainingDiamonds);
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


    protected void Modifie(int Levels){
        try{
            this.Breakable = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Breakable\\1.png"));
            this.Delemitation_Block = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Outline\\1.png"));
            this.Background = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Background\\1.png"));
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
                    BufferedImage Image = this.TestCase(this.CurrentWorld[y/32][x/32]);
                    boolean Test = false;
                    for(int i = 0; i <= this.Explosions.size()-1; i++){
                        int[] ExplosionXY = this.Explosions.get(i);
                        if(!Test){
                            if((this.Exploid)&&(ExplosionXY[0]*32 == x)&&(ExplosionXY[1]*32 == y)){
                                Test = true;
                                Image = this.AnimationDie();
                            }
                        }
                    }
                    Graphic.drawImage(Image, x - X1, y - Y1, 32, 32, this);
                    Test = false;
                }else{
                    BufferedImage Image = this.TestCase(this.CurrentWorld[y/32][x/32]);
                    Graphic.drawImage(Image, x - X1, y - Y1, 32, 32, this);
                }
            }
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
            case 'a':
                Image = this.Breakable;
                break;
            case '0':
                Image = this.Delemitation_Block;
                break;
            case '1':
                Image = this.Delemitation_Block;
                break;
            case '*':
                Image = this.AnimationGravity();
                break;
            case '#':
                Image = this.AnimationDiamond();
                break;
        }
        return Image;
    }


    private BufferedImage AnimationDie(){
        try{
            BufferedImage Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Star\\1.png"));
            switch(this.getEtatDiamond()){
                case 1:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Star\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Star\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Star\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Star\\4.png"));
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
            BufferedImage Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Diamond\\1.png"));
            switch(this.getEtatPlayer()){
                case 1:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Diamond\\1.png"));
                    break;
                case 2:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Diamond\\2.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Diamond\\3.png"));
                    break;
                case 4:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Diamond\\4.png"));
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
            BufferedImage Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Gravity\\1.png"));
            switch(this.getEtatPlayer()){
                case 1:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Gravity\\1.png"));
                    break;
                case 2:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Gravity\\2.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Gravity\\3.png"));
                    break;
                case 4:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Level_"+ this.Levels +"\\Gravity\\4.png"));
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
            BufferedImage Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Personnage\\"+ Repertory +"\\1.png"));
            switch(this.getEtatPlayer()){
                case 1:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Personnage\\"+ Repertory +"\\1.png"));
                    break;
                case 2:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Personnage\\"+ Repertory +"\\2.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Personnage\\"+ Repertory +"\\3.png"));
                    break;
                case 4:
                    Image = ImageIO.read(new File("C:\\Users\\lecra\\Desktop\\Ressource\\Personnage\\"+ Repertory +"\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}