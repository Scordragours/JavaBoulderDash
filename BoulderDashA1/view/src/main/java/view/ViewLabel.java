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
    private int Width, Height;

    /** Image location.  {@link java.lang.String}*/
    private String URL;

    /**
     * Label class constructor.
     *
     * @param Text Text displayed in the label. {@link java.lang.String}
     * @param Width Label width.
     * @param Height Label height.
     * @param URL Image Location. {@link java.lang.String}
     *
     * */
    public ViewLabel(String Text, int Width, int Height, String URL){
        this.setURL(URL);
        this.setWidth(Width);
        this.setHeight(Height);
        this.setText(Text);
        setOpaque(false);
    }

    /**
     * Defines the size of the label.
     *
     * @param Width Width of the label.
     *
     */
    protected void setWidth(int Width){
        this.Width = Width;
    }

    /**
     * Recovers the size of the label.
     *
     * @return int
     *
     */
    public int getWidth(){
        return this.Width;
    }

    /**
     * Defines the size of the label.
     *
     * @param Height Height of the label.
     *
     */
    protected void setHeight(int Height){
        this.Height = Height;
    }

    /**
     * Recovers the size of the label.
     *
     * @return int
     *
     */
    public int getHeight(){
        return this.Height;
    }

    /**
     * Defines the background of the label.
     *
     * @param URL Path of the background. {@link java.lang.String}
     *
     */
    protected void setURL(String URL){
        this.URL = URL;
    }

    /**
     * Recovers the background of the label.
     *
     * @return String {@link java.lang.String}
     *
     */
    public String getURL(){
        return this.URL;
    }


    /**
     * Allows to paint a background in the label.
     *
     * @see java.awt.TexturePaint
     * @return TexturePaint {@link java.awt.TexturePaint}
     *
     */
    private TexturePaint BackgroundPaint(){
        BufferedImage im = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D imG2d = im.createGraphics();
        try{
            imG2d.drawImage(ImageIO.read(new File(this.URL)), 0, 0, this.getWidth(), this.getHeight(), this);
        }catch (IOException e){
            e.printStackTrace();
        }
        imG2d.dispose();
        Rectangle2D rect = new Rectangle2D.Double(0,0, this.getWidth(), this.getHeight());
        return new TexturePaint(im, rect);
    }

    /**
     * Applies the background image.
     *
     * @see javax.swing.JComponent
     * @param g Graphic object. {@link java.awt.Graphics}
     *
     */
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(BackgroundPaint());
        g2d.fill(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(),0,0));
        super.paintComponent(g2d);
    }

    /**
     * Applies a border.
     *
     * @see javax.swing.JComponent
     * @param g Graphic object. {@link java.awt.Graphics}
     *
     */
    protected void paintBorder(Graphics g){}
}