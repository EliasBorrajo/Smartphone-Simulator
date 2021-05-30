package ch.hevs.smartphone.structure;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.structure.frame.SmartphoneGUI;

import java.io.IOException;

public class SmartphoneDemo {
    public static void main(String[] args) throws IOException, BusinessException {
        new SmartphoneGUI();
    }
}
