package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.bases.MyButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ContactsGUI extends JPanel {
    // PANEL
    private JPanel pnlHomeContact;
    private JPanel pnlNorth;
    private JPanel pnlCentre;

    // SCROLL PAN
    private JScrollPane scrollPaneContact;

    // LABEL
    private JLabel lblContactTitle;

    // BUTTONS
    private MyButton btnAddContact;

    public ContactsGUI() {
        add(buildpnlContentContact());
    }

    private JPanel buildpnlContentContact(){
        pnlHomeContact = new JPanel(new BorderLayout());

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        lblContactTitle = new JLabel("Contacts");
        scrollPaneContact = new JScrollPane();
        btnAddContact = new MyButton("+");

        pnlCentre.add(scrollPaneContact);
        pnlNorth.add(lblContactTitle);
        pnlNorth.add(btnAddContact);
        pnlHomeContact.add(pnlNorth, BorderLayout.NORTH);
        pnlHomeContact.add(pnlCentre, BorderLayout.CENTER);

        return pnlHomeContact;
    }

    public MyButton getBtnAddContact(){return btnAddContact;}

}