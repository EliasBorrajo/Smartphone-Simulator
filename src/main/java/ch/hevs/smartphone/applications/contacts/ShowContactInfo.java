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

    private JLabel pnlNoPhone;

    // LABEL
    private JLabel lblShowContactInfoTitle;
    private String name = "";
    private String noPhone = "";
    private Contact contact;

    // OTHER
    AddressBook addressBook = new AddressBook();

    // CONSTRUCTOR
    public ShowContactInfo(String name, String noPhone) {
        this.name = name;
        this.noPhone = noPhone;
        add(buildpnlShowContactInfo());
    }

    // METHODS
    private JPanel buildpnlShowContactInfo() {
        ArrayList<Contact> contacts = this.addressBook.getTabContact();

        pnlMainWindow = new JPanel(new BorderLayout());
        pnlMainWindow.setBackground(Color.red);

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();

        pnlNoPhone = new JLabel(noPhone);
        lblShowContactInfoTitle = new JLabel(this.name);

        pnlNorth.add(lblShowContactInfoTitle);
        pnlCentre.add(pnlNoPhone);

        pnlMainWindow.add(pnlNorth, BorderLayout.NORTH);
        pnlMainWindow.add(pnlCentre, BorderLayout.CENTER);

        return pnlMainWindow;

    }

}
