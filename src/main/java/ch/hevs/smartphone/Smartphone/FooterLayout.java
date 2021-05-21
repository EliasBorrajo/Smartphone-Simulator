package ch.hevs.smartphone.Smartphone;

import ch.hevs.smartphone.Bases.MyButton;
import ch.hevs.smartphone.Bases.MyIcon;
import ch.hevs.smartphone.Bases.ScreenSizeEnum;


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
    private JMenu jMenuOption;

    // BUTTONS
    private MyIcon btnBack;
    private MyIcon btnHome;
    private MyIcon btnOption;

    // JITEM
    // change en fonction de la fenêtre a voir comment Switch case avec enum?
    // ou bien getCardlayout, donc en fonction du card ou on est ça change

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public FooterLayout() {
        setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setBackground(Color.blue);

        // Construction du btn home
        URL imageHome = FooterLayout.class.getClassLoader().getResource("FooterIcon/homeIcon.png");
        ImageIcon iconHome = new ImageIcon(imageHome);
        this.btnHome = new MyIcon(iconHome);

        // Construction du btn back
        URL imageBack = FooterLayout.class.getClassLoader().getResource("FooterIcon/backIcon.png");
        ImageIcon iconBack = new ImageIcon(imageBack);
        this.btnBack = new MyIcon(iconBack);

        // Construction du btn options
        URL imageOption = FooterLayout.class.getClassLoader().getResource("FooterIcon/optionIcon.jpg");
        ImageIcon iconOption = new ImageIcon(imageOption);
        this.btnOption = new MyIcon(iconOption);

    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************

    public MyIcon getBtnBack() {
        return btnBack;
    }

    public MyIcon getBtnHome() {
        return btnHome;
    }

    void buildMenu(String panel) {
        if (panel.equals("Home")) {
            this.buildMenuBarHome();
        } else if (panel.equals("Weather")) {
            this.buildMenuBarMeteo();
        } else if (panel.equals("Gallery")) {
            this.buildMenuBarGallery();
        }  else if (panel.equals("AddPhoto")) {
            this.buildMenuBarGalleryAdd();
        }else if (panel.equals("Contact")) {
            this.buildMenuBarContact();
        } else if (panel.equals("AddContact")) {
            this.buildMenuBarAddContact();
        } else {
            this.buildMenuBarHome();
        }
    }

    void buildMenuBarHome() {
        this.removeAll();
        menuBar = new JMenuBar();

        jMenuOption = new JMenu("");

        menuBar.add(this.btnHome);
        this.add(menuBar);
    }

    void buildMenuBarContact() {
        this.removeAll();
        menuBar = new JMenuBar();

        jMenuOption = new JMenu("");

        menuBar.add(this.btnBack);
        menuBar.add(this.btnHome);
        menuBar.add(this.btnOption);
        menuBar.add(jMenuOption);
        this.add(menuBar);
    }

    void buildMenuBarAddContact() {
        this.removeAll();
        menuBar = new JMenuBar();

        jMenuOption = new JMenu("");

        menuBar.add(this.btnBack);
        menuBar.add(this.btnHome);
        menuBar.add(this.btnOption);
        menuBar.add(jMenuOption);
        this.add(menuBar);
    }

    void buildMenuBarGallery() {
        this.removeAll();
        menuBar = new JMenuBar();

        jMenuOption = new JMenu("");

        menuBar.add(this.btnBack);
        menuBar.add(this.btnHome);

        menuBar.add(jMenuOption);
        this.add(menuBar);
    }

    void buildMenuBarGalleryAdd(){
        this.removeAll();
        menuBar = new JMenuBar();

        jMenuOption = new JMenu("");

        menuBar.add(this.btnBack);
        menuBar.add(this.btnHome);

        menuBar.add(jMenuOption);
        this.add(menuBar);
    }

    void buildMenuBarMeteo() {
        this.removeAll();
        menuBar = new JMenuBar();

        jMenuOption = new JMenu("");

        menuBar.add(this.btnBack);
        menuBar.add(this.btnHome);
        menuBar.add(this.btnOption);

        menuBar.add(jMenuOption);
        this.add(menuBar);
    }
}