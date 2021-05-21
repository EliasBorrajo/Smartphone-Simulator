package ch.hevs.smartphone.Bases;

import javax.swing.*;

public class MyButton extends JButton {
    public MyButton(String txt){
        setText(txt);
        setOpaque(true);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
}
