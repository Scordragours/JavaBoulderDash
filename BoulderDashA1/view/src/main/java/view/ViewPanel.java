package view;

import contract.ControllerOrder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class View Panel.
 *
 * @author Arthur Lecras
 * @see javax.swing.JPanel
 *
 */
public class ViewPanel extends JPanel{
    /** Definition of variables to store images */
    private BufferedImage Breakable, Delemitation_Block, Background;

    /** Texture condition with respect to time. */
    private int Levels;

    /** Player's position. */
    private int PlayerX, PlayerY;

    /** Status for animations. */
    private int StatePlayer = 1, StateDiamond = 1, StateExit = 1;

    /** State of the game. */
    private boolean Die = false, WinBoolean = false, Exploid = false, ExitPossible = false;

    /** Defines the table containing the positions of the explosions. */
    private ArrayList<int[]> Explosions = new ArrayList<>();

    /** Definition of the variable that will contain the map. */
    private char[][] CurrentWorld = new char[26][48];

    /** Define the variables for displaying the interface. */
    private ViewLabel LabelScore, LabelTime, LabelDiamond;

    /** Defined the player's state. */
    private ControllerOrder ControlleStatePlayer = ControllerOrder.STAND_BY;

    /** Interprets the project path for images. */
    private static String Path = System.getProperty("user.dir") +"\\BoulderDashA1\\model\\src\\main\\resources\\Assets\\";


    /**
     *
     * Frame class constructor.
     *
     * @param Levels The level of the textures.
     */
    public ViewPanel(int Levels){
        this.Levels = Levels;
        this.Modify();

        Font FontLabel = new Font("TimesRoman", Font.BOLD, 25);

        this.LabelScore = new ViewLabel("000000", 150, 40, this.Path +"Interface\\ScoreBoard.png");
        this.LabelScore.setHorizontalAlignment(SwingConstants.CENTER);
        this.LabelScore.setBounds(188, 10, 150, 40);
        this.LabelScore.setFont(FontLabel);
        this.LabelScore.setForeground(Color.WHITE);
        this.add(this.LabelScore);

        this.LabelTime = new ViewLabel("        000", 113, 40, this.Path +"\\Interface\\ClockBoard.png");
        this.LabelTime.setBounds(7, 64, 113, 40);
        this.LabelTime.setFont(FontLabel);
        this.LabelTime.setForeground(Color.WHITE);
        this.add(LabelTime);

        this.LabelDiamond = new ViewLabel("        000", 113, 40, this.Path +"\\Interface\\DiamondBoard.png");
        this.LabelDiamond.setBounds(7, 112, 113, 40);
        this.LabelDiamond.setFont(FontLabel);
        this.LabelDiamond.setForeground(Color.WHITE);
        this.add(this.LabelDiamond);
    }

    /**
     * Adds an explosion to the explosion array.
     *
     * @param explosion the explosion
     */
    public void addExplosion(int[] explosion)
    {
        this.Explosions.add(explosion);
    }

    /**
     * Defines the received map to display it.
     *
     * @param CurrentWorld The received map.
     */
    protected void setCurrentWorld(char[][] CurrentWorld){
        this.CurrentWorld = CurrentWorld;
    }
    /**
     *  Get all the characters from the map.
     *
     * @return char[][]
     */
    protected char[][] getCurrentWorld(){
        return this.CurrentWorld;
    }
    /**
     * Retrieves a character from the map according to a coordinate.
     *
     * @param X Position X.
     * @param Y Postion Y.
     * @return char
     */
    protected char getCurrentWorldChar(int X, int Y){
        return this.CurrentWorld[Y][X];
    }

    /**
     *  Texture condition with respect to time.
     *
     * @param Levels Texture condition with respect to time.
     */
    protected void setLevels(int Levels){
        this.Levels = Levels;
    }

