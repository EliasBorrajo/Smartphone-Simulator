package ch.hevs.smartphone.applications.contacts;

import java.io.Serial;
import java.io.Serializable;

/**
 * Attributes the strings that will be saved in the JSON for each contact
 *
 * @author Bourquin Jonathan
 */

public class Contact implements Serializable
{
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    @Serial
    private static final long serialVersionUID = -7259141374952645688L;

    // String
    private String firstName;
    private String lastName;
    private String noPhone;
    private String pathContactPhoto;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     * @param firstName
     * @param lastName
     * @param noPhone
     * @param pathContactPhoto
     */
    public Contact(String firstName, String lastName, String noPhone, String pathContactPhoto){
        this.firstName = firstName;
        this.lastName = lastName;
        this.noPhone = noPhone;
        this.pathContactPhoto = pathContactPhoto;
    }

    /**
     * Empty constructor
     */
    public Contact(){

    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * Custom toString method used to sys out print the attributes
     * @return
     */
    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", noPhone='" + noPhone + '\'' +
                ", contactPhoto=" + pathContactPhoto +
                '}';
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNoPhone() {
        return noPhone;
    }

    public String getContactPhoto() {
        return pathContactPhoto;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNoPhone(String noPhone) {
        this.noPhone = noPhone;
    }

    public void setContactPhoto(String pathContactPhoto)
    {
        this.pathContactPhoto = pathContactPhoto;
    }
}
