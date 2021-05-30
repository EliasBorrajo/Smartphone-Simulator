package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.ScreenSizeEnum;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FooterLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // MENU BAR
    private JMenuBar menuBar;

    // BUTTONS
    private ButtonIcon btnBack;
    private ButtonIcon btnHome;


    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public FooterLayout() {
        setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setBackground(Color.WHITE);

        // Construction du btn home
        URL imageHome = FooterLayout.class.getClassLoader().getResource("FooterIcon/homeIcon.png");
        ImageIcon iconHome = new ImageIcon(imageHome);
        this.btnHome = new ButtonIcon(iconHome);

        // Construction du btn back
        URL imageBack = FooterLayout.class.getClassLoader().getResource("FooterIcon/backIcon.png");
        ImageIcon iconBack = new ImageIcon(imageBack);
        this.btnBack = new ButtonIcon(iconBack);

    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************

    public ButtonIcon getBtnBack() {
        return btnBack;
    }

    public ButtonIcon getBtnHome() {
        return btnHome;
    }

    void buildMenu() {
        removeAll();
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.add(this.btnBack);
        menuBar.add(this.btnHome);
        this.add(menuBar);
    }
}