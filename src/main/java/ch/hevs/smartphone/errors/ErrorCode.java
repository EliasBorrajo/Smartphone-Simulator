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
    BAD_PARAMETER(100), // Calls the constructor
    READING_JSON_STORAGE_CONTACT_ERROR(200),
    READING_JSON_STORAGE_GALLERY_ERROR(300);

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

