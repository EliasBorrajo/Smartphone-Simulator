package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.structure.layout.ContentLayout;
import ch.hevs.smartphone.structure.layout.FooterLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowContactInfo extends JPanel {

    // PANEL
    private JPanel pnlMainWindow;
    private JPanel pnlNorth;
    private JPanel pnlCentre;
    private JPanel pnlSouth;

    private JPanel pnlNoPhone;

    // LABEL
    private JLabel lblShowContactInfoTitle;
    private String name = "";

    // OTHER
    AddressBook addressBook = new AddressBook();

    // CONSTRUCTOR
    public ShowContactInfo(String name) {
        this.name = name;
        add(buildpnlShowContactInfo());
    }

    // METHODS
    private JPanel buildpnlShowContactInfo() {
        ArrayList<Contact> contacts = this.addressBook.getTabContact();

        pnlMainWindow = new JPanel(new BorderLayout());
        pnlMainWindow.setBackground(Color.red);

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();

        pnlNoPhone = new JPanel();
        lblShowContactInfoTitle = new JLabel(name);

        pnlNorth.add(lblShowContactInfoTitle);

        pnlMainWindow.add(pnlNorth);

        return pnlMainWindow;

    }

}
