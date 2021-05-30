package ch.hevs.smartphone.parameters.button;

import javax.swing.*;
import java.awt.*;

public class ButtonIcon extends JButton {
    public ButtonIcon(Icon icon){
        super(icon);
        setOpaque(true);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setIcon(icon);
    }
}