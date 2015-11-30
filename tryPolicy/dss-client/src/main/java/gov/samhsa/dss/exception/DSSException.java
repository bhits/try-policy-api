package gov.samhsa.dss.exception;

/**
 * Created by sadhana.chandra on 11/22/2015.
 */
public class DSSException extends RuntimeException {

    /**
     * Instantiates a new d s4 p exception.
     */
    public DSSException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Instantiates a new d s4 p exception.
     *
     * @param arg0 the arg0
     * @param arg1 the arg1
     */
    public DSSException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    /**
     * Instantiates a new d s4 p exception.
     *
     * @param arg0 the arg0
     */
    public DSSException(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * Instantiates a new d s4 p exception.
     *
     * @param arg0 the arg0
     */
    public DSSException(Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6820537567171759096L;

}

