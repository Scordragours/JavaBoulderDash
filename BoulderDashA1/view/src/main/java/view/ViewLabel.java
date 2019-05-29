package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class View Label.
 *
 * @author Arthur Lecras
 * @see javax.swing.JLabel
 *
 */
public class ViewLabel extends JLabel{
    /** Label size. */
    int Width, Height;

    /** Image location  */
    String URL;

    /**
     * Label class constructor.
     *
     * @param Text Text displayed in the label.
     * @param Width Label width.
     * @param Height Label height.
     * @param URL Image Location
     *
     * */
    public ViewLabel(String Text, int Width, int Height, String URL){
        this.URL = URL;
        this.Width = Width;
        this.Height = Height;
        this.setText(Text);
        setOpaque(false);
    }

    /**
     * Allows to paint a background in the label.
     *
     * @see java.awt.TexturePaint
     * @return TexturePaint
     *
     * */
    private TexturePaint BackgroundPaint(){
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

    /**
     * Applies the background image.
     *
     * @param g Graphic object.
     *
     */
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(BackgroundPaint());
        g2d.fill(new RoundRectangle2D.Double(0, 0, this.Width, this.Height,0,0));
        super.paintComponent(g2d);
    }

    /**
     * Applies a border.
     *
     * @param g Graphic object.
     *
     */
    protected void paintBorder(Graphics g){}
}