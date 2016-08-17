package gov.samhsa.c2s.trypolicy.service.exception;

public class TryPolicyException extends RuntimeException {
    public TryPolicyException() {
    }

    public TryPolicyException(String message) {
        super(message);
    }

    public TryPolicyException(String message, Throwable cause) {
        super(message, cause);
    }

    public TryPolicyException(Throwable cause) {
        super(cause);
    }

    public TryPolicyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
