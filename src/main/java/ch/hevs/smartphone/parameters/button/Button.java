package ch.hevs.smartphone.parameters.button;

import javax.swing.*;

public class Button extends JButton {
    public Button(String txt){
        setText(txt);
        setOpaque(true);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
}
