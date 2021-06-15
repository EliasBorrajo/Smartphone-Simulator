package ch.hevs.smartphone.parameters.button;

import javax.swing.*;

/**
 * @author Lonfat Milena
 * Class for stylized JButtons
 */
public class myButton extends JButton {
    /**
     * Constructor
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
