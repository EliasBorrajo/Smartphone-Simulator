package ch.hevs.smartphone.parameters.button;

import javax.swing.*;

/**
 * @author Lonfat Milena
 * Class for stylized JButtons
 */
public class ButtonIcon extends JButton {
    /**
     * Constructor
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