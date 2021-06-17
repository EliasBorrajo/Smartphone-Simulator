/**
 *  Project              :   Smartphone simulation
 *
 *  Creation date        :   01.05.2021
 *  Render Date          :   18.06.2021
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

/**
 * This class will create the entire project
 */
public class SmartphoneDemo
{
    /**
     * Main that will create the Singleton and our smartphone
     */
    public static void main(String[] args) {
        // Creation of the singleton
        Config.getConfig();

        // Start the smartphone
        SmartphoneGUI myPhone =  new SmartphoneGUI();
    }
}

