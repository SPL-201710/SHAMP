package co.edu.uniandes.shamp.util.exception;


public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String status;

    public BusinessException(final String status) {
        super();
        this.status = status;
    }

    public BusinessException(final String message, final String status) {
        super(message);
        this.status = status;
    }

    public BusinessException(final String message, final Throwable cause, final String status) {
        super(message, cause);
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
