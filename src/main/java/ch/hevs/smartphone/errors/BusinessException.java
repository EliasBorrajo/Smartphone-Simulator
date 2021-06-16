package ch.hevs.smartphone.errors;

import java.io.Serial;

/**
 * @author Bourquin Jonathan
 * This class concern the error
 */

public class BusinessException extends Throwable {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    @Serial
    private static final long serialVersionUID = -446022369330950597L;

    private final ErrorCode errorCode;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param msg
     * @param errorCode
     */
    public BusinessException(String msg, ErrorCode errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    /**
     * Constructor
     *
     * @param msg
     * @param cause
     * @param errorCode
     */
    public BusinessException(String msg, Throwable cause, ErrorCode errorCode) {
        super(msg, cause);
        this.errorCode = errorCode;
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public int getErrorCode() {
        return errorCode.getCode();
    }
}

