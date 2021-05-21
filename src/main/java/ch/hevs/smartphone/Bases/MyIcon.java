package ch.hevs.smartphone.Bases;

import javax.swing.*;

public class MyIcon extends JButton {
    public MyIcon(Icon icon){
        super(icon);
        setOpaque(true);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setIcon(icon);
        //setPreferredSize(new Dimension(25,45));
    }
}