    /**
     * Defines the player's position X.
     *
     * @param PlayerX Defines the x-position of the player.
     */
    protected void setPlayerX(int PlayerX){
        this.PlayerX = PlayerX;
    }
    /**
     * Recovers the player's position X.
     *
     * @return int
     */
    protected int getPlayerX(){
        return this.PlayerX;
    }

    /**
     * Defines the player's position Y.
     *
     * @param PlayerY Defines the x-position of the player.
     */
    protected void setPlayerY(int PlayerY){
        this.PlayerY = PlayerY;
    }
    /**
     * Recovers the player's position Y.
     *
     * @return int
     */
    protected int getPlayerY(){
        return this.PlayerY;
    }

    /**
     * Define the state of the game exit.
     *
     * @param ExitPossible Define the state of the game exit.
     */
    protected void setExitPossible(boolean ExitPossible){
        this.ExitPossible = ExitPossible;
    }
    /**
     * Recovers the state of the game.
     *
     * @return boolean
     */
    protected boolean getExitPossible(){
        return this.ExitPossible;
    }


    /**
     * Defined the state of the game.
     *
     * @param Die Define the state of the game on death.
     */
    protected void setDie(boolean Die){
        this.Die = Die;
    }
    /**
     * Recovers the state of the game.
     *
     * @return boolean
     */
    protected boolean getDie(){
        return this.Die;
    }

    /**
     *  Recovers the state of the game.
     *
     * @param WinBoolean Define the state of the game on victory.
     */
    protected void setWinBoolean(boolean WinBoolean){
        this.WinBoolean = WinBoolean;
    }
    /**
     * Recovers the state of the game.
     *
     * @return boolean
     */
    protected boolean getWinBoolean(){
        return this.WinBoolean;
    }

    /**
     * Defines the state of the game for an explosion to occur.
     *
     * @param Exploid Defines the state of the game for an explosion to occur.
     *
     */
    protected void setExploid(boolean Exploid){
        this.Exploid = Exploid;
    }
    /**
     * Recovers the state of the game so that an explosion appears.
     *
     * @return boolean
     */
    protected boolean getExploid(){
        return this.Exploid;
    }

    /**
     * Defines the position table of the explosions.
     *
     * @param Explosions the position table of the explosions.
     */
    protected void setExplosions(ArrayList<int[]> Explosions){
        this.Explosions = Explosions;
    }
    /**
     * Recovers the position table of the explosions.
     *
     * @return ArrayList {@link java.util.ArrayList}
     */
    protected ArrayList<int[]> getExplosions(){
        return this.Explosions;
    }

    /**
     * Defines the player's state.
     *
     * @param StatePlayer States of player.
     */
    protected void setStatePlayer(int StatePlayer){
        this.StatePlayer = StatePlayer;
    }
    /** Increments the player's status. */
    protected void setStatePlayerIncrement(){
        this.StatePlayer++;
    }
    /**
     * Recovers the player's status.
     *
     * @return int
     */
    protected int getStatePlayer(){
        return this.StatePlayer;
    }

    /**
     * Defines the diamond's state.
     *
     * @param StateDiamond States of diamonds.
     */
    protected void setStateDiamond(int StateDiamond){
        this.StateDiamond = StateDiamond;
    }
    /** Increments the diamond's status. */
    protected void setStateDiamondIncrement(){
        this.StateDiamond++;
    }
    /**
     * Recovers the diamond's status.
     *
     * @return int
     */
    protected int getStateDiamond(){
        return this.StateDiamond;
    }

    /**
     * Defines the exit's state.
     *
     * @param StateExit States of Win.
     */
    protected void setStateExit(int StateExit){
        this.StateExit = StateExit;
    }
    /** Increments the exit's status. */
    protected void setStateExitIncrement(){
        this.StateExit++;
    }
    /**
     * Recovers the exit's status.
     *
     * @return int
     */
    protected int getStateExit(){
        return this.StateExit;
    }

