package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.bases.ButtonIcon;
import ch.hevs.smartphone.applications.enums.ScreenSizeEnum;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FooterLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // MENU BAR
    private JMenuBar menuBar;

    // JMENU
    private JMenu btnOption;

    // BUTTONS
    private ButtonIcon btnBack;
    private ButtonIcon btnHome;

    // JITEM
    //home
    JMenuItem itemShotdown = new JMenuItem("Shotdown");
    JMenuItem itemlock = new JMenuItem("Lock");
    // contacts and gallery
    JMenuItem itemAdd = new JMenuItem("Add");
    JMenuItem itemEdit = new JMenuItem("Edit");
    JMenuItem itemCancel = new JMenuItem("Cancel");
    //weather
    JMenuItem itemSeven = new JMenuItem("7 days");
    JMenuItem itemTen = new JMenuItem("10 days");

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public FooterLayout() {
        setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setBackground(Color.blue);

        // Construction du btn options
        URL imageOption = FooterLayout.class.getClassLoader().getResource("FooterIcon/optionIcon.jpg");
        ImageIcon iconOption = new ImageIcon(imageOption);
        this.btnOption = new JMenu();
        btnOption.setIcon(iconOption);

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

    void buildMenu(String panel) {
        if (panel.equals("Home")) {
            this.buildMenuBarHome();
        } else {
            this.buildMenuBase();
        }
    }

    JMenuBar buildMenuBase(){
        this.removeAll();
        menuBar = new JMenuBar();
        menuBar.add(this.btnBack);
        menuBar.add(this.btnHome);
        this.add(menuBar);
        return menuBar;
    }

    void buildMenuBarHome() {
        this.removeAll();
        buildMenuBase();
        menuBar.add(btnOption);
        btnOption.add(itemShotdown);
        btnOption.add(itemlock);
        this.add(menuBar);
    }
}