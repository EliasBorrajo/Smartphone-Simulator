package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.applications.contacts.*;
import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.ScreenSizeEnum;
import ch.hevs.smartphone.applications.gallery.GalleryGUI;
import ch.hevs.smartphone.applications.weather.WeatherGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Class containing all of our main screen.
 * Three applications can be started from home
 * ContentLayout is of type CardLayout.
 *
 * @author Lonfat Milena, Borrajo Elias, Bourquin Jonathan
 */

public class ContentLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Layout
    private CardLayout cardlayout;

    // Panel
    // Home
    private JPanel pnlContent;          // Panel principal qui va contenir tous les cards
    private JPanel pnlHome;             // Panneau d'accueil
    // Contact
    private ContactsGUI pnlContact;     // Application Contactes
    // Galerie
    private GalleryGUI pnlGallery;      // Application Gallerie
    // Météo
    private WeatherGUI pnlWeather;      // Application Weather

    // Button - lancent les applicatons
    private ButtonIcon btnContact;
    private ButtonIcon btnGallery;
    private ButtonIcon btnWeather;

    // ImageIcon
    private ImageIcon iconWeather;
    private ImageIcon iconContact;
    private ImageIcon iconGallery;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     */
    public ContentLayout() {
        setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        buildpnlContent();
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * Build panel
     */
    public void buildpnlContent() {
        cardlayout = new CardLayout();

        pnlContent = this;                          // permet de faciliter la lecture

        pnlHome = new JPanel();                     //new GridLayout(2,3)
        pnlContact = new ContactsGUI(this);
        pnlGallery = new GalleryGUI(this);
        pnlWeather = new WeatherGUI();

        // Home - Build imageIcon
        URL imageContact = ContentLayout.class.getClassLoader().getResource("ContentIcon/contactIcon2.png");
        URL imageGallery = ContentLayout.class.getClassLoader().getResource("ContentIcon/galleryIcon2.png");
        URL imageWeather = ContentLayout.class.getClassLoader().getResource("ContentIcon/weatherIcon2.png");

        iconContact = new ImageIcon(imageContact);
        iconGallery = new ImageIcon(imageGallery);
        iconWeather = new ImageIcon(imageWeather);

        btnContact = new ButtonIcon(iconContact);
        btnGallery = new ButtonIcon(iconGallery);
        btnWeather = new ButtonIcon(iconWeather);

        // CardLayout
        this.setLayout(cardlayout);

        // Add buttons
        pnlHome.add(btnContact);
        pnlHome.add(btnGallery);
        pnlHome.add(btnWeather);

        // Add cards to the container panel
        this.add("Home", pnlHome);
        this.add("Contact", pnlContact);
        this.add("Gallery", pnlGallery);
        this.add("Weather", pnlWeather);

        cardlayout.show(this, "Home");

        //*****************************************************************************
        // L I S T E N E R S
        //*****************************************************************************
        // Open the contact application
        btnContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(pnlContent, "Contact");
            }
        });

        // Open the gallery application
        btnGallery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(pnlContent, "Gallery");
            }
        });

        // Open the weather application
        btnWeather.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(pnlContent, "Weather");
            }
        });
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public CardLayout getCardlayout() {
        return cardlayout;
    }

    public JPanel getPnlContent() {
        return pnlContent;
    }

    public ContactsGUI getPnlContact() {
        return pnlContact;
    }

    public GalleryGUI getPnlGallery() {
        return pnlGallery;
    }
}