package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.listeners.ContactListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ShowContactInfo extends JPanel
{

    // PANEL
    private JPanel pnlMainWindow;
    private JPanel pnlNorth;
    private JPanel pnlCentre;
    private JPanel pnlSouth;

    // LABEL
    private JLabel lblShowContactInfoTitle;
    private JLabel pnlNoPhone;

    // BUTTON
    private JButton btnDeleteContact;

    // OTHER
    private ContactsGUI contactsGUI;
    private ArrayList<Contact> contacts;
    private ContactListener myListener;

    private String name = "";
    private String noPhone = "";

    // CONSTRUCTOR
    public ShowContactInfo(ContactsGUI contactsGUI, String name, String noPhone) throws IOException, BusinessException
    {
        this.contactsGUI = contactsGUI;
        this.name = name;
        this.noPhone = noPhone;
        buildpnlShowContactInfo();
        setListeners();
    }

    // METHODS
    private JPanel buildpnlShowContactInfo() {
        contacts = contactsGUI.getJsonAddressBook().getContactArray();

        this.setLayout(new BorderLayout());
        this.setBackground(Color.red);
        /*pnlMainWindow = new JPanel(new BorderLayout());
        pnlMainWindow.setBackground(Color.red);*/

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        btnDeleteContact = new JButton("Delete Contact");

        pnlNoPhone = new JLabel(noPhone);
        lblShowContactInfoTitle = new JLabel(this.name);

        pnlNorth.add(lblShowContactInfoTitle, BorderLayout.CENTER);
        pnlNorth.add(btnDeleteContact, BorderLayout.EAST);
        pnlCentre.add(pnlNoPhone);

        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCentre, BorderLayout.CENTER);

        return this;

    }

    protected void setListeners()
    {
        myListener = new ContactListener(contactsGUI);

        btnDeleteContact.addActionListener(myListener);

    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************

    public JButton getBtnDeleteContact() {
        return btnDeleteContact;
    }
}

