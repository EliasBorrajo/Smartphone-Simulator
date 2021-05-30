package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.serialization.JSONStorage;
import ch.hevs.smartphone.bases.MyButton;
import ch.hevs.smartphone.structure.layout.ContentLayout;
import ch.hevs.smartphone.structure.layout.FooterLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

    // BUTTON
    JButton btnDeleteContact;

    // OTHER
    JSONStorage addressBook = new JSONStorage();
    private String name = "";
    private String noPhone = "";

    // CONSTRUCTOR
    public ShowContactInfo(String name, String noPhone) throws IOException, BusinessException {
        this.name = name;
        this.noPhone = noPhone;
        add(buildpnlShowContactInfo());
    }

    // METHODS
    private JPanel buildpnlShowContactInfo() {
        ArrayList<Contact> contacts = this.addressBook.getContacts();

        pnlMainWindow = new JPanel(new BorderLayout());
        pnlMainWindow.setBackground(Color.red);

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        btnDeleteContact = new JButton("Delete Contact");

        pnlNoPhone = new JLabel(noPhone);
        lblShowContactInfoTitle = new JLabel(this.name);

        pnlNorth.add(lblShowContactInfoTitle, BorderLayout.CENTER);
        pnlNorth.add(btnDeleteContact, BorderLayout.EAST);
        pnlCentre.add(pnlNoPhone);

        pnlMainWindow.add(pnlNorth, BorderLayout.NORTH);
        pnlMainWindow.add(pnlCentre, BorderLayout.CENTER);

        btnDeleteContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*addressBook.get();
                addressBook.getTabContact().remove(this);
                addressBook.save();*/
            }
        });

        return pnlMainWindow;

    }

}
