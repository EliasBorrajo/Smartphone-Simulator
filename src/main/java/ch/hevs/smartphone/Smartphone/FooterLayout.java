package ch.hevs.smartphone.Smartphone;

import ch.hevs.smartphone.Bases.MyButton;
import ch.hevs.smartphone.Bases.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FooterLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // MENU BAR
    private JMenuBar menuBar = new JMenuBar();
    // JMENU
    private MyButton btnBack = new MyButton("Back");
    private MyButton btnHome = new MyButton("Home");
    private JMenu btnOption = new JMenu("Option");
    // JITEM
    // change en fonction de la fenêtre a voir comment Switch case avec enum?
    // ou bien getCardlayout, donc en fonction du card ou on est ça change

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public FooterLayout(){

        this.setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.FOOTER_HEIGHT.getSize()));
        this.setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.FOOTER_HEIGHT.getSize()));
        this.setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.FOOTER_HEIGHT.getSize()));
        this.setBackground(Color.blue);

        menuBar.add(btnBack);
        menuBar.add(btnHome);
        menuBar.add(btnOption);

        //option.add(JMenuItem voulu);

        this.add(menuBar);

        btnBack.addActionListener(new ListenerBtnBack());
        btnHome.addActionListener(new ListenerBtnHome());
    }

    //*****************************************************************************
    // I N T E R N A L   C L A S S
    //*****************************************************************************
    class ListenerBtnBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class ListenerBtnHome implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    // Voir pour les méthodes suivant les cards conact add/supp/modifier
}