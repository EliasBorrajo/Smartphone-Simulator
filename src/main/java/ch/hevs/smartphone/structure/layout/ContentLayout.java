package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.applications.contacts.*;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.parameters.button.ButtonIcon;
import ch.hevs.smartphone.parameters.ScreenSizeEnum;
import ch.hevs.smartphone.applications.gallery.GalleryGUI;
import ch.hevs.smartphone.applications.weather.WeatherGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author Lonfat Milena, Borrajo Elias, Bourquin Jonathan
 * Classe contenant tout notre écran principal.
 * On peut lancer 3 applications différentes depuis le Home.
 * ContentLayout est de type CardLayout.
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
    // C O N S T R U C T E U R
    //*****************************************************************************

    /**
     * Constructeur
     *
     * @throws IOException
     * @throws BusinessException
     */
    public ContentLayout() {
        setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        buildpnlContent();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    public void buildpnlContent() {
        cardlayout = new CardLayout();

        pnlContent = this;                          // permet de faciliter la lecture

        pnlHome = new JPanel();                     //new GridLayout(2,3)
        pnlContact = new ContactsGUI(this);
        pnlGallery = new GalleryGUI(this);
        pnlWeather = new WeatherGUI();

        // Home - Contruction des images
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

        // Ajout des boutons au Home
        pnlHome.add(btnContact);
        pnlHome.add(btnGallery);
        pnlHome.add(btnWeather);

        // Ajouter les cards au panel conteneur
        this.add("Home", pnlHome);
        this.add("Contact", pnlContact);
        this.add("Gallery", pnlGallery);
        this.add("Weather", pnlWeather);

        cardlayout.show(this, "Home");

        //*****************************************************************************
        // L I S T E N E R S
        //*****************************************************************************
        // Boutons de la page home
        // Ouvre l'application contact
        btnContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(pnlContent, "Contact");
            }
        });

        // Ouvre l'application galerie
        btnGallery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(pnlContent, "Gallery");
            }
        });

        // Ouvre l'appliation météo
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

    public JPanel getPnlHome() {
        return pnlHome;
    }

    public ContactsGUI getPnlContact() {
        return pnlContact;
    }

    public GalleryGUI getPnlGallery() {
        return pnlGallery;
    }

    public WeatherGUI getPnlWeather() {
        return pnlWeather;
    }

    public ButtonIcon getBtnContact() {
        return btnContact;
    }

    public ButtonIcon getBtnGallery() {
        return btnGallery;
    }

    public ButtonIcon getBtnWeather() {
        return btnWeather;
    }

    public ImageIcon getIconWeather() {
        return iconWeather;
    }

    public ImageIcon getIconContact() {
        return iconContact;
    }

    public ImageIcon getIconGallery() {
        return iconGallery;
    }

}