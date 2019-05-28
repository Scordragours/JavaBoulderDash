package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ViewLabel extends JLabel{
    int Width, Height;
    String URL;
    public ViewLabel(String Text, int Width, int Height, String URL){
        this.URL = URL;
        this.Width = Width;
        this.Height = Height;
        this.setText(Text);
        setOpaque(false);
    }
    private TexturePaint prepareBackgroundPaint(){
        BufferedImage im = new BufferedImage(this.Width, this.Height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imG2d = im.createGraphics();
        try{
            imG2d.drawImage(ImageIO.read(new File(this.URL)), 0, 0, this.Width, this.Height, this);
        }catch (IOException e){
            e.printStackTrace();
        }
        imG2d.dispose();
        Rectangle2D rect = new Rectangle2D.Double(0,0, this.Width, this.Height);
        return new TexturePaint(im, rect);
    }
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(prepareBackgroundPaint());
        g2d.fill(new RoundRectangle2D.Double(0, 0, this.Width, this.Height,0,0));
        super.paintComponent(g2d);
    }
    protected void paintBorder(Graphics g){}
}