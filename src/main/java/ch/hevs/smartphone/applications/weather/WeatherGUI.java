package ch.hevs.smartphone.applications.weather;

import ch.hevs.smartphone.parameters.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Elias
 */
public class WeatherGUI extends JPanel
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    WeatherAPI weatherInfos;

    // PANEL
    private JPanel pnlNord;
    private JPanel pnlCentre;

    private JPanel pnlVille;
    private JPanel pnlTemp;
    private JPanel pnlTempMax;
    private JPanel pnlTempMin;
    private JPanel pnlHumidite;

    private JPanel pnlNoConnexion;

    // TEXTFIELD
    private JTextField tfLocation;

    // BUTTONS
    private JButton btnSearch;

    // ICON
    private ImageIcon iconSearch;

    // LABEL
    private JLabel lblVille;
    private JLabel lblTemp;
    private JLabel lblTempMax;
    private JLabel lblTempMin;
    private JLabel lblHumidite;

    private JLabel lblNoConnexion;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public WeatherGUI()
    {
        weatherInfos = new WeatherAPI();
        buildMainPanel();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildMainPanel()
    {
        setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));

        setLayout(new BorderLayout());

        buildIcon();
        buildPanel();
    }
    private void buildIcon()
    {
        //Création icon
        ClassLoader classLoader = getClass().getClassLoader();

        // btn search
        iconSearch = new ImageIcon(classLoader.getResource("ContentIcon/Apps/Weather_IconSearch.png"));
        Image imageSearchIcon = iconSearch.getImage();
        Image newImgSearchIcon = imageSearchIcon.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        iconSearch = new ImageIcon(newImgSearchIcon);
    }

    private void buildPanel()
    {
        // Création textfield
        tfLocation = new JTextField();
        tfLocation.setPreferredSize(new Dimension(200,25));

        //Création bouton
        btnSearch   = new JButton(iconSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change URL location
                System.out.println("Ancien URL : " + weatherInfos.getUrlLocation());
                weatherInfos.setUrlLocation(tfLocation.getText());
                System.out.println("Nouvel URL : " + weatherInfos.getUrlLocation());
                //remettre les valeurs getAPIDetails
                weatherInfos.getAPIDetails();

                // Changer le nom à l'affichage
                weatherInfos.setNomVille(tfLocation.getText());
                weatherInfos.setTemperature(weatherInfos.getTemperature());
                weatherInfos.setTempMax(weatherInfos.getTempMax());
                weatherInfos.setTempMin(weatherInfos.getTempMin());
                weatherInfos.setHumidite(weatherInfos.getHumidite());

                removeAll();
                validate();
                buildPanel();
                revalidate();
                repaint();
            }
        });

        // Création des labels
        // @TODO METTRE DES VALEURS PAR DEFAUT
        // @TODO POUVOIR SAISIR DANS UN TEXTBOX NOM DE LA VILLE A CHERCHER
        lblVille        = new JLabel("Location : " + weatherInfos.getNomVille());
        lblTemp         = new JLabel("Temperature : " + weatherInfos.getTemperature() + "°C");
        lblTempMax      = new JLabel("Maximum temperature : " + weatherInfos.getTempMax() + "°C");
        lblTempMin      = new JLabel("Minimum temperature : " + weatherInfos.getTempMin() + "°C");
        lblHumidite     = new JLabel("Humidity level : " + weatherInfos.getHumidite() + "%");

        // NORD
        pnlNord         = new JPanel();
        pnlNord.add(tfLocation);
        pnlNord.add(btnSearch);

        // CENTRE
        pnlCentre       = new JPanel(new GridLayout(20,0,5,5));
        pnlVille        = new JPanel();
        pnlTemp         = new JPanel();
        pnlTempMax      = new JPanel();
        pnlTempMin      = new JPanel();
        pnlHumidite     = new JPanel();

        pnlVille.add(lblVille);
        pnlTemp.add(lblTemp);
        pnlTempMax.add(lblTempMax);
        pnlTempMin.add(lblTempMin);
        pnlHumidite.add(lblHumidite);

        pnlCentre.add(pnlVille);
        pnlCentre.add(pnlTemp);
        pnlCentre.add(pnlTempMax);
        pnlCentre.add(pnlTempMin);
        pnlCentre.add(pnlHumidite);

        // Panel si pas de connexion internet
        pnlNoConnexion  = new JPanel();
        lblNoConnexion  = new JLabel("Could not connect to server");
        pnlNoConnexion.add(lblNoConnexion);

        // Ajout des PANELS au Layout
        this.add(pnlNord,   BorderLayout.NORTH);
        this.add(pnlNoConnexion, BorderLayout.SOUTH);
        this.add(pnlCentre, BorderLayout.CENTER);


        if(weatherInfos.isConnected() == false){
            pnlCentre.setVisible(false);
            pnlNord.setVisible(true);
            pnlNoConnexion.setVisible(true);
        }
        else{
            pnlCentre.setVisible(true);
            pnlNord.setVisible(true);
            pnlNoConnexion.setVisible(false);
        }

    }
}
