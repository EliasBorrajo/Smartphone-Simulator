package ch.hevs.smartphone.applications.contacts;


import ch.hevs.smartphone.applications.gallery.Photo;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class Contact implements Serializable
{

    @Serial
    private static final long serialVersionUID = -7259141374952645688L;

    private String firstName;
    private String lastName;
    private String noPhone;
    private Photo  contactPhoto;
    //private JButton btnContactInfo;

    // GETTERS
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNoPhone() {
        return noPhone;
    }

    public Photo getContactPhoto() {
        return contactPhoto;
    }

//    public JButton getBtnContactInfo() {
//        return btnContactInfo;
//    }

    // SETTERS
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNoPhone(String noPhone) {
        this.noPhone = noPhone;
    }

    public void setContactPhoto(Photo contactPhoto) {
        this.contactPhoto = contactPhoto;
    }

    // CONSTRUCTOR
    public Contact(String firstName, String lastName, String noPhone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.noPhone = noPhone;
//        this.btnContactInfo = new JButton(this.firstName + " " + this.lastName);
    }
    public Contact(){

    }

    // METHODS
    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", noPhone='" + noPhone + '\'' +
                ", contactPhoto=" + contactPhoto +
                '}';
    }
}
