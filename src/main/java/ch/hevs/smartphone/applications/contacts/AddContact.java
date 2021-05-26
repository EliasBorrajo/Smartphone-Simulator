package ch.hevs.smartphone.applications.contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddContact extends JPanel {

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
    private JTextField tfName = new JTextField("",50);
    private JTextField tfFirstName = new JTextField("", 50);
    private JTextField tfNoPhone = new JTextField("", 50);
    // private photo ?

    // BUTTON
    JButton btnSave = new JButton("Save");

    // GETTERS
    public JButton getBtnSave() {
        return btnSave;
    }

    // CONSTRUCTOR
    public AddContact() {
        // Parametre panel
        setSize(50, 50);
        setLayout(new BorderLayout());
        pnlCentre.setLayout(new GridLayout(3,2));

        // Ajout panels
        pnlNorth.add(lblContactsTitle);
        this.add(pnlNorth, BorderLayout.NORTH);

        pnlCentre.add(lblFirstName);
        pnlCentre.add(tfFirstName);

        pnlCentre.add(lblName);
        pnlCentre.add(tfName);

        pnlCentre.add(lblNoPhone);
        pnlCentre.add(tfNoPhone);

        this.add(pnlCentre, BorderLayout.CENTER);

        pnlSouth.add(btnSave);
        this.add(pnlSouth, BorderLayout.SOUTH);
    }
}