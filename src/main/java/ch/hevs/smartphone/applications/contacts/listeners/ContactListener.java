package ch.hevs.smartphone.applications.contacts.listeners;

import ch.hevs.smartphone.applications.contacts.ContactsGUI;
import ch.hevs.smartphone.applications.contacts.EditContactInfo;

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
        System.out.println(this.contactsGUI);
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

                String finalName = contactsGUI.getContactName()[i];
                contactsGUI.getCardLayoutContact().show(contactsGUI, finalName);
            }

            /*if(e.getSource() == contactsGUI.getPnlShowContactInfo()[i].getBtnIcon())
            {
                // 1) CardLayout de la racine doit passer de l'APP contactes à l'APP Gallerie
                // 2) CardLayout Galleryie doit passer sur son Home pour choisir une photo
                // 3) On mouse

                // x) Reset des CardLayout
                // Serialiser la nouvelle photo
            }*/

            // Listener pour BTN BACK
            if (e.getSource() == contactsGUI.getPnlShowContactInfo()[i].getBtnBack())
            {
                System.out.println("Back from SHowing contact Info");
                contactsGUI.getCardLayoutContact().show(contactsGUI, "HomeContact");
            }

            /*// Listener pour Editer le contacte + SERIALISER
            if(e.getSource() == contactsGUI.getPnlShowContactInfo()[i].getBtnEdit())
            {
                // Récuperer le contenu des Text Fields à editer
                String tfFisrtName = contactsGUI.getPnlShowContactInfo()[i].getTfFirstName().getText();
                String tfLastname  = contactsGUI.getPnlShowContactInfo()[i].getTfLastName() .getText();
                String tfPhone     = contactsGUI.getPnlShowContactInfo()[i].getTfPhone()    .getText();

                // Set des nouvelles valeurs dans notre ADRESS BOOK
                contactsGUI.getJsonAddressBook().getContactArray().get(i).setFirstName(tfFisrtName);
                contactsGUI.getJsonAddressBook().getContactArray().get(i).setLastName (tfLastname);
                contactsGUI.getJsonAddressBook().getContactArray().get(i).setNoPhone  (tfPhone);

                reBuildApp();

            }*/

            if (e.getSource() == contactsGUI.getPnlShowContactInfo()[i].getBtnEdit()) {

            }

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
        contactsGUI.buildCardsLayout();
        contactsGUI.setListeners();
        contactsGUI.revalidate();
        contactsGUI.repaint();
    }
}
