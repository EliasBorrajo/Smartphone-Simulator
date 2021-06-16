package ch.hevs.smartphone.parameters.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class we use to resize our images
 *
 * @author Lonfat Milena
 */

abstract public class Util {
    /**
     * Resizes ImageIcon to the given ratio
     *
     * @param imageToScale
     * @param smaller
     * @return an resize ImageIcon
     */
    public static ImageIcon getScaledImageIcon(ImageIcon imageToScale, int smaller) {
        int widhOriginal = imageToScale.getIconWidth();
        int heightOriginal = imageToScale.getIconHeight();
        ImageIcon icon = imageToScale;
        if (widhOriginal > smaller) {
            if (widhOriginal > heightOriginal) {
                float ratio = (float) (widhOriginal / smaller);
                icon = new ImageIcon(imageToScale.getImage().getScaledInstance(smaller, (int) (heightOriginal / ratio), Image.SCALE_SMOOTH));
            } else {
                float ratio = (float) (heightOriginal / smaller);
                icon = new ImageIcon(imageToScale.getImage().getScaledInstance((int) (widhOriginal / ratio), smaller, Image.SCALE_SMOOTH));
            }
        }
        return icon;
    }

}