    /**
     * Defines the time display.
     *
     * @param RemainingTime Remaining Time.
     */
    protected void setRemainingTime(int RemainingTime){
        if(RemainingTime < 10){
            this.LabelTime.setText("        00"+ RemainingTime);
        }else if(RemainingTime < 100){
            this.LabelTime.setText("        0"+ RemainingTime);
        }else{
            this.LabelTime.setText("        "+ RemainingTime);
        }
    }
    /**
     * Defines the display of the number of diamonds.
     *
     * @param RemainingDiamonds Remaining diamonds.
     * */
    protected void setRemainingDiamonds(int RemainingDiamonds){
        if(RemainingDiamonds < 10){
            this.LabelDiamond.setText("        00"+ RemainingDiamonds);
        }else if(RemainingDiamonds < 100){
            this.LabelDiamond.setText("        0"+ RemainingDiamonds);
        }else{
            this.LabelDiamond.setText("        "+ RemainingDiamonds);
        }
    }
    /**
     * Defines the display of the score.
     *
     * @param Score The score.
     */
    protected void setScore(int Score){
        if(Score < 10){
            this.LabelScore.setText("00000"+ Score);
        }else if(Score < 100){
            this.LabelScore.setText("0000"+ Score);
        }else if(Score < 1000){
            this.LabelScore.setText("000"+ Score);
        }else if(Score < 10000){
            this.LabelScore.setText("00"+ Score);
        }else if(Score < 100000){
            this.LabelScore.setText("0"+ Score);
        }else{
            this.LabelScore.setText(""+ Score);
        }
    }

    /**
     * Defines the state of the character.
     *
     * @param ControllerOrder State of the character.
     */
    protected void setControlleStatePlayer(ControllerOrder ControllerOrder){
        this.ControlleStatePlayer = ControllerOrder;
    }
    /**
     * Recovers the state of the character.
     *
     * @return ControllerOrder
     */
    protected ControllerOrder getControlleStatePlayer(){
        return this.ControlleStatePlayer;
    }

    /** Changes the location of the recovered images. */
    protected void Modify(){
        try{
            this.Breakable = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Breakable\\1.png"));
            this.Delemitation_Block = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Outline\\1.png"));
            this.Background = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Background\\1.png"));
        }catch(IOException e){}
    }

