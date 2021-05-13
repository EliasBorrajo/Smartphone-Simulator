package ch.hevs.smartphone.Smartphone;

import ch.hevs.smartphone.Bases.MyButton;
import ch.hevs.smartphone.Bases.ScreenSizeEnum;
import ch.hevs.smartphone.Contacts.ContactGUI;
import ch.hevs.smartphone.Gallery.GalleryGUI;
import ch.hevs.smartphone.Weather.WeatherGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // LAYOUT
    private CardLayout cardlayout;

    // PANEL
    private JPanel pnlContent;  // Panel principal qui va contenir tous les cards
    private JPanel pnlHome;
    private ContactGUI pnlContact;
    private GalleryGUI pnlGallery;
    private WeatherGUI pnlWeather;

    // BUTTON
    private MyButton btnContact;
    private MyButton btnGallery;
    private MyButton btnWeather;

    // ICON
    private Icon iconWeather;
    private Icon iconContact;
    private Icon iconGallery;


    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContentLayout(){
        setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        buildpnlContent();
    }
    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JPanel buildpnlContent(){
        cardlayout = new CardLayout();

        pnlContent = this;

        pnlHome = new JPanel(); //new GridLayout(2,3)
        pnlContact = new ContactGUI();
        pnlGallery = new GalleryGUI();
        pnlWeather = new WeatherGUI();

        //String fileNameContact = getClass().getResource("\\contactIcon2.png").getFile();
        //ContentLayout.class.getResource();

        iconWeather = new ImageIcon(String.valueOf(ClassLoader.getSystemResourceAsStream("\\resources\\IconApp\\weatherIcon2.png")));
        iconContact = new ImageIcon("C:\\Users\\Lonfat Milena\\Desktop\\SmartphonePhotoApp\\contactIcon2.png");
        iconGallery = new ImageIcon("C:\\Users\\Lonfat Milena\\Desktop\\SmartphonePhotoApp\\galleryIcon2.png");

        btnContact = new MyButton(iconContact);
        btnGallery = new MyButton(iconGallery);
        btnWeather = new MyButton(iconWeather);

        this.setLayout(cardlayout);

        pnlHome.add(btnContact);
        pnlHome.add(btnGallery);
        pnlHome.add(btnWeather);
        pnlHome.setBackground(Color.GREEN);

        //Ajouteur les cards au panel conteneur
        this.add("Home",pnlHome);
        this.add("Contact",pnlContact);
        this.add("Gallery",pnlGallery);
        this.add("Weather",pnlWeather);

        cardlayout.show(this,"Home");

        btnContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(pnlContent,"Contact");
            }
        });

        btnGallery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(pnlContent,"Gallery");
            }
        });

        btnWeather.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(pnlContent,"Weather");
            }
        });
        return this;
    }
}
