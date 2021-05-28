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
    private JButton[] btnShowContacts;


    // OTHER OBJECTS
    private AddressBook addressBook;
    String contactName = "";

    // GETTERS
    public MyButton getBtnAddContact(){
        return btnAddContact;
    }

    public JButton[] getBtnShowContact() {
        return btnShowContacts;
    }

    public String getContactName() {
        return contactName;
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

        pnlNorth.add(lblContactTitle);
        pnlNorth.add(btnAddContact);
        pnlHomeContact.add(pnlNorth, BorderLayout.NORTH);
        pnlHomeContact.add(buildScrollPaneContact(), BorderLayout.CENTER);

        return pnlHomeContact;
    }

    private JScrollPane buildScrollPaneContact() {
        ArrayList<Contact> contacts = this.addressBook.getTabContact();
        btnShowContacts = new JButton[contacts.size()];

        pnlJscrollContact = new JPanel(new GridLayout(0,1,5,5));

        if (contacts.size() == 0) {
            JLabel emptyContactMessage = new JLabel("No contact to show");
            pnlJscrollContact.add(emptyContactMessage);
        } else {
            for (int i=0; i<contacts.size(); i++) {
                contactName = contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName();

                btnShowContacts[i] = new JButton(contactName);

                pnlJscrollContact.add(btnShowContacts[i]);
            }
            /*for (Contact entity:contacts) {
                Contact contact = (Contact) entity;

//                contactName = contact.getFirstName() + " " + contact.getLastName();

                btnShowContact = contact.getBtnContactInfo();

                pnlJscrollContact.add(btnShowContact);

            }*/
        }
        scrollPaneContact = new JScrollPane(pnlJscrollContact);
        scrollPaneContact.setPreferredSize(new Dimension(280, 500));
        scrollPaneContact.setMinimumSize(new Dimension(280, 500));
        scrollPaneContact.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneContact.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        return scrollPaneContact;
    }





}