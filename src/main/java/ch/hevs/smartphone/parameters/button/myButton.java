package ch.hevs.smartphone.parameters.button;

import javax.swing.*;

/**
 * @author Lonfat Milena
 * Classe pour stylisé nos JButtons
 */
public class myButton extends JButton {
    /**
     * Constructeur
     *
     * @param txt
     */
    public myButton(String txt) {
        setText(txt);
        setOpaque(true);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
}
