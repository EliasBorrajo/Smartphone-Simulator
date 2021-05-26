package ch.hevs.smartphone.applications.contacts;


import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Contact implements Serializable {


    @Serial
    private static final long serialVersionUID = -7259141374952645688L;

    private String firstName;
    private String lastName;
    private String noPhone;

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

    // CONSTRUCTOR
    public Contact(String firstName, String lastName, String noPhone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.noPhone = noPhone;
    }

    // METHODS
    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", noPhone='" + noPhone + '\'' +
                '}';
    }
}