package ch.hevs.smartphone.applications.weather;

import ch.hevs.smartphone.parameters.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Borrajo Elias, Milena Lonfat
 * Classe qui gère l'application météo
 * Changement de ville possible
 * Donne la température maximale, minimale et celle du moment, avec le taux d'humidité
 */
public class WeatherGUI extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    WeatherAPI weatherInfos;

    // Panel
    private JPanel pnlNorth;
    private JPanel pnlCenter;
    private JPanel pnlLocation;
    private JPanel pnlTemp;
    private JPanel pnlTempMax;
    private JPanel pnlTempMin;
    private JPanel pnlHumidity;
    private JPanel pnlNoConnexion;

    // Label
    private JLabel lblLocation;
    private JLabel lblTemp;
    private JLabel lblTempMax;
    private JLabel lblTempMin;
    private JLabel lblHumidity;
    private JLabel lblNoConnexion;

    // Button
    private JButton btnSearch;

    // ImageIcon
    private ImageIcon iconSearch;

    // TextField
    private JTextField tfLocation;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public WeatherGUI() {
        weatherInfos = new WeatherAPI();
        buildMainPanel();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private void buildMainPanel() {
        setLayout(new BorderLayout());

        buildIcon();
        buildPanel();
    }

    private void buildIcon() {
        //Création icon
        ClassLoader classLoader = getClass().getClassLoader();

        // btn search
        iconSearch = new ImageIcon(classLoader.getResource("ContentIcon/Apps/Weather_IconSearch.png"));
        Image imageSearchIcon = iconSearch.getImage();
        Image newImgSearchIcon = imageSearchIcon.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        iconSearch = new ImageIcon(newImgSearchIcon);
    }

    private void buildPanel() {
        // Création textfield
        tfLocation = new JTextField();
        tfLocation.setPreferredSize(new Dimension(200, 25));

        //Création bouton
        btnSearch = new JButton(iconSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change URL location
                weatherInfos.setUrlLocation(tfLocation.getText());

                //remettre les valeurs getAPIDetails
                weatherInfos.getAPIDetails();

                // Changer les informations à l'affichage
                weatherInfos.setNomVille(weatherInfos.getNomVille());
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
        lblLocation = new JLabel("Location : " + weatherInfos.getNomVille());
        lblTemp = new JLabel("Temperature : " + weatherInfos.getTemperature() + "°C");
        lblTempMax = new JLabel("Maximum temperature : " + weatherInfos.getTempMax() + "°C");
        lblTempMin = new JLabel("Minimum temperature : " + weatherInfos.getTempMin() + "°C");
        lblHumidity = new JLabel("Humidity level : " + weatherInfos.getHumidite() + "%");

        // Panel nord
        pnlNorth = new JPanel();
        pnlNorth.add(tfLocation);
        pnlNorth.add(btnSearch);

        // Panel centre
        pnlCenter = new JPanel(new GridLayout(20, 0, 5, 5));
        pnlLocation = new JPanel();
        pnlTemp = new JPanel();
        pnlTempMax = new JPanel();
        pnlTempMin = new JPanel();
        pnlHumidity = new JPanel();

        pnlLocation.add(lblLocation);
        pnlTemp.add(lblTemp);
        pnlTempMax.add(lblTempMax);
        pnlTempMin.add(lblTempMin);
        pnlHumidity.add(lblHumidity);

        pnlCenter.add(pnlLocation);
        pnlCenter.add(pnlTemp);
        pnlCenter.add(pnlTempMax);
        pnlCenter.add(pnlTempMin);
        pnlCenter.add(pnlHumidity);

        // Panel si pas de connexion internet
        pnlNoConnexion = new JPanel();
        lblNoConnexion = new JLabel("Could not connect to server");
        pnlNoConnexion.add(lblNoConnexion);

        // Ajout des PANELS au Layout
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlNoConnexion, BorderLayout.SOUTH);
        this.add(pnlCenter, BorderLayout.CENTER);

        // Si connection non valide
        if (weatherInfos.isConnected() == false) {
            pnlCenter.setVisible(false);
            pnlNorth.setVisible(true);
            pnlNoConnexion.setVisible(true);
        } else {
            pnlCenter.setVisible(true);
            pnlNorth.setVisible(true);
            pnlNoConnexion.setVisible(false);
        }

    }
}
