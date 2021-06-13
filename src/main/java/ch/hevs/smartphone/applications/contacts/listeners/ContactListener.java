package ch.hevs.smartphone.applications.contacts.listeners;


import ch.hevs.smartphone.structure.layout.ContentLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Bourquin Jonathan
 * Classe pour gérer tous les listeners qui concernent l'application des contacts
 */

public class ContactListener implements ActionListener {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Layout
    ContentLayout contentLayout;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    /**
     * Constructeur
     *
     * @param contentLayout
     */
    public ContactListener(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {

        // Listener de btnAddContact pour ajouter un contact
        if (e.getSource() == contentLayout.getPnlContact().getBtnAddContact()) {
            System.out.println("BTN ADD CONTACT CLIQUE");
            contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "AddContact");
        }

        // Listener de btnBack de pnlAddContact qui retourne sur le home de l'application contact
        if (e.getSource() == contentLayout.getPnlContact().getPnlAddContact().getBtnBack()) {
            System.out.println("Back from adding a new contact");
            contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "HomeContact");
        }

        // FOR ShowContactInfo LOOP
        // Listener pour chaque bouton de contact
        for (int i = 0; i < contentLayout.getPnlContact().getContacts().size(); i++) {

            // Listener pour les boutons de chaque contact de la liste, affiche pnlShowContactInfo
            if (e.getSource() == contentLayout.getPnlContact().getBtnShowContact()[i]) {
                System.out.println("BTN SHOW CONTACT N° : " + i);

                String finalName = contentLayout.getPnlContact().getContactNameShowContact()[i];
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), finalName);
            }

            // Listener de btnRetour de pnlShowContactInfo qui retourne sur le home de l'application contact
            if (e.getSource() == contentLayout.getPnlContact().getPnlShowContactInfo()[i].getBtnBack()) {
                System.out.println("Back from SHowing contact Info");
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "HomeContact");
            }

            // Listener de btnEdit de pnlShowContactInfo qui ouvre pnlEditContactInfo
            if (e.getSource() == contentLayout.getPnlContact().getPnlShowContactInfo()[i].getBtnEdit()) {
                String finalNameEditContact = contentLayout.getPnlContact().getContactNameEditContact()[i];
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), finalNameEditContact);
            }

            // Listener de btnSaveEdit de pnlEditContactInfo pour enregistrer les modifications fait à un contact
            // Retourne sur le home de l'application contact
            if (e.getSource() == contentLayout.getPnlContact().getPnlEditContactInfo()[i].getBtnSaveEdit()) {
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

            // Listener de btnBackEdit de pnlEditContactInfo qui retourne sur pnlShowContactInfo
            if (e.getSource() == contentLayout.getPnlContact().getPnlEditContactInfo()[i].getBtnBackEdit()) {
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact()
                        , contentLayout.getPnlContact().getContactNameShowContact()[i]);
            }

            // Listener de btnIconContact de pnlEditContactInfo
            // Permet de modifier la photo du contact
            if (e.getSource() == contentLayout.getPnlContact().getPnlEditContactInfo()[i].getBtnIconContact()) {
                // 1) On ouvre la fenetre de l'application Gallery
                contentLayout.getCardlayout().show(contentLayout, "Gallery");

                // 2) On masque les boutons du panel de l'image, et on affiche le bouton select
                // On masque les boutons normaux de toutes les images, et on affiche les boutons que l'on veut
                for (int j = 0; j < contentLayout.getPnlGallery().getPnlShowPhoto().length; j++) {
                    // 3) On affiche le bouton select
                    contentLayout.getPnlGallery().getPnlShowPhoto()[j].showSelectImageBtn();

                    // Récupère le PATH sur le click du boutton
                    int finalJ = j;     // Index image
                    int finalI = i;     // Index Contact

                    contentLayout.getPnlGallery().getPnlShowPhoto()[j].getBtnSelect().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String pathImage = contentLayout.getPnlGallery().getPnlShowPhoto()[finalJ].getPath();
                            System.out.println("path image " + contentLayout.getPnlGallery().getPnlShowPhoto()[finalJ].getPath());

                            // Modifier dans mon contact séléctionné le path de l'ancienne photo par le path de la photo choisie
                            contentLayout.getPnlContact().getJsonAddressBook().getContactArray().get(finalI).setContactPhoto(pathImage);

                            // 4) on masque le btn select
                            contentLayout.getPnlGallery().getPnlShowPhoto()[finalJ].showNormalBtn();

                            // 5) Rebuild des contacts
                            reBuildApp();

                            // 6)  Retour à l'application contact
                            contentLayout.getCardlayout().show(contentLayout, "Contact");
                            contentLayout.getPnlGallery().getCardGallHome().show(contentLayout.getPnlGallery(), "GallHOME");
                        }
                    });

                }

            }

            // Listener de btnDeleteContact de pnlShowContactInfo
            // Permet de supprimer le contact
            if (e.getSource() == contentLayout.getPnlContact().getPnlShowContactInfo()[i].getBtnDeleteContact()) {
                contentLayout.getPnlContact().getJsonAddressBook().getContactArray().remove(i); // supprime le contact de l'array

                // Refresh des PANELS
                reBuildApp(); // ATTENTION : Il peut tout casser, doit venir à la fin !
            }
        }
    }

    private void reBuildApp() {
        // Refresh des panels
        System.out.println("\nREBUILD APP CONTACTE\n");
        contentLayout.getPnlContact().removeAll();
        contentLayout.getPnlContact().validate();
        contentLayout.getPnlContact().buildPnlContentContact();
        contentLayout.getPnlContact().buildCardLayout();
        contentLayout.getPnlContact().setListeners();
        contentLayout.getPnlContact().revalidate();
        contentLayout.getPnlContact().repaint();
    }
}
