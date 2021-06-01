package ch.hevs.smartphone.applications.contacts.listeners;

import ch.hevs.smartphone.applications.contacts.ContactsGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactListener implements ActionListener
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    ContactsGUI contactsGUI;


    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContactListener(ContactsGUI contactsGUI)
    {
        this.contactsGUI = contactsGUI;
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == contactsGUI.getBtnAddContact())
        {
            System.out.println("BTN ADD CONTACT CLIQUE");
            contactsGUI.getCardLayoutContact().show(contactsGUI, "AddContact");
        }

        for (int i = 0; i < contactsGUI.getContacts().size(); i++)
        {
            if (e.getSource() == contactsGUI.getBtnShowContacts()[i])
            {
                System.out.println("BTN SHOW CONTACT N° : " + i);

                String finalName = contactsGUI.getContactName()[i];
                contactsGUI.getCardLayoutContact().show(contactsGUI, finalName);
            }

        }
    }
}
