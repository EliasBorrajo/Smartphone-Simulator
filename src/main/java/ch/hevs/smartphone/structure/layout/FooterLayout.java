package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.ScreenSizeEnum;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class FooterLayout extends JPanel
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // BUTTONS
    private ButtonIcon btnHome;

    // PANEL
    private ContentLayout contentLayout;


    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public FooterLayout(ContentLayout contentLayout)
    {
        this.contentLayout = contentLayout;

        setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setBackground(Color.WHITE);

        // Construction du btn home
        URL imageHome = FooterLayout.class.getClassLoader().getResource("FooterIcon/homeIcon.png");
        ImageIcon iconHome = new ImageIcon(imageHome);
        btnHome = new ButtonIcon(iconHome);

        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "HomeContact");
                contentLayout.getPnlGallery().getCardGallHome().show(contentLayout.getPnlGallery(), "HomeGallery");
                for(int i = 0; i < contentLayout.getPnlGallery().getPnlShowPhoto().length; i++){
                    // Réaffiche tous les bouttons normaux du panel gallery, en cas d'interruption dans
                    // la procedure d'attribuer une photo à un contacte
                    contentLayout.getPnlGallery().getPnlShowPhoto()[i].showNormalBtn();
                }
                contentLayout.getCardlayout().show(contentLayout.getPnlContent(), "Home");
            }
        });

        this.add(btnHome);
    }
}