package ch.hevs.smartphone.structure;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;

import java.io.IOException;

public class SmartphoneDemo {
    public static void main(String[] args) throws IOException, BusinessException
    {
        String cfg = System.getenv("SMARTPHONEPROJECT");
        System.out.println("VARIABLE D'ENVIRONNEMENT : ");
        System.out.println(cfg);
        System.out.println("VARIABLE D'ENVIRONNEMENT : ");
        new SmartphoneGUI();
    }
}
