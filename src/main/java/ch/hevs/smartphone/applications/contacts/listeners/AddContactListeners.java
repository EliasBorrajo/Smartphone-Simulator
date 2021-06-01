package ch.hevs.smartphone.applications.contacts.listeners;

import ch.hevs.smartphone.applications.contacts.AddContact;
import ch.hevs.smartphone.applications.contacts.Contact;
import ch.hevs.smartphone.errors.BusinessException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
public class AddContactListeners implements ActionListener {
    private AddContact ad;
    private Contact c;

    private JTextField inputFN;
    private JTextField inputN;
    private JTextField inputNP;



    public AddContactListeners(AddContact ad, Contact c, JTextField firstName, JTextField name, JTextField noPhone) {
        this.ad = ad;
        this.c  = c;
        inputFN = firstName;
        inputN = name;
        inputNP = noPhone;
    }

    public AddContactListeners() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        c = new Contact("","","");


        String text1 = "";
        text1 = inputFN.getText();
        c.setFirstName(text1);

        String text2 = "";
        text2 = inputN.getText();
        c.setLastName(text2);

        String text3 = "";
        text3 = inputNP.getText();
        c.setNoPhone(text3);

        System.out.println(c);

        ad.addContact(c);
        System.out.println("AddContact1");

        try {
            ad.write(ad.getmyObj(),ad.getContacts());
            System.out.println(ad.getmyObj());
        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }

        System.out.println("AddContact2");

        inputFN.setText("");
        inputN.setText("");
        inputNP.setText("");

    }
}
*/
