package ch.hevs.smartphone.applications.weather;

import ch.hevs.smartphone.parameters.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

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
    private JPanel pnlNord;
    private JPanel pnlCentre;
    private JPanel pnlVille;
    private JPanel pnlTemp;
    private JPanel pnlTempMax;
    private JPanel pnlTempMin;
    private JPanel pnlHumidite;
    private JPanel pnlNoConnexion;

    private JPanel pnlIconWeather;

    // Label
    private JLabel lblVille;
    private JLabel lblTemp;
    private JLabel lblTempMax;
    private JLabel lblTempMin;
    private JLabel lblHumidite;
    private JLabel lblNoConnexion;

    private JLabel lblIconWeather;

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
        setPreferredSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.CONTENT_PANEL_WIDTH.getSize(), ScreenSizeEnum.CONTENT_PANEL_HEIGHT.getSize()));
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

                // Changer le nom à l'affichage
                weatherInfos.setNomVille(tfLocation.getText());
                weatherInfos.setTemperature(weatherInfos.getTemperature());
                weatherInfos.setTempMax(weatherInfos.getTempMax());
                weatherInfos.setTempMin(weatherInfos.getTempMin());
                weatherInfos.setHumidity(weatherInfos.getHumidity());
                weatherInfos.setUrlPicture(weatherInfos.getUrlPicture());

                removeAll();
                validate();
                buildPanel();
                revalidate();
                repaint();
            }
        });

        // Création des labels
        lblVille = new JLabel("Location : " + weatherInfos.getNomVille());
        lblTemp = new JLabel("Temperature : " + weatherInfos.getTemperature() + "°C");
        lblTempMax = new JLabel("Maximum temperature : " + weatherInfos.getTempMax() + "°C");
        lblTempMin = new JLabel("Minimum temperature : " + weatherInfos.getTempMin() + "°C");
        lblHumidite = new JLabel("Humidity level : " + weatherInfos.getHumidity() + "%");

        //Icon du temps
        try {
            lblIconWeather = new JLabel(new ImageIcon(new URL(weatherInfos.getUrlPicture()) ) );
        } catch (MalformedURLException e1){
            System.out.println("URL invalide");
        }
        // Panel nord
        pnlNord = new JPanel();
        pnlNord.add(tfLocation);
        pnlNord.add(btnSearch);

        // Panel centre
        pnlCentre = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        pnlVille = new JPanel();
        pnlVille.add(lblVille);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlCentre.add(pnlVille, gridBagConstraints);

        pnlTemp = new JPanel();
        pnlTemp.add(lblTemp);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlCentre.add(pnlTemp, gridBagConstraints);

        pnlTempMax = new JPanel();
        pnlTempMax.add(lblTempMax);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlCentre.add(pnlTempMax, gridBagConstraints);

        pnlTempMin = new JPanel();
        pnlTempMin.add(lblTempMin);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        pnlCentre.add(pnlTempMin, gridBagConstraints);

        pnlHumidite = new JPanel();
        pnlHumidite.add(lblHumidite);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        pnlCentre.add(pnlHumidite, gridBagConstraints);

        pnlIconWeather = new JPanel();
        pnlIconWeather.add(lblIconWeather);
        pnlIconWeather.setBackground(Color.cyan.darker());
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        pnlCentre.add(pnlIconWeather, gridBagConstraints);

        // Panel si pas de connexion internet
        pnlNoConnexion = new JPanel();
        lblNoConnexion = new JLabel("Could not connect to server");
        pnlNoConnexion.add(lblNoConnexion);

        // Ajout des PANELS au Layout
        this.add(pnlNord, BorderLayout.NORTH);
        this.add(pnlCentre, BorderLayout.CENTER);
        this.add(pnlNoConnexion, BorderLayout.SOUTH);

        if (weatherInfos.isConnected() == false) {
            pnlCentre.setVisible(false);
            pnlNord.setVisible(true);
            pnlNoConnexion.setVisible(true);
        } else {
            pnlCentre.setVisible(true);
            pnlNord.setVisible(true);
            pnlNoConnexion.setVisible(false);
        }
    }
}
