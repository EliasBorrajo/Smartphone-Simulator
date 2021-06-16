/**
 *  Project              :   Smartphone simulation
 *
 *  Creation date        :   01.05.2021
 *  Render Date          :
 *
 *  Authors              :   Bourquin  Jonathan
 *                           Lonfat    Milena
 *                           Borrajo   Elias
 *
 *  Description          :   Second semester project at HEG - Sierre - FIG.
 *                           The goal is to simulate a smartphone with realistic behavior on a screen.
 *                           Three applications on the phone have been developed.
 *                           A Contact app and a gallery app which communicate together,
 *                           and a weather app that retrieves information from an API.
 */

package ch.hevs.smartphone.structure;

import ch.hevs.smartphone.parameters.jsonStorage.Config;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * This class will create the entire project
 */
public class SmartphoneDemo
{
    /**
     * Main that will create the Singleton and our smartphone
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel()); // Mandatory to manage the risks of "UnsupportedLookAndFeelException" errors to be able to use it
        } catch (UnsupportedLookAndFeelException e) {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }

        // Creation of the singleton.
        Config.getConfig();

        // Start the smartphone
        SmartphoneGUI myPhone =  new SmartphoneGUI();
    }
}

