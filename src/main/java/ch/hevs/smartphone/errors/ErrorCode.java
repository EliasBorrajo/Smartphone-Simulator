package ch.hevs.smartphone.errors;

/**
 * Error code for contact and gallery applications
 *
 * @author Bourquin Jonathan
 */

public enum ErrorCode {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    READING_JSON_STORAGE_CONTACT_ERROR(101),
    READING_JSON_STORAGE_GALLERY_ERROR(102),
    WRONG_URL_ERROR(200);



    private final int code;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param code
     */
    ErrorCode(int code) {
        this.code = code;
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public int getCode() {
        return code;
    }
}

