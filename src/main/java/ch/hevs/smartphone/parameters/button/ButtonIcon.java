package ch.hevs.smartphone.parameters.button;

import javax.swing.*;

/**
 * Class for stylized JButtons
 *
 * @author Lonfat Milena
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