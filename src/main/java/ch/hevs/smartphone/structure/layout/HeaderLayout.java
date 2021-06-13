package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.ScreenSizeEnum;
import ch.hevs.smartphone.structure.SmartphoneGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Lonfat Milena, Borrajo Elias
 * Haut de notre frame qui sera visible de partout
 * Sert à éteindre le smartphone, et donne l'ehure et la date
 */

public class HeaderLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Layout
    private SmartphoneGUI smartphoneGUI;

    // Label
    private JLabel lblDate;
    private JLabel lblTime;

    // Button
    private ButtonIcon btnShutdown;

    // Icon
    private Icon iconPower;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    /**
     * Constructeur
     *
     * @param smartphoneGUI
     */
    public HeaderLayout(SmartphoneGUI smartphoneGUI) {
        this.smartphoneGUI = smartphoneGUI;
        setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setBackground(Color.WHITE);

        // Ajout des éléments
        add(buildButtonIconShutdown());
        add(buildLabelDate());
        add(buildLabelTime());
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * JButton qui permet d'éteindre le smartphone et sérialise les JSON des contacts et des photos
     */
    private JButton buildButtonIconShutdown() {
        URL imagePower = HeaderLayout.class.getClassLoader().getResource("HeaderIcon/powerIcon.png");
        iconPower = new ImageIcon(imagePower);
        btnShutdown = new ButtonIcon(iconPower);

        /**
         * Quand on éteint le smartphone, il faut SERIALISER toutes nos informations
         */
        btnShutdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // SERIALISATION PHOTOS
                smartphoneGUI.getContentLayout().getPnlGallery().getJsonPhotoBook().write(
                        smartphoneGUI.getContentLayout().getPnlGallery().getJsonPhotoBook().getmyObj(),
                        smartphoneGUI.getContentLayout().getPnlGallery().getJsonPhotoBook().getPhotosArray());

                // SERIALISATION CONTACTES
                smartphoneGUI.getContentLayout().getPnlContact().getJsonAddressBook().write(
                        smartphoneGUI.getContentLayout().getPnlContact().getJsonAddressBook().getmyObj(),
                        smartphoneGUI.getContentLayout().getPnlContact().getJsonAddressBook().getContactArray());

                System.exit(0);

            }
        });
        return btnShutdown;
    }

    /**
     * Label qui donne la date
     */
    private JLabel buildLabelDate() {
        lblDate = new JLabel();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime date = LocalDateTime.now();
        lblDate.setText(dtf.format(date));
        return lblDate;
    }

    // @TODO EFFACER L'aFFICHAGE DES SECONDES

    /**
     * Label qui donne l'heure
     */
    private JLabel buildLabelTime() {
        lblTime = new JLabel();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        lblTime.setText(dtf.format(time));
        return lblTime;
    }

    /**
     * Affichage de l'heure
     */
    public void updateTime() {
        lblTime.setText(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
    }

    /**
     * Affichage de la date
     */
    public void updateDate() {
        lblDate.setText(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now()));
    }
}