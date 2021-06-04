package ch.hevs.smartphone.applications.contacts.listeners;

import ch.hevs.smartphone.applications.contacts.ContactsGUI;
import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.structure.SmartphoneGUI;
import ch.hevs.smartphone.structure.layout.ContentLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ContactListener implements ActionListener
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    ContentLayout contentLayout;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContactListener(ContentLayout contentLayout)
    {
        this.contentLayout = contentLayout;
    }


    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Listener pour add Contact
        if (e.getSource() == contentLayout.getPnlContact().getBtnAddContact())
        {
            System.out.println("BTN ADD CONTACT CLIQUE");
            contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "AddContact");
        }

        // Boutton retour sur la page d'ajout de contacte
        if (e.getSource() == contentLayout.getPnlContact().getPnlAddContact().getBtnBack())
        {
            System.out.println("Back from adding a new contact");
            contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "HomeContact");
        }

        // FOR ShowContactInfo LOOP
        // Listeners pour chaque bouton de contactes
        for (int i = 0; i < contentLayout.getPnlContact().getContacts().size(); i++)
        {
            // Listener pour les bouttons de chaque contacte de la liste
            if (e.getSource() == contentLayout.getPnlContact().getBtnShowContact()[i])
            {
                System.out.println("BTN SHOW CONTACT N° : " + i);

                String finalName = contentLayout.getPnlContact().getContactNameShowContact()[i];
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), finalName);
            }

            // Listener pour BTN BACK SHOWCONTACT
            if (e.getSource() == contentLayout.getPnlContact().getPnlShowContactInfo()[i].getBtnBack())
            {
                System.out.println("Back from SHowing contact Info");
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "HomeContact");
            }

            // Listener pour BTN EDIT SHOWCONTACT
            if (e.getSource() == contentLayout.getPnlContact().getPnlShowContactInfo()[i].getBtnEdit())
            {
                String finalNameEditContact = contentLayout.getPnlContact().getContactNameEditContact()[i];
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), finalNameEditContact);
            }

            // Listener pour BTN SAVE EDIT
            if (e.getSource() == contentLayout.getPnlContact().getPnlEditContactInfo()[i].getBtnSaveEdit())
            {
                // Récuperer le contenu des Text Fields à editer
                String tfFisrtName = contentLayout.getPnlContact().getPnlEditContactInfo()[i].getTfFirstName().getText();
                String tfLastname = contentLayout.getPnlContact().getPnlEditContactInfo()[i].getTfLastName().getText();
                String tfPhone = contentLayout.getPnlContact().getPnlEditContactInfo()[i].getTfPhone().getText();

                // Set des nouvelles valeurs dans notre ADRESS BOOK
                contentLayout.getPnlContact().getJsonAddressBook().getContactArray().get(i).setFirstName(tfFisrtName);
                contentLayout.getPnlContact().getJsonAddressBook().getContactArray().get(i).setLastName(tfLastname);
                contentLayout.getPnlContact().getJsonAddressBook().getContactArray().get(i).setNoPhone(tfPhone);

                reBuildApp();
            }

            // Listener pour BTN BACK EDIT
            if (e.getSource() == contentLayout.getPnlContact().getPnlEditContactInfo()[i].getBtnBackEdit())
            {
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact()
                        , contentLayout.getPnlContact().getContactNameShowContact()[i]);
            }

            // Listener pour BTN ICON EDIT
            if (e.getSource() == contentLayout.getPnlContact().getPnlEditContactInfo()[i].getBtnIconContact())
            {
                // 1) On ouvre la fenetre de l'APP Gallery
                contentLayout.getCardlayout().show(contentLayout, "Gallery");

                // 2) On Sélectione la fenêtre que l'on veut afficher dans l'app Gallery
                contentLayout.getPnlGallery().getCardGallHome().show(contentLayout.getPnlGallery(), "Choose"); // @TODO : Changer le STRING avec le nouveau panneau à MILENA

                // Selectionner la PHOTO à utiliser en profil

                // Reset du cardLayout de l'APP Gallery

                // Reset du cardlayout principale, et ainsi retour sur notre APP contacte.

                // @TODO : NOTE : Sur le click du bouton, dans le pannel de GAllery, pour choisir sa photo, ce click la va donner le chemin d'accès à la photo pour le boutonIcon
                // setIconPathContactPicture = PathimageSelected
                // reBuildIcon(); -- > = BuildPanelShowContacte --> ReBuild = refresh à faire




                /*//rebuildGalleryWOListener();
                contentLayout.removeAll();
                contentLayout.getPnlContact().validate();
                try {
                    contentLayout.buildpnlContent();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
                contentLayout.getPnlContact().revalidate();
                contentLayout.getPnlContact().repaint();

                contentLayout.getPnlGallery().getCardGallHome().show(contentLayout.getPnlGallery(),"Photo0");
                System.out.println("done");*/
            }

            // Listener pour DELETE CONTACT
            if (e.getSource() == contentLayout.getPnlContact().getPnlShowContactInfo()[i].getBtnDeleteContact())
            {
                contentLayout.getPnlContact().getJsonAddressBook().getContactArray().remove(i); // supprime le contact de l'array

                // Refresh des PANNELS
                reBuildApp(); // @TODO : ATTENTION : Il peut tout casser, doit venir à la fin !
            }
        }
    }

    private void reBuildApp()
    {
        // Refresh des PANNELS
        System.out.println("\nREBUILD APP CONTACTE\n");
        contentLayout.getPnlContact().removeAll();
        contentLayout.getPnlContact().validate();
        contentLayout.getPnlContact().buildPnlContentContact();
        contentLayout.getPnlContact().buildCardLayout();
        contentLayout.getPnlContact().setListeners();
        contentLayout.getPnlContact().revalidate();
        contentLayout.getPnlContact().repaint();
    }

    private void rebuildGalleryWOListener()
    {
        System.out.println("\nREBUILD APP GALLERY\n");

        contentLayout.getPnlGallery().removeAll();
        System.out.println("remove");

        contentLayout.getPnlGallery().validate();
        System.out.println("validate");

        contentLayout.getPnlGallery().buildPnlHomeGall();
        System.out.println("buildHomePanel");

        contentLayout.getPnlGallery().buildCardLayout();
        System.out.println("buildCardLayout");

        contentLayout.getPnlGallery().buildListeners();
        System.out.println("buildListeners");

        contentLayout.getPnlGallery().revalidate();
        System.out.println("revalidate");

        contentLayout.getPnlGallery().repaint();
        System.out.println("repaint");
    }
}
