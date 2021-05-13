package ch.hevs.smartphone.Smartphone;

import ch.hevs.smartphone.Bases.MyButton;
import ch.hevs.smartphone.Bases.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class FooterLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // MENU BAR
    private JMenuBar menuBar;

    // JMENU
    private JMenu jMenuOption;

    // BUTTONS
    private MyButton btnBack;
    private MyButton btnHome;

    // ICON
    private ImageIcon iconOption;
    private ImageIcon iconBack;
    private ImageIcon iconHome;

    // JITEM
    // change en fonction de la fenêtre a voir comment Switch case avec enum?
    // ou bien getCardlayout, donc en fonction du card ou on est ça change

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public FooterLayout(){
        setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setBackground(Color.blue);

        add(buildMenuBar());
    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    JMenuBar buildMenuBar(){
        menuBar = new JMenuBar();

        URL imageBack = FooterLayout.class.getClassLoader().getResource("FooterIcon/backIcon.png");
        URL imageHome = FooterLayout.class.getClassLoader().getResource("FooterIcon/homeIcon.png");
        URL imageOption = FooterLayout.class.getClassLoader().getResource("FooterIcon/optionIcon.jpg");

        iconBack = new ImageIcon(imageBack);
        iconHome = new ImageIcon(imageHome);
        iconOption = new ImageIcon(imageOption);

        btnBack = new MyButton(iconBack);
        btnHome = new MyButton(iconHome);

        jMenuOption = new JMenu("");
        jMenuOption.setIcon(iconOption);

        menuBar.add(btnBack);
        btnBack.addActionListener(new ListenerBtnBack());
        menuBar.add(btnHome);
        btnHome.addActionListener(new ListenerBtnHome());
        menuBar.add(jMenuOption);
        return menuBar;
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