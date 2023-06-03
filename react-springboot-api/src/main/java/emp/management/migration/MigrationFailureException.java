package emp.management.migration;


/**
 * Exception thrown from a migration processing run when a migration
 * fails due to an underlying error. This exception will prevent the
 * API from starting up properly.
 * 
 * @author Vishal Kumar
 */
public class MigrationFailureException extends RuntimeException {

    private static final long serialVersionUID = 2802020202429564881L;

    public MigrationFailureException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
