package ch.hevs.smartphone.applications.contacts.listeners;

import ch.hevs.smartphone.applications.contacts.ContactsGUI;
import ch.hevs.smartphone.applications.contacts.ShowContactInfo;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ContactListener implements ActionListener
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    ContactsGUI contactsGUI;
    ShowContactInfo showContactInfo;


    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContactListener(ContactsGUI contactsGUI)
    {
        this.contactsGUI = contactsGUI;
        System.out.println(this.contactsGUI);
    }

    /*public ContactListener(ShowContactInfo showContactInfo) {
        this.showContactInfo = showContactInfo;
        System.out.println(this.showContactInfo);
    }*/

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Listener pour add Contact
        if(e.getSource() == contactsGUI.getBtnAddContact())
        {
            System.out.println("BTN ADD CONTACT CLIQUE");
            contactsGUI.getCardLayoutContact().show(contactsGUI, "AddContact");
        }

        for (int i = 0; i < contactsGUI.getContacts().size(); i++)
        {
            // Listener pour showing contact
            if (e.getSource() == contactsGUI.getBtnShowContacts()[i]) {
                System.out.println("BTN SHOW CONTACT N° : " + i);

                String finalName = contactsGUI.getContactName()[i];
                contactsGUI.getCardLayoutContact().show(contactsGUI, finalName);
            }

            // Listener pour delete contact
            if (e.getSource() == contactsGUI.getPnlShowContactInfo()[i].getBtnDeleteContact()) {

                contactsGUI.getJsonAddressBook().getContactArray().remove(i); // supprime le contact de l'array

                // serialise à nouveau les données après modification
                try {
                    contactsGUI.getJsonAddressBook().write(contactsGUI.getJsonAddressBook().getmyObj(),
                            contactsGUI.getJsonAddressBook().getContactArray());
                } catch (BusinessException o){
                    System.out.println("Deleting contact error");
                }

                // Refresh des PANNELS
                contactsGUI.removeAll();
                contactsGUI.validate();

                contactsGUI.buildPnlContentContact();
                contactsGUI.buildCardsLayout();
                contactsGUI.setListeners();

                contactsGUI.revalidate();
                contactsGUI.repaint();

            }
        }

    }

}
