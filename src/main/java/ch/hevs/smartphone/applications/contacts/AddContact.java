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
    private JTextField tfName = new JTextField("N",50);
    private JTextField tfFirstName = new JTextField("FN", 50);
    private JTextField tfNoPhone = new JTextField("NP", 50);

    // OTHER OBJECTS
    AddressBook ad = new AddressBook();

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

        btnSave.setActionCommand("action1");
        btnSave.setActionCommand("action2");
        btnSave.setActionCommand("action3");
        btnSave.addActionListener(new ListenerSaveAddContact(tfFirstName, tfName, tfNoPhone));
//        contentLayout.getfLayout().getBtnBack().addActionListener(new ListenerBackAddContact());

        return this;
    }

    // INNER CLASS
    class ListenerSaveAddContact implements ActionListener {

        JTextField inputFN;
        JTextField inputN;
        JTextField inputNP;

        public ListenerSaveAddContact(JTextField firstName, JTextField name, JTextField noPhone) {
            inputFN = firstName;
            inputN = name;
            inputNP = noPhone;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Contact c = new Contact("","","");

            String text1 = "";
            text1 = inputFN.getText();
            c.setFirstName(text1);

            String text2 = "";
            text2 = inputN.getText();
            c.setLastName(text2);

            String text3 = "";
            text3 = inputNP.getText();
            c.setNoPhone(text3);

            System.out.println(c);

            ad.addContact(c);
            ad.save();

        }
    }
}
