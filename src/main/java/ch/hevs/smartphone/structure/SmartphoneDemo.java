package ch.hevs.smartphone.structure;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.jsonStorage.Config;

import java.io.IOException;

public class SmartphoneDemo
{
    public static void main(String[] args) throws IOException, BusinessException
    {
        //Créer le singleTon au début.
        Config.getConfig();

        // Démarer le smartPhone
        new SmartphoneGUI();
    }
}
