/**
 *  Nom                 :   Simulation de smartphone
 *
 *  Date de création    :   01.05.2021
 *  Date de rendu       :
 *
 *  Autheurs            :   Jonathan Bourquin
 *                          Milena   Lonfat
 *                          Elias    Borrajo
 *
 *  Description         :   Projet de 2èm Semestre à la HEG - Sierre - FIG.
 *                          Le but est de simuler sur un écran, un smartphone avec un comportement réalise.
 *                          Il est nécessaire d'avoir 3 apps au minimum sur le téléphone,
 *                          dont 2 qui communiqueront ensembles, et une qui devra récuperer des informations d'une API.
 */
package ch.hevs.smartphone.structure;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.parameters.jsonStorage.Config;

import java.io.IOException;

/**
 * @author Borrajo Elias
 * Main qui va nous créer le projet au complet
 */
public class SmartphoneDemo
{
    /**
     * Création du main
     * @throws IOException
     * @throws BusinessException
     * */
    public static void main(String[] args) throws IOException, BusinessException
    {
        //Créer le singleTon au début.
        Config.getConfig();

        // Démarer le smartPhone
        SmartphoneGUI myPhone =  new SmartphoneGUI();
    }
}
