package ch.hevs.smartphone.Bases;

import javax.swing.*;

public class MyButton extends JButton {
    public MyButton(Icon icon){
        super(icon);
        setOpaque(true);
        setContentAreaFilled(false);
        setBorderPainted(false);
        //setIcon(new ImageIcon(icon));
        //setPreferredSize(new Dimension(25,45));
    }
}
