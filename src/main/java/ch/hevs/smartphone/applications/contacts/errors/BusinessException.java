package ch.hevs.smartphone.applications.contacts.errors;

import java.io.Serial;

public class BusinessException extends Throwable
{
    @Serial
    private static final long serialVersionUID = -446022369330950597L;

    private final ErrorCode errorCode;

    public BusinessException(String msg, ErrorCode errorCode)
    {
        super(msg);
        this.errorCode = errorCode;
    }

    public BusinessException(String msg, Throwable cause, ErrorCode errorCode)
    {
        super(msg, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode()
    {
        return errorCode.getCode();
    }
}

