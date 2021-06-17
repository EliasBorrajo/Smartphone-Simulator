package ch.hevs.smartphone.applications.contacts.listeners;

import ch.hevs.smartphone.structure.layout.ContentLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is for every ActionListeners we have in our contact application
 * The first part concern the main panel of the application
 * The second part concern the ShowContactInfo panel
 * The third part concern the ActionListeners on panel EditContactInfo
 *
 * @author Bourquin Jonathan, Borrajo Elias, Lonfat Milena
 */
public class ContactListener implements ActionListener {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Layout
    ContentLayout contentLayout;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param contentLayout
     */
    public ContactListener(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * method to set listeners to most of the buttons used in the contact app
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    // First part : ActionListener on main panel of contact application ************************************************

        // ActionListener on btnAddContact : add a contact
        if (e.getSource() == contentLayout.getPnlContact().getBtnAddContact()) {
            contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "AddContact");
        }

        // ActionListener on btnBack : go back to contact home
        if (e.getSource() == contentLayout.getPnlContact().getPnlAddContact().getBtnBack()) {
            contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "HomeContact");
        }

    // Second part : ActionListener on panel ShowContactInfo ***********************************************************

        for (int i = 0; i < contentLayout.getPnlContact().getContactArray().size(); i++) {

            // ActionListener on each buttons contact: open ShowContactInfo panel
            if (e.getSource() == contentLayout.getPnlContact().getBtnShowContact()[i]) {
                String finalName = contentLayout.getPnlContact().getContactNameShowContact()[i];
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), finalName);
            }

            // ActionListener on btnBack : go back to contact home
            if (e.getSource() == contentLayout.getPnlContact().getPnlShowContactInfo()[i].getBtnBack()) {
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), "HomeContact");
            }

            // ActionListener on btnDeleteContact : Delete a contact
            if (e.getSource() == contentLayout.getPnlContact().getPnlShowContactInfo()[i].getBtnDeleteContact()) {
                // Delete contact in array
                contentLayout.getPnlContact().getJsonAddressBook().getContactArray().remove(i);
                // Refresh PANELS
                reBuildApp();
                break;
            }

    // Third part : ActionListeners on panel EditContactInfo ***********************************************************

            // ActionListener on btnEdit : open EditContactInfo panel
            if (e.getSource() == contentLayout.getPnlContact().getPnlShowContactInfo()[i].getBtnEdit()) {
                String finalNameEditContact = contentLayout.getPnlContact().getContactNameEditContact()[i];
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact(), finalNameEditContact);
            }

            // ActionListener on btnSaveEdit : save changes made to a contact
            if (e.getSource() == contentLayout.getPnlContact().getPnlEditContactInfo()[i].getBtnSaveEdit()) {
                // Retrieve the content of Textfield to edit
                String tfFisrtName = contentLayout.getPnlContact().getPnlEditContactInfo()[i].getTfFirstName().getText();
                String tfLastname = contentLayout.getPnlContact().getPnlEditContactInfo()[i].getTfLastName().getText();
                String tfPhone = contentLayout.getPnlContact().getPnlEditContactInfo()[i].getTfPhone().getText();

                // Set new values on JsonAddressBook
                contentLayout.getPnlContact().getJsonAddressBook().getContactArray().get(i).setFirstName(tfFisrtName);
                contentLayout.getPnlContact().getJsonAddressBook().getContactArray().get(i).setLastName(tfLastname);
                contentLayout.getPnlContact().getJsonAddressBook().getContactArray().get(i).setNoPhone(tfPhone);

                reBuildApp();
            }

            // ActionListener on btnBackEdit : open ShowContactInfo panel
            if (e.getSource() == contentLayout.getPnlContact().getPnlEditContactInfo()[i].getBtnBackEdit()) {
                contentLayout.getPnlContact().getCardLayoutContact().show(contentLayout.getPnlContact()
                        , contentLayout.getPnlContact().getContactNameShowContact()[i]);
            }

            // ActionListener on btnIconContact : allow to edit the contact's photo
            if (e.getSource() == contentLayout.getPnlContact().getPnlEditContactInfo()[i].getBtnIconContact()) {

                // 1) Open Gallery application panel
                contentLayout.getCardlayout().show(contentLayout, "Gallery");

                // Hide button to add photos
                contentLayout.getPnlGallery().getBtnAddPhoto().setVisible(false);

                for (int j = 0; j < contentLayout.getPnlGallery().getPnlShowPhoto().length; j++) {
                    // 2)  Change button type
                    contentLayout.getPnlGallery().getPnlShowPhoto()[j].showSelectImageBtn();

                    int finalJ = j;     // image index
                    int finalI = i;     // contact index

                    contentLayout.getPnlGallery().getPnlShowPhoto()[j].getBtnSelect().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // 3) Get the PATH on the click of select button
                            String pathImage = contentLayout.getPnlGallery().getPnlShowPhoto()[finalJ].getPath();

                            // Modify in my selected contact the path of the old photo by the path of the chosen photo
                            contentLayout.getPnlContact().getJsonAddressBook().getContactArray().get(finalI).setContactPhoto(pathImage);

                            // 4) Hide select button
                            contentLayout.getPnlGallery().getPnlShowPhoto()[finalJ].showNormalBtn();

                            // 5) Rebuild
                            reBuildApp();
                            contentLayout.getPnlGallery().getGalleryListener().rebuildApp();

                            // 6) Return to the Home of the contact application
                            contentLayout.getCardlayout().show(contentLayout, "Contact");
                        }
                    });
                }
            }
        }
    }

    /**
     * This method is to rebuild our application
     */
    private void reBuildApp() {
        contentLayout.getPnlContact().removeAll();
        contentLayout.getPnlContact().validate();
        contentLayout.getPnlContact().buildPnlContentContact();
        contentLayout.getPnlContact().buildCardLayout();
        contentLayout.getPnlContact().setListeners();
        contentLayout.getPnlContact().revalidate();
        contentLayout.getPnlContact().repaint();
    }
}
