package cg.image.basics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by valentin on 05/05/16.
 */
public class ImageCanvas extends Canvas {

    private BufferedImage img;
    private int imageWidth;
    private int imageHeight;

    public ImageCanvas(int width, int height) {
        this.imageWidth = width;
        this.imageHeight = height;
    }

    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        this.img = new BufferedImage(imageWidth, imageHeight, TYPE_INT_RGB);
        final WritableRaster raster = img.getRaster();
        final ColorModel model = this.getColorModel();

        for (i=0; i<img.getWidth(); i++) {
            for (j=0; j<img.getHeight(); j++) {
                raster.setDataElements(i, j, Color.BLACK);
            }
        }
        g.drawImage(img, imageWidth, imageHeight, null);
    }



    /*
    @Override
    public void paint() {
        super.paint(g);
        this.img = new BufferedImage(imageWidth, imageHeight, TYPE_INT_RGB);
        final WritableRaster raster = img.getRaster();
        final ColorModel model = this.getColorModel();

        for (i=0; i<img.getWidth(); i++) {
            for (j=0; j<img.getHeight(); j++) {
                raster.setDataElements(imageWidth, imageHeight, raster );
            }
        }
    }
    */


}
