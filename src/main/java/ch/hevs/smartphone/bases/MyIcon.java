package ch.hevs.smartphone.bases;

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