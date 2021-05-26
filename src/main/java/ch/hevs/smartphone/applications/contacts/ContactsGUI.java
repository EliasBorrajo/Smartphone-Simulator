package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.bases.Button;

import javax.swing.*;
import java.awt.*;

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
    private Button btnAddContact;

    public ContactsGUI() {
        add(buildpnlContentContact());
    }

    private JPanel buildpnlContentContact(){
        pnlHomeContact = new JPanel(new BorderLayout());

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        lblContactTitle = new JLabel("Contacts");
        scrollPaneContact = new JScrollPane();
        btnAddContact = new Button("+");

        pnlCentre.add(scrollPaneContact);
        pnlNorth.add(lblContactTitle);
        pnlNorth.add(btnAddContact);
        pnlHomeContact.add(pnlNorth, BorderLayout.NORTH);
        pnlHomeContact.add(pnlCentre, BorderLayout.CENTER);

        return pnlHomeContact;
    }

    public Button getBtnAddContact(){return btnAddContact;}

}