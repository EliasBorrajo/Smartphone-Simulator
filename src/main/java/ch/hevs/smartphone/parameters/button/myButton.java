package ch.hevs.smartphone.parameters.button;

import javax.swing.*;

public class myButton extends JButton {
    public myButton(String txt){
        setText(txt);
        setOpaque(true);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
}