    /** @see javax.swing.JComponent */
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
                                this.Explosions.get(i)[2] = this.Explosions.get(i)[2]+1;
                                if(this.Explosions.get(i)[2] >= 10)
                                {
                                    this.Explosions.remove(i);
                                }
                            }
                        }
                    }
                    Graphic.drawImage(Image, x - X1, y - Y1, 32, 32, this);
                    if((this.getExploid())&&(this.getPlayerX() == x/32)&&(this.getPlayerY() == y/32)){
                        Graphic.drawImage(this.TestCase(this.getCurrentWorldChar(x/32, y/32)), x - X1, y - Y1, 32, 32, this);
                    }
                    Test = false;
                }else{
                    BufferedImage Image = this.TestCase(this.getCurrentWorldChar(x/32, y/32));
                    Graphic.drawImage(Image, x - X1, y - Y1, 32, 32, this);
                }
            }
        }
        if(this.getDie()){
            Graphic.drawImage(this.AnimationText("Game_Over"), 191, 240, 129, 32, this);
        }else if(this.getWinBoolean()){
            Graphic.drawImage(this.AnimationText("Win"), 225, 240, 62, 32, this);
        }
    }

    /**
     * Gives the right image in relation to a character.
     *
     * @return BufferedImage {@link java.awt.image.BufferedImage}
     * @param Char Character.
     */
    private BufferedImage TestCase(char Char){
        BufferedImage Image = null;
        switch(Char){
            case 'P':
                switch(this.getControlleStatePlayer()){
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
                if(this.getExitPossible()){
                    Image = this.AnimationExit();
                }else{
                    Image = this.Delemitation_Block;
                }
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
        }
        return Image;
    }

    /**
     * Victory animation.
     *
     * @return BufferedImage {@link java.awt.image.BufferedImage}
     */
    private BufferedImage AnimationExit(){
        try{
            BufferedImage Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Delemitation_Block_1\\1.png"));
            switch(this.getStateExit()){
                case 1:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Delemitation_Block_1\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Delemitation_Block_1\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Delemitation_Block_1\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Delemitation_Block_1\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){}
        return null;
    }


    /**
     * Animation of the texts.
     *
     * @return BufferedImage {@link java.awt.image.BufferedImage}
     * @param URL Respertory of the sprite.
     */
    private BufferedImage AnimationText(String URL){
        try{
            BufferedImage Image = ImageIO.read(new File(this.Path +"\\Interface\\"+ URL +"\\1.png"));
            switch(this.getStateDiamond()){
                case 1:
                    Image = ImageIO.read(new File(this.Path +"\\Interface\\"+ URL +"\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(this.Path +"\\Interface\\"+ URL +"\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File(this.Path +"\\Interface\\"+ URL +"\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File(this.Path +"\\Interface\\"+ URL +"\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){}
        return null;
    }


    /**
     * Animation of an moving enemy.
     *
     * @return BufferedImage {@link java.awt.image.BufferedImage}
     * @param N The number of the enemy.
     */
    private BufferedImage AnimationEnnemy(int N){
        try{
            BufferedImage Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Enemy\\"+ N +"\\1.png"));
            switch(this.getStateDiamond()){
                case 1:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Enemy\\"+ N +"\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Enemy\\"+ N +"\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Enemy\\"+ N +"\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Enemy\\"+ N +"\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){}
        return null;
    }

    /**
     * Animation at the death of the player.
     *
     * @return BufferedImage {@link java.awt.image.BufferedImage}
     */
    private BufferedImage AnimationDie(){
        try{
            BufferedImage Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Star\\1.png"));
            switch(this.getStateDiamond()){
                case 1:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Star\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Star\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Star\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Star\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){}
        return null;
    }

    /**
     * Animation of the flashing diamond.
     *
     * @return BufferedImage {@link java.awt.image.BufferedImage}
     */
    private BufferedImage AnimationDiamond(){
        try{
            BufferedImage Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Diamond\\1.png"));
            switch(this.getStateDiamond()){
                case 1:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Diamond\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Diamond\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Diamond\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Diamond\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){}
        return null;
    }

    /**
     * Animation of the stone.
     *
     * @return BufferedImage {@link java.awt.image.BufferedImage}
     */
    private BufferedImage AnimationGravity(){
        try{
            BufferedImage Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Gravity\\1.png"));
            switch(this.getStateDiamond()){
                case 1:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Gravity\\1.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Gravity\\2.png"));
                    break;
                case 5:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Gravity\\3.png"));
                    break;
                case 7:
                    Image = ImageIO.read(new File(this.Path +"Level_"+ this.Levels +"\\Gravity\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){}
        return null;
    }

    /**
     * Animation of the player.
     *
     * @return BufferedImage {@link java.awt.image.BufferedImage}
     * @param Repertory Respertory of the player's states.
     */
    private BufferedImage AnimationPeronnage(String Repertory){
        try{
            BufferedImage Image = ImageIO.read(new File(this.Path +"Personnage\\"+ Repertory +"\\1.png"));
            switch(this.getStatePlayer()){
                case 1:
                    Image = ImageIO.read(new File(this.Path +"Personnage\\"+ Repertory +"\\1.png"));
                    break;
                case 2:
                    Image = ImageIO.read(new File(this.Path +"Personnage\\"+ Repertory +"\\2.png"));
                    break;
                case 3:
                    Image = ImageIO.read(new File(this.Path +"Personnage\\"+ Repertory +"\\3.png"));
                    break;
                case 4:
                    Image = ImageIO.read(new File(this.Path +"Personnage\\"+ Repertory +"\\4.png"));
                    break;
            }
            return Image;
        }catch(IOException e){}
        return null;
    }
}