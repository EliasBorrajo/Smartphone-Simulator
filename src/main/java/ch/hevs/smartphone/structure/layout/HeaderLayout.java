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
 * Top of our frame which will be visible from everywhere
 * Used to turn off the smartphone, and give the time and date
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
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param smartphoneGUI
     */
    public HeaderLayout(SmartphoneGUI smartphoneGUI) {
        this.smartphoneGUI = smartphoneGUI;
        setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setBackground(Color.WHITE);

        // Add elements
        add(buildButtonIconShutdown());
        add(buildLabelDate());
        add(buildLabelTime());
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * JButton which allows you to turn off the smartphone and serializes the JSON of contacts and photos
     * @return a JButton btnShutdown
     */
    private JButton buildButtonIconShutdown() {
        URL imagePower = HeaderLayout.class.getClassLoader().getResource("HeaderIcon/powerIcon.png");
        iconPower = new ImageIcon(imagePower);
        btnShutdown = new ButtonIcon(iconPower);

        /**
         * ActionListener on btnShutdown
         * When we turn off the smartphone, serialize all the information
         */
        btnShutdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // PHOTOS SERIALIZATION
                smartphoneGUI.getContentLayout().getPnlGallery().getJsonPhotoBook().write(
                        smartphoneGUI.getContentLayout().getPnlGallery().getJsonPhotoBook().getmyObj(),
                        smartphoneGUI.getContentLayout().getPnlGallery().getJsonPhotoBook().getPhotosArray());

                // CONTACTS SERIALIZATION
                smartphoneGUI.getContentLayout().getPnlContact().getJsonAddressBook().write(
                        smartphoneGUI.getContentLayout().getPnlContact().getJsonAddressBook().getMyObj(),
                        smartphoneGUI.getContentLayout().getPnlContact().getJsonAddressBook().getContactArray());

                System.exit(0);

            }
        });
        return btnShutdown;
    }

    /**
     * Label with the current date
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
     * Label with the current time
     */
    private JLabel buildLabelTime() {
        lblTime = new JLabel();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        lblTime.setText(dtf.format(time));
        return lblTime;
    }

    /**
     * Time display
     */
    public void updateTime() {
        lblTime.setText(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
    }

    /**
     * Date display
     */
    public void updateDate() {
        lblDate.setText(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now()));
    }
}