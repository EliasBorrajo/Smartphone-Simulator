package ch.hevs.smartphone.applications.contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddContact extends JPanel
{

    // PANEL
    private JPanel pnlNorth = new JPanel();
    private JPanel pnlCentre = new JPanel();
    private JPanel pnlSouth = new JPanel();

    // LABEL
    private JLabel lblContactsTitle = new JLabel("Add new contact");
    private JLabel lblName = new JLabel("Last Name : ");
    private JLabel lblFirstName = new JLabel("First name : ");
    private JLabel lblNoPhone = new JLabel("Phone number : ");

    // TEXTFIELD
    private JTextField tfName = new JTextField("N",50);
    private JTextField tfFirstName = new JTextField("FN", 50);
    private JTextField tfNoPhone = new JTextField("NP", 50);

    // OTHER OBJECTS
    protected AddressBook ad = new AddressBook();
    protected ContactsGUI cGUI = new ContactsGUI();

    // TODO fonction pour ajouter une image de la galerie

    // BUTTON
    JButton btnSave = new JButton("Save");

    // GETTERS
    public JButton getBtnSave() {
        return btnSave;
    }

    // CONSTRUCTOR
    public AddContact() {
        buildPnlContent();
    }

    // METHODS
    private JPanel buildPnlContent() {

        // Parametre du frame
        setSize(50, 50);
        setLayout(new BorderLayout());

        // Parametre des JPanel
        pnlCentre.setLayout(new GridLayout(3,2));

        // Ajout panels à la Frame
        pnlNorth.add(btnSave);
        this.add(pnlNorth, BorderLayout.NORTH);

        pnlCentre.add(lblFirstName);
        pnlCentre.add(tfFirstName);
        pnlCentre.add(lblName);
        pnlCentre.add(tfName);
        pnlCentre.add(lblNoPhone);
        pnlCentre.add(tfNoPhone);
        this.add(pnlCentre, BorderLayout.CENTER);

        pnlSouth.setLayout(new GridLayout());
        pnlSouth.add(btnSave);
        this.add(pnlSouth, BorderLayout.SOUTH);


        return this;
    }


    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public JTextField getTfName()
    {
        return tfName;
    }

    public JTextField getTfFirstName()
    {
        return tfFirstName;
    }

    public JTextField getTfNoPhone()
    {
        return tfNoPhone;
    }
}

