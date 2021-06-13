package ch.hevs.smartphone.parameters.button;

import javax.swing.*;

/**
 * @author Lonfat Milena
 * Classe pour stylisé nos JButtons
 */
public class ButtonIcon extends JButton {
    /**
     * Construteur
     *
     * @param icon
     */
    public ButtonIcon(Icon icon) {
        super(icon);
        setOpaque(true);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setIcon(icon);
    }
}