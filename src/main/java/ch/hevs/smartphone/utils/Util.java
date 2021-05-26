package ch.hevs.smartphone.utils;

import javax.swing.*;
import java.awt.*;

abstract public class Util {
    /**
     * Redimensionne les imagesicon selon le ratio donné
     * @param imageToScale ImageIcon à redimensionner
     * @param smaller Ratio de redimensionnement
     * @return Imageicon redimensionnée
     */
    public static ImageIcon getScaledImageIcon(ImageIcon imageToScale, int smaller){
        int widhOriginal = imageToScale.getIconWidth();
        int heightOriginal = imageToScale.getIconHeight();
        ImageIcon icon = imageToScale;
        if(widhOriginal > smaller){
            if(widhOriginal>heightOriginal){
                float ratio=(float)(widhOriginal/smaller);
                icon = new ImageIcon(imageToScale.getImage().getScaledInstance(smaller,(int)(heightOriginal/ratio) , Image.SCALE_SMOOTH)); //(int)(heightOriginal*ratio)
            }else{
                float ratio=(float)(heightOriginal/smaller);
                icon = new ImageIcon(imageToScale.getImage().getScaledInstance((int)(widhOriginal/ratio),smaller, Image.SCALE_SMOOTH)); //(int)(heightOriginal*ratio)
            }
        }
        return icon;
    }
}
