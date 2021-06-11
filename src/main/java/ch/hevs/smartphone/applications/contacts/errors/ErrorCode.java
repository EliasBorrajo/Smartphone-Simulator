package ch.hevs.smartphone.applications.contacts.errors;

/**
 * Code d'erreur pour l'application Contactes
 */
public enum ErrorCode
{
    BAD_PARAMETER(100), // Calls the constructor
    READING_JSON_STORAGE_ERROR(200);

    private final int code;

    ErrorCode(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }
}

