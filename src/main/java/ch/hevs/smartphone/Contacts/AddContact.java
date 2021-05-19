package ch.hevs.smartphone.Contacts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddContact extends JPanel {

    //ENregistrer les contacts dans un fichier en local

    // PANEL
    private JPanel pnlNorth = new JPanel();
    private JPanel pnlCentre = new JPanel();
    private JPanel pnlSouth = new JPanel();

    // LABEL
    private JLabel contactsTitle = new JLabel("AJOUT CONTACT");
    private JLabel firstNameLabel = new JLabel("Prénom : ");
    private JLabel nameLabel = new JLabel("Nom : ");
    private JLabel noPhoneLabel = new JLabel("No. téléphone : ");

    // TEXTFIELD
    private JTextField name = new JTextField("last name",50);
    private JTextField firstName = new JTextField("first name", 50);
    private JTextField noPhone = new JTextField("No. phone", 50);
    // private photo ?

    // BUTTON
    JButton btnSave = new JButton("save");
    /*JButton btnCancel = new JButton("cancel");*/

    // ADDRESS BOOK OBJECT
    Contacts myAdressBook = new Contacts();

    // LIST
    List<String[]> contactList = new ArrayList<String[]>();
    JList contactJList;

    // GETTER
    public Contacts getMyAdressBook() {
        return myAdressBook;
    }

    public JList getContactJList() {
        return contactJList;
    }

    public AddContact() {

        // Parametre du frame
        setSize(50, 50);
        setLayout(new BorderLayout());

        // Parametre des JPanel
        pnlCentre.setLayout(new GridLayout(3,2));

        // Ajout panels à la Frame
        pnlNorth.add(contactsTitle);
        this.add(pnlNorth, BorderLayout.NORTH);

        pnlCentre.add(firstNameLabel);
        pnlCentre.add(firstName);
        pnlCentre.add(nameLabel);
        pnlCentre.add(name);
        pnlCentre.add(noPhoneLabel);
        pnlCentre.add(noPhone);
        this.add(pnlCentre, BorderLayout.CENTER);

        pnlSouth.setLayout(new GridLayout());
        pnlSouth.add(btnSave);
        /*pnlSouth.add(btnCancel);*/
        this.add(pnlSouth, BorderLayout.SOUTH);

        btnSave.setActionCommand("action1");
        btnSave.setActionCommand("action2");
        btnSave.setActionCommand("action3");

        btnSave.addActionListener(new ListenerAddContact(firstName, name, noPhone));
        /*btnCancel.addActionListener(new ListenerCancelAddContact());*/

    }

    class ListenerAddContact implements ActionListener {

        JTextField inputFN;
        JTextField inputN;
        JTextField inputNP;

        public ListenerAddContact(JTextField firstName, JTextField name, JTextField noPhone) {
            inputFN = firstName;
            inputN = name;
            inputNP = noPhone;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String text1 = "";
            text1 = inputFN.getText();
            myAdressBook.addContactFirstName(text1);

            String text2 = "";
            text2 = inputN.getText();
            myAdressBook.addContactLastName(text2);

            String text3 = "";
            text3 = inputNP.getText();
            myAdressBook.addNoPhone(text3);

            myAdressBook.display(myAdressBook.getFirstName());
            myAdressBook.display(myAdressBook.getLastName());
            myAdressBook.display(myAdressBook.getNoPhone());

            contactList.add(myAdressBook.getFirstName());
            contactList.add(myAdressBook.getLastName());
            contactList.add(myAdressBook.getNoPhone());

            contactJList = new JList(contactList.toArray());

        }
    }

    class ListenerCancelAddContact implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}