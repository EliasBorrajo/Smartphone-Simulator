package ch.hevs.smartphone.parameters.button;

import javax.swing.*;

/**
 * Class for stylized JButtons
 *
 * @author Lonfat Milena
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
