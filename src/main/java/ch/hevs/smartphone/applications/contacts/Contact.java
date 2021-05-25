package ch.hevs.smartphone.applications.contacts;


import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class Contact {
    Scanner scan = new Scanner(System.in);

    private String firstName;
    private String lastName;
    private String noPhone;

    // CONSTRUCTOR
    public Contact(String firstName, String lastName, String noPhone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.noPhone = noPhone;
    }

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

}