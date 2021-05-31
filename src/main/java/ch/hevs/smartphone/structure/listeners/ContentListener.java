package ch.hevs.smartphone.structure.listeners;

import ch.hevs.smartphone.applications.contacts.AddContact;
import ch.hevs.smartphone.structure.SmartphoneGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Classe qui s'occupe de toutes les actions des boutons du CONTENT
 */

public class ContentListener implements ActionListener
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    SmartphoneGUI phone;    // Permet de récuperer tous les éléments du telephone


    //*****************************************************************************
    //    // C O N S T R U C T E U R
    //*****************************************************************************
    public ContentListener(SmartphoneGUI smartPhone)
    {
        this.phone = smartPhone;
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("BOUTTON SAVE");

        // FAIRE SAVE
       /* phone.getContentLayout().getPnlAddContact().getBtnSave().addActionListener
                (new AddContact.ListenerSaveAddContact
                        (
                        phone.getContentLayout().getPnlAddContact().getTfFirstName(),
                        phone.getContentLayout().getPnlAddContact().getTfName(),
                        phone.getContentLayout().getPnlAddContact().getTfNoPhone()
                        )
                );*/
        // FAIRE REFRESH
        System.out.println("ContentLayout");
        phone.getContentLayout().getPnlContact().removeAll();
       // phone.getContentLayout().add(phone.getContentLayout().getPnlContact().buildpnlContentContact());
        phone.getContentLayout().getCardlayout().show(phone.getContentLayout().getPnlContent(),"Contact");


        /*
        if(e.getSource() == phone.footer.getBtnHome())
        {
            phone.content.getCardlayout().show(phone.content.getPnlContent(), "Home");
        }
        */

    }
}
