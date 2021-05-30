package ch.hevs.smartphone.applications.contacts.errors;

public enum ErrorCode {

    BAD_PARAMETER(100), // Calls the constructor
    ALREADY_HIRED_ERROR(200),
    COMPANY_FULL_ERROR(201),
    SYNTAX_ERROR(300),
    IO_ERROR(301);


    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
