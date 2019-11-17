package group.jsjxh.community.exception;

public class ParamNoFoundException extends Exception
{
    public ParamNoFoundException() {
    }

    public ParamNoFoundException(String message) {
        super(message);
    }

    public ParamNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamNoFoundException(Throwable cause) {
        super(cause);
    }

    public ParamNoFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
