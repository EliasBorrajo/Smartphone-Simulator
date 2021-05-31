package ch.hevs.smartphone.applications.contacts;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.contacts.serialization.JSONStorageContact;
import ch.hevs.smartphone.parameters.button.Button;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ContactsGUI extends JPanel
{
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
    private Button btnAddContact;
    private JButton[] btnShowContacts;


    // OTHER OBJECTS
    private JSONStorageContact addressBook;
    String contactName = "";

    // GETTERS
    public Button getBtnAddContact()
    {
        return btnAddContact;
    }

    public JButton[] getBtnShowContact()
    {
        return btnShowContacts;
    }

    public JPanel getPnlJscrollContact()
    {
        return pnlJscrollContact;
    }

    public JSONStorageContact getAddressBook()
    {
        return addressBook;
    }

    // SETTER
    public void setPnlJscrollContact(JPanel pnlJscrollContact)
    {
        this.pnlJscrollContact = pnlJscrollContact;
    }

    public String getContactName()
    {
        return contactName;
    }

    // CONSTRUCTOR
    public ContactsGUI() throws IOException, BusinessException
    {
        this.addressBook = new JSONStorageContact();
        add(buildpnlContentContact());
    }

    // METHODS
    public JPanel buildpnlContentContact()
    {
        pnlHomeContact = new JPanel(new BorderLayout());

        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        lblContactTitle = new JLabel("Contacts");
        scrollPaneContact = new JScrollPane();
        btnAddContact = new Button("+");

        pnlNorth.add(lblContactTitle);
        pnlNorth.add(btnAddContact);
        System.out.println("MEthode Build 1");
        pnlHomeContact.add(pnlNorth, BorderLayout.NORTH);
        System.out.println("MEthode Build 2");

        pnlHomeContact.add(buildScrollPaneContact(), BorderLayout.CENTER);

        return pnlHomeContact;
    }

    public JScrollPane buildScrollPaneContact()
    {
        ArrayList<Contact> contacts = this.addressBook.getContactArray();
        btnShowContacts = new JButton[contacts.size()];

        pnlJscrollContact = new JPanel(new GridLayout(0, 1, 5, 5));

        if (contacts.size() == 0)
        {
            JLabel emptyContactMessage = new JLabel("No contact to show");
            pnlJscrollContact.add(emptyContactMessage);
        } else
        {
            for (int i = 0; i < contacts.size(); i++)
            {
                contactName = contacts.get(i).getFirstName() + " " + contacts.get(i).getLastName();

                btnShowContacts[i] = new JButton(contactName);

                pnlJscrollContact.add(btnShowContacts[i]);
            }
        }
        scrollPaneContact = new JScrollPane(pnlJscrollContact);
        scrollPaneContact.setPreferredSize(new Dimension(280, 500));
        scrollPaneContact.setMinimumSize(new Dimension(280, 500));
        scrollPaneContact.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneContact.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        return scrollPaneContact;
    }


}
