package ch.hevs.smartphone.applications.contacts.errors;

/**
 * @author Bourquin Jonathan
 * Error code for contact and gallery applications
 */

public enum ErrorCode {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    BAD_PARAMETER(100), // Calls the constructor
    ALREADY_HIRED_ERROR(200),
    COMPANY_FULL_ERROR(201),
    SYNTAX_ERROR(300),
    IO_ERROR(301);

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

