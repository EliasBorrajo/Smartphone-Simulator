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
        // Listener pour add Contact
        if(e.getSource() == contactsGUI.getBtnAddContact())
        {
            System.out.println("BTN ADD CONTACT CLIQUE");
            contactsGUI.getCardLayoutContact().show(contactsGUI, "AddContact");
        }

        // Boutton retour sur la page d'ajout de contacte
        if(e.getSource() == contactsGUI.getPnlAddContact().getBtnBack())
        {
            System.out.println("Back from adding a new contact");
            contactsGUI.getCardLayoutContact().show(contactsGUI, "HomeContact");
        }

        // FOR ShowContactInfo LOOP
        // Listeners pour chaque bouton de contactes
        for (int i = 0; i < contactsGUI.getContacts().size(); i++)
        {
            // Listener pour les bouttons de chaque contacte de la liste
            if(e.getSource() == contactsGUI.getBtnShowContact()[i]) {
                System.out.println("BTN SHOW CONTACT N° : " + i);

                String finalName = contactsGUI.getContactNameShowContact()[i];
                contactsGUI.getCardLayoutContact().show(contactsGUI, finalName);
            }

            // Listener pour BTN BACK SHOWCONTACT
            if (e.getSource() == contactsGUI.getPnlShowContactInfo()[i].getBtnBack())
            {
                System.out.println("Back from SHowing contact Info");
                contactsGUI.getCardLayoutContact().show(contactsGUI, "HomeContact");
            }

            // Listener pour BTN EDIT SHOWCONTACT

            if(e.getSource() == contactsGUI.getPnlShowContactInfo()[i].getBtnEdit()) {
                String finalNameEditContact = contactsGUI.getContactNameEditContact()[i];
                contactsGUI.getCardLayoutContact().show(contactsGUI, finalNameEditContact);
            }

            // Listener pour BTN SAVE EDIT
            /*if(e.getSource() == contactsGUI.getPnlEditContactInfo()[i].getBtnSaveEdit())
            {
                System.out.println("coucou");
                // Récuperer le contenu des Text Fields à editer
                String tfFisrtName = contactsGUI.getPnlEditContactInfo()[i].getTfFirstName().getText();
                String tfLastname  = contactsGUI.getPnlEditContactInfo()[i].getTfLastName() .getText();
                String tfPhone     = contactsGUI.getPnlEditContactInfo()[i].getTfPhone()    .getText();

                // Set des nouvelles valeurs dans notre ADRESS BOOK
                contactsGUI.getJsonAddressBook().getContactArray().get(i).setFirstName(tfFisrtName);
                contactsGUI.getJsonAddressBook().getContactArray().get(i).setLastName (tfLastname);
                contactsGUI.getJsonAddressBook().getContactArray().get(i).setNoPhone  (tfPhone);
                System.out.println(contactsGUI.getJsonAddressBook().getContactArray().get(i).getNoPhone());

                reBuildApp();
            }*/

            /*// Listener pour BTN BACK EDIT
            if (e.getSource() == contactsGUI.getPnlEditContactInfo()[i].getBtnBackEdit()) {
                contactsGUI.getCardLayoutContact().show(contactsGUI,contactsGUI.getContactNameShowContact()[i]);
            }*/

            // Listener pour DELETE CONTACT
            if (e.getSource() == contactsGUI.getPnlShowContactInfo()[i].getBtnDeleteContact())
            {
                contactsGUI.getJsonAddressBook().getContactArray().remove(i); // supprime le contact de l'array

                // Refresh des PANNELS
                reBuildApp(); // @TODO : ATTENTION : Il peut tout casser, doit venir à la fin !
            }
        }
    }
    private void reBuildApp()
    {
        // Refresh des PANNELS
        System.out.println("\nREBUILD APP CONTACTE\n");
        contactsGUI.removeAll();
        contactsGUI.validate();
        contactsGUI.buildPnlContentContact();
        contactsGUI.buildCardLayout();
        contactsGUI.setListeners();
        contactsGUI.revalidate();
        contactsGUI.repaint();
    }
}
