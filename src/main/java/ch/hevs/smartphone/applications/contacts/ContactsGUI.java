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
    private JPanel pnlJscrollContact;

    // SCROLL PAN
    private JScrollPane scrollPaneContact;

    // LABEL
    private JLabel lblContactTitle;

    // BUTTONS
    private MyButton btnAddContact;
    private JButton btnShowContact;


    // OTHER OBJECTS
    private AddressBook addressBook;

    // GETTERS
    public MyButton getBtnAddContact(){
        return btnAddContact;
    }

    // CONSTRUCTOR
    public ContactsGUI() {
        this.addressBook = new AddressBook();
        add(buildpnlContentContact());

    }


    // METHODS
    private JPanel buildpnlContentContact(){
        pnlHomeContact = new JPanel(new BorderLayout());

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        lblContactTitle = new JLabel("Contacts");
        scrollPaneContact = new JScrollPane();
        btnAddContact = new MyButton("+");

        pnlCentre.add(buildScrollPaneContact());
        pnlNorth.add(lblContactTitle);
        pnlNorth.add(btnAddContact);
        pnlHomeContact.add(pnlNorth, BorderLayout.NORTH);
        pnlHomeContact.add(pnlCentre, BorderLayout.CENTER);

        return pnlHomeContact;
    }

    private JScrollPane buildScrollPaneContact() {
        ArrayList<Contact> contacts = this.addressBook.getTabContact();
        String contactName = "";

        pnlJscrollContact = new JPanel(new GridLayout(0,1,5,5));

        if (contacts.size() == 0) {
            JLabel emptyContactMessage = new JLabel("No contact to show");
            pnlJscrollContact.add(emptyContactMessage);

        } else {
            for (Contact entity:contacts) {
                Contact contact = (Contact) entity;

                contactName = contact.getFirstName() + contact.getLastName();

                btnShowContact = new JButton(contactName);

                pnlJscrollContact.add(btnShowContact);

            }
        }
        scrollPaneContact = new JScrollPane(pnlJscrollContact);

        return scrollPaneContact;
    }





}