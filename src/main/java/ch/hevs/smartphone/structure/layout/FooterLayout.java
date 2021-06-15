package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * @author Lonfat Milena, Borrajo Elias, Bourquin Jonathan
 * Bas de notre frame qui sera visible de partout
 * Sert à revenir sur la page home
 */

public class FooterLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Layout
    private ContentLayout contentLayout;

    // Button
    private ButtonIcon btnHome;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    /**
     * Constructeur
     *
     * @param contentLayout
     */
    public FooterLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;

        setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setBackground(Color.WHITE);

        // Construction du btn home
        URL imageHome = FooterLayout.class.getClassLoader().getResource("FooterIcon/homeIcon.png");
        ImageIcon iconHome = new ImageIcon(imageHome);
        btnHome = new ButtonIcon(iconHome);

        // ActionListener du btnHome
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "HomeContact");
                contentLayout.getPnlGallery().getCardGallHome().show(contentLayout.getPnlGallery(), "HomeGallery");
                contentLayout.getPnlGallery().getBtnAddPhoto().setVisible(true);
                for (int i = 0; i < contentLayout.getPnlGallery().getPnlShowPhoto().length; i++) {
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