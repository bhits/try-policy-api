package gov.samhsa.mhc.trypolicy.exception;

/**
 * Created by sadhana.chandra on 11/17/2015.
 */
public class TryPolicyException  extends Exception {


    public TryPolicyException() {
        super();
    }

    public TryPolicyException(String message) {
        super(message);
    }

    public TryPolicyException(String message, Throwable cause) {
        super(message, cause);
    }

    public TryPolicyException(String message, Throwable cause,
                              boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TryPolicyException(Throwable cause) {
        super(cause);
    }
}